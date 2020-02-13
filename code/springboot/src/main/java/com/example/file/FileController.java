package com.example.file;

import com.example.consts.IntegerConsts;
import com.example.util.CommonReturnUtil;
import com.example.util.CommonUtil;
import com.example.util.FtpUtil;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@RestController
@RequestMapping("/api/file/v1")
public class FileController {

    @Value("${ftp.uploadTempPath}")
    private String uploadTempPath;

    @Value("${ftp.ip}")
    private   String ftpIp;

    @Value("${ftp.username}")
    private  String ftpUsername;

    @Value("${ftp.password}")
    private  String ftpPassword;

    private String CURRENT_DIR;     // 文件存放的目录
    public static final String DIRSPLIT="/";

    public String getCURRENT_DIR() {
        return CURRENT_DIR;
    }
    public void setCURRENT_DIR(String cURRENTDIR) {
        CURRENT_DIR = cURRENTDIR;
    }

    /**
     * 下载文件
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        String fileName = request.getParameter("fileName");

        InputStream in =  downloadFile(fileName);

        try{
            int index;
            byte[] bytes = new byte[1024];
            FileOutputStream downloadFile = new FileOutputStream("D:\\SIMS\\downloadTemp\\"+fileName);
            while ((index = in.read(bytes)) != -1) {
                downloadFile.write(bytes, 0, index);
                downloadFile.flush();
            }
            in.close();
            downloadFile.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        String fileTempPath = "D:\\SIMS\\downloadTemp\\"+fileName;
        File file = new File(fileTempPath);

        BufferedInputStream bis = null;
        OutputStream os2 = null;
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            in.close();

            fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            response.reset();
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            response.setContentLength((int) file.length());
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

            byte[] buff = new byte[1024];


            os2 = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os2.write(buff, 0, i);
                os2.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                os2.close();
                bis.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }

    /**
     * 文件上传
     * @param multipartFile
     * @return
     */
    @ResponseBody
    @RequestMapping("/upload")
    public Map<String, Object> upload(MultipartFile[] multipartFile) {
        //指定存放上传文件的目录
        String fileDir = uploadTempPath;
        File dir = new File(fileDir);

        //判断目录是否存在，不存在则创建目录
        if (!dir.exists()){
            dir.mkdirs();
       }

        //生成新文件名，防止文件名重复而导致文件覆盖
        //1、获取原文件后缀名 .img .jpg ....
        for (int i = 0; i <multipartFile.length ; i++) {
            String originalFileName = multipartFile[i].getOriginalFilename();
            String suffix = originalFileName.substring(originalFileName.lastIndexOf('.'));
            //2、使用UUID生成新文件名
            String newFileName = CommonUtil.getUUid() + suffix;

            //生成文件
            File file = new File(dir, newFileName);
            //传输内容
            try {
                multipartFile[i].transferTo(file);
                System.out.println("上传文件成功！");
            } catch (IOException e) {
                System.out.println("上传文件失败！");
                e.printStackTrace();
            }
            //至此，文件已经传到了程序运行的服务器上。

            //上传至ftp服务器
            //1、上传文件
            if (uploadToFtp(file,"testUpload")){
                //2、删除本地文件
                file.delete();
                return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_SUCCESS,null,"上传成功！");
            }else {
                //2、删除本地文件
                file.delete();
                return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_FAIL,null,"上传失败！");
            }
        }

        return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_FAIL,null,"上传失败！");
    }

    private boolean uploadToFtp(File file, String targetPath){
        FTPClient ftpClient = new FTPClient();
        try {
            //连接ftp服务器 参数填服务器的ip
            ftpClient.connect(ftpIp);

            //进行登录 参数分别为账号 密码
            ftpClient.login(ftpUsername,ftpPassword);

            //改变工作目录（按自己需要是否改变）
            //只能选择local_root下已存在的目录
            ftpClient.changeWorkingDirectory(targetPath);

            //设置文件类型为二进制文件
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            //开启被动模式（按自己如何配置的ftp服务器来决定是否开启）
            //ftpClient.enterLocalPassiveMode();

            //上传文件 参数：上传后的文件名，输入流
            ftpClient.storeFile(file.getName(), new FileInputStream(file));

            ftpClient.disconnect();
            System.out.println(file.getName());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private InputStream downloadFile(String filename){
        InputStream in=null;
        FTPClient ftpClient = new FTPClient();

        try {
            // 建立连接
            ftpClient.connect(ftpIp);
            ftpClient.enterLocalPassiveMode();
            // 设置传输二进制文件
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            int reply = ftpClient.getReplyCode();
            ftpClient.changeWorkingDirectory(CURRENT_DIR);

            // ftp文件获取文件
            in=ftpClient.retrieveFileStream(filename);

        } catch (Exception e) {

        }
        return in;
    }
}
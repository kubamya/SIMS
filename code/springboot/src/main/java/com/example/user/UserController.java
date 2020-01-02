package com.example.user;

import com.example.consts.IntegerConsts;
import com.example.model.User;
import com.example.user.service.UserService;
import com.example.util.CommonReturnUtil;
import com.example.util.CommonUtil;
import com.example.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("/api/user/v1")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据userId获取user
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserById")
    public Map<String, Object> getUserById(HttpServletRequest request) {
        User user = handleRequest(request);

        try{
            User resultUser = userService.getUserById(user);
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_SUCCESS,resultUser,"查询成功！");
        }catch (Exception e){
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_DATABASEERROR,e.getMessage(),"查询失败！");
        }
    }

    /**
     * 根据userId删除user
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteUser")
    public Map<String, Object> deleteUser(HttpServletRequest request) {
        User user = handleRequest(request);

        try{
            userService.deleteUser(user);
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_SUCCESS,null,"删除成功！");
        }catch (Exception e){
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_DATABASEERROR,e.getMessage(),"删除失败！");
        }
    }

    /**
     * 新增或更新user
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/addOrUpdateUser")
    public Map<String, Object> addOrUpdateUser(HttpServletRequest request) {

        //参数
        User user = handleRequest(request);

        if(user.getCId() == null){
            //新增
            try{
                userService.addUser(user);
                return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_SUCCESS,null,"新增人员成功！");
            }catch (Exception e){
                return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_DATABASEERROR,e.getMessage(),"新增人员失败！");
            }
        }else{
            //修改
            try{
                userService.updateUser(user);
                return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_SUCCESS,null,"修改人员成功！");
            }catch (Exception e){
                return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_DATABASEERROR,e.getMessage(),"修改人员失败！");
            }
        }

    }

    /**
     * 处理前端传递参数公用方法
     * @param request
     * @return
     */
    private User handleRequest(HttpServletRequest request){
        //获取前台参数
        User user = new User();
        user.setCCjr(request.getParameter("cjr"));
        user.setCComId(request.getParameter("comId"));
        user.setCDeptId(request.getParameter("deptId"));
        user.setCEmail(request.getParameter("email"));
        if(request.getParameter("id") != null){
            user.setCId(request.getParameter("id"));
        }else {
            user.setCId(CommonUtil.getUUid());
        }
        user.setCLoginId(request.getParameter("loginId"));
        if(request.getParameter("password") != null){
            try {
                user.setCPassword(SecurityUtil.MD5Encode(request.getParameter("password")));
            } catch (NoSuchAlgorithmException e) {
                user.setCPassword(request.getParameter("password"));
            } catch (UnsupportedEncodingException e) {
                user.setCPassword(request.getParameter("password"));
            }
        }

        user.setCTel(request.getParameter("tel"));
        user.setCUserName(request.getParameter("userName"));
        user.setCXgr(request.getParameter("xgr"));
        user.setDCjsj(CommonUtil.getCurDateTime());
        user.setDXgsj(CommonUtil.getCurDateTime());
        if(request.getParameter("sfyx") != null){
            user.setNSfyx(Integer.parseInt(request.getParameter("sfyx")));
        }
        if(request.getParameter("xssx") != null){
            user.setNXssx(Integer.parseInt(request.getParameter("xssx")));
        }
        return user;
    }
}

package com.example.com;

import com.example.com.service.ComService;
import com.example.consts.IntegerConsts;
import com.example.model.Com;
import com.example.util.CommonReturnUtil;
import com.example.util.CommonUtil;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/com/v1")
@Slf4j
public class ComController {

    @Autowired
    private ComService comService;
    
    /**
     * 根据comid更新com
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateComById")
    public Map<String, Object> updateComById(HttpServletRequest request) {
    	String xssx = request.getParameter("nXssx");
    	Integer nXssx = null;
    	if(StringUtils.isNotEmpty(xssx)) {
    		nXssx = Integer.parseInt(xssx);
    	}
    	
    	Com com = new Com();
    	com.setCId(request.getParameter("comId"));
    	com.setCName(request.getParameter("cName"));
    	com.setNXssx(nXssx);
    	com.setCXgr(request.getParameter("cXgr"));
    	com.setDXgsj(CommonUtil.getCurDateTime());
    	
    	try {
			comService.updateComById(com);
			return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_SUCCESS, null, "保存成功！");
		} catch (Exception e) {
			log.error(e.getMessage());
			return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_DATABASEERROR, null, e.getMessage());
		}
    }
    
    /**
     * 根据comid删除com
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/setComDisableById")
    public Map<String, Object> setComDisableById(HttpServletRequest request) {
    	
    	String comId = request.getParameter("comId");
    	
    	if(StringUtils.isEmpty(comId)) {
    		return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_FAIL, null, "comId不能为空！");
    	}
    	
    	Com com = new Com();
    	com.setCId(comId);
    	com.setCXgr(request.getParameter("cXgr"));
    	com.setDXgsj(CommonUtil.getCurDateTime());
    	com.setNSfyx(IntegerConsts.CODE_NO);
    	
    	try {
			comService.setComDisableById(com);
			return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_SUCCESS, null, "删除成功！");
		} catch (Exception e) {
			log.error(e.getMessage());
			return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_DATABASEERROR, null, e.getMessage());
		}
    }
    
    /**
     * 新增com
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/addCom")
    public Map<String, Object> addCom(HttpServletRequest request) {

    	String xssx = request.getParameter("nXssx");
    	Integer nXssx = null;
    	if(StringUtils.isNotEmpty(xssx)) {
    		nXssx = Integer.parseInt(xssx);
    	}
    	
    	Com com = new Com();
    	com.setCId(CommonUtil.getUUid());
    	com.setDCjsj(CommonUtil.getCurDateTime());
    	com.setCCjr(request.getParameter("cCjr"));
    	com.setCName(request.getParameter("cName"));
    	com.setNXssx(nXssx);
    	com.setNSfyx(IntegerConsts.CODE_YES);
    	
    	try {
			comService.addCom(com);			
			return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_SUCCESS, null, "保存成功");
		} catch (Exception e) {
			log.error(e.getMessage());
			return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_DATABASEERROR, e.getMessage(), "保存失败！");
		}
    }

    /**
     * 通过comid获取com信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/getComById")
    public Map<String, Object> getComById(HttpServletRequest request) {
        Com com = new Com();
        com.setCId(request.getParameter("comId"));
        try {
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_SUCCESS, comService.getComById(com), "查询成功！");
        }catch (Exception e){
        	log.error(e.getMessage());
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_DATABASEERROR,e.getMessage(),"查询失败！");
        }
    }
}

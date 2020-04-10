package com.example.dept;

import com.example.com.service.ComService;
import com.example.consts.IntegerConsts;
import com.example.dept.service.DeptService;
import com.example.model.Com;
import com.example.model.Dept;
import com.example.util.CommonReturnUtil;
import com.example.util.CommonUtil;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dept/v1")
@Slf4j
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private ComService comService;
    
    @ResponseBody
    @RequestMapping("/updateDeptById")
    public Map<String, Object> updateDeptById(HttpServletRequest request) {
    	String cXssx = request.getParameter("nXssx");
    	Integer nXssx = null;
    	if(StringUtils.isNotEmpty(cXssx) && !"null".equals(cXssx)) {
    		nXssx = Integer.parseInt(cXssx);
    	}
    	Dept dept = new Dept();
    	dept.setCId(request.getParameter("cId"));
    	dept.setCName(request.getParameter("cName"));
    	dept.setCXgr(request.getParameter("cXgr"));
    	dept.setDXgsj(CommonUtil.getCurDateTime());
    	dept.setNXssx(nXssx);
    	
    	try {
			deptService.updateDeptById(dept);
			return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_SUCCESS, null, "保存成功！");
		} catch (Exception e) {
			log.error(e.getMessage());
			return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_DATABASEERROR, null, e.getMessage());
		}
    }


    /**
     * 逻辑删除dept
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/setDeptDisableById")
    public Map<String, Object> setDeptDisableById(HttpServletRequest request) {
    	Dept dept = new Dept();
    	dept.setCId(request.getParameter("cId"));
    	dept.setCXgr(request.getParameter("cXgr"));
    	dept.setNSfyx(IntegerConsts.CODE_NO);
    	dept.setDXgsj(CommonUtil.getCurDateTime());
    	
    	try {
			deptService.setDeptDisableById(dept);
			return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_SUCCESS, null, "删除成功！"); 
		} catch (Exception e) {
			log.error(e.getMessage());
			return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_DATABASEERROR, null, e.getMessage());
		}
    }
    
    /**
     * 新增dept
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/addDept")
    public Map<String, Object> addDept(HttpServletRequest request) {
    	String cXssx = request.getParameter("cXssx");
    	Integer nXssx = null;
    	if(StringUtils.isNotEmpty(cXssx)) {
    		nXssx = Integer.parseInt(cXssx);
    	}
    	
    	Dept dept = new Dept();
    	dept.setCId(CommonUtil.getUUid());
    	dept.setCName(request.getParameter("cName"));
    	dept.setNSfyx(IntegerConsts.CODE_YES);
    	dept.setNXssx(nXssx);
    	dept.setCPid(request.getParameter("cPid"));
    	dept.setCComId(request.getParameter("cComid"));
    	dept.setCCjr(request.getParameter("cCjr"));
    	dept.setDCjsj(CommonUtil.getCurDateTime());
    	
    	try {
			deptService.addDept(dept);
			return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_SUCCESS, null, "保存成功！");
		} catch (Exception e) {
			log.error(e.getMessage());
			return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_DATABASEERROR, null, e.getMessage());
		}
    }

    /**
     * 根据comid获取dept树形结构
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDeptTreeByComId")
    public Map<String, Object> getDeptTreeByComId(HttpServletRequest request) {
        Com com = new Com();
        com.setCId(request.getParameter("comId"));

        try{
            com = comService.getComById(com);
            if(com == null){
                return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_NODATA,null,"未查询到com信息！");
            }
        }catch (Exception e){
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_DATABASEERROR,e.getMessage(),"获取com失败！");
        }


        List<Dept> depts = new ArrayList<>();

        try {
            depts = deptService.getDeptsByComId(com);
        }catch (Exception e){
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_DATABASEERROR,e.getMessage(),"获取dept树失败！");
        }

        List<Map<String, Object>> resTree = new ArrayList<>();

        Map<String, Object> tree = new HashMap<>();
        tree.put("label",com.getCName());
        tree.put("id", com.getCId());

        List<Map<String, Object>> treeNodes = new ArrayList<>();
        for (Dept dept : depts){
            if(dept.getCPid() == null){
                Map<String, Object> treeNode = new HashMap<>();
                treeNode.put("label", dept.getCName());
                treeNode.put("id", dept.getCId());

                treeNode.put("children",CommonUtil.getDeptTreeByComId(dept.getCId(), depts));
                treeNodes.add(treeNode);
            }
        }
        tree.put("children", treeNodes);

        resTree.add(tree);
        return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_SUCCESS, resTree,"查询成功！");
    }

    /**
     * 根据comid获取所有的dept
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDeptsByComId")
    public Map<String, Object> getDeptsByComId(HttpServletRequest request) {
        Com com = new Com();
        com.setCId(request.getParameter("comId"));

        try{
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_SUCCESS,deptService.getDeptsByComId(com),"查询成功！");
        }catch (Exception e){
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_DATABASEERROR,e.getMessage(),"获取dept失败！");
        }
    }
}

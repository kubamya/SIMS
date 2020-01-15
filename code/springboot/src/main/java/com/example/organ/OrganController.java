package com.example.organ;

import com.example.com.service.ComService;
import com.example.consts.IntegerConsts;
import com.example.model.Com;
import com.example.model.Dept;
import com.example.model.User;
import com.example.organ.service.OrganService;
import com.example.util.CommonReturnUtil;
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
@RequestMapping("/api/organ/v1")
public class OrganController{

    @Autowired
    private OrganService organService;

    @Autowired
    private ComService comService;

    /**
     * 获取com信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/getComInfo")
    public Map<String, Object> getComInfo(HttpServletRequest request) {
        String comId = request.getParameter("comId");
        if(StringUtils.isBlank(comId)){
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_FAIL,null,"comId不能为空！");
        }

        Com com = new Com();
        com.setCId(comId);

        try{
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_SUCCESS, comService.getComById(com), "查询成功！");
        }catch (Exception e){
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_DATABASEERROR, e.getMessage(), "查询失败！");
        }
    }

    /**
     * 懒加载组织机构树节点
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/getOrganTreeNode")
    public Map<String, Object> getOrganTreeNode(HttpServletRequest request) {
        Dept dept = new Dept();
        User user = new User();
        dept.setCPid(request.getParameter("id"));
        user.setCDeptId(request.getParameter("id"));

        String type = request.getParameter("type");

        List<Dept> deptList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        try{

            if("com".equals(type)){
                dept.setCComId(request.getParameter("id"));
                deptList = organService.getDeptByComid(dept);
            }else{
                deptList = organService.getDeptByPid(dept);
            }
            userList = organService.getUserByDeptId(user);

            List<Map<String, Object>> resultList = new ArrayList<>();
            if(!deptList.isEmpty()){
                for(Dept dept1 : deptList){
                    Map<String, Object> deptMap = new HashMap<>();
                    deptMap.put("name",dept1.getCName());
                    deptMap.put("id",dept1.getCId());
                    deptMap.put("leaf",false);
                    deptMap.put("type","dept");

                    resultList.add(deptMap);
                }
            }
            if(!userList.isEmpty()){
                for(User user1 : userList){
                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("name", user1.getCUserName());
                    userMap.put("id",user1.getCId());
                    userMap.put("leaf", true);
                    userMap.put("type","user");

                    resultList.add(userMap);
                }
            }

            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_SUCCESS,resultList,"查询成功！");
        }catch (Exception e){
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_DATABASEERROR,e.getMessage(),"查询失败！");
        }
    }

    /**
     * 懒加载组织机构数根节点
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/getOrganTreeRoot")
    public Map<String, Object> getOrganTreeRoot(HttpServletRequest request) {
        List<Com> comList = new ArrayList<>();
        List<Map<String, Object>> res = new ArrayList<>();
        try{
            comList = organService.getAllCom();
            if(!comList.isEmpty()){
                for (Com com : comList){
                    Map<String, Object> comMap = new HashMap<>();
                    comMap.put("name", com.getCName());
                    comMap.put("id", com.getCId());
                    comMap.put("type", "com");

                    res.add(comMap);
                }
            }
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_SUCCESS,res,"查询成功！");
        }catch (Exception e){
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_DATABASEERROR,e.getMessage(),"查询失败！");
        }
    }
}

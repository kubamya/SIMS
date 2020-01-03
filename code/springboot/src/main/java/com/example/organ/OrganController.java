package com.example.organ;

import com.example.consts.IntegerConsts;
import com.example.model.Com;
import com.example.model.Dept;
import com.example.model.User;
import com.example.organ.service.OrganService;
import com.example.util.CommonReturnUtil;
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

            if("root".equals(type)){
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
        Com com = new Com();
        com.setCId(request.getParameter("comId"));

        try{
            com = organService.getComById(com);
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_SUCCESS,com,"查询成功！");
        }catch (Exception e){
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_DATABASEERROR,e.getMessage(),"查询失败！");
        }
    }
}

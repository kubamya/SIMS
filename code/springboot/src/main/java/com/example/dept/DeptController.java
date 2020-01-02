package com.example.dept;

import com.example.consts.IntegerConsts;
import com.example.dept.service.DeptService;
import com.example.model.Com;
import com.example.model.Dept;
import com.example.util.CommonReturnUtil;
import com.example.util.CommonUtil;
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
public class DeptController {

    @Autowired
    private DeptService deptService;

    @ResponseBody
    @RequestMapping("/getDeptTreeByComId")
    public Map<String, Object> getDeptTreeByComId(HttpServletRequest request) {
        Com com = new Com();
        com.setCId(request.getParameter("comId"));

        List<Dept> depts = new ArrayList<>();

        try {
            depts = deptService.getDeptsByComId(com);
        }catch (Exception e){
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_DATABASEERROR,e.getMessage(),"获取dept树失败！");
        }

        List<Map<String, Object>> resTree = new ArrayList<>();

        Map<String, Object> tree = new HashMap<>();
        tree.put("label", "");//TODO 获取com信息
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

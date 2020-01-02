package com.example.com;

import com.example.com.service.ComService;
import com.example.consts.IntegerConsts;
import com.example.model.Com;
import com.example.util.CommonReturnUtil;
import com.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/com/v1")
public class ComController {

    @Autowired
    private ComService comService;

    @ResponseBody
    @RequestMapping("/getComById")
    public Map<String, Object> getComById(HttpServletRequest request) {
        Com com = new Com();
        com.setCId(request.getParameter("comId"));

        try {
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_SUCCESS, comService.getComById(com), "查询成功！");
        }catch (Exception e){
            return CommonReturnUtil.CommonReturnMsg(IntegerConsts.RET_CODE_DATABASEERROR,e.getMessage(),"查询失败！");
        }
    }
}

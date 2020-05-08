package com.example.schedule;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedule/v1")
public class ScheduleController {

	/**
	 * 创建日程
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping("/addScheduleByUserid")
    public Map<String, Object> addScheduleByUserid(HttpServletRequest request) {
		
		return null;
	}
}

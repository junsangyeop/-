package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.HouseMgrUserBean;
import com.example.demo.bean.LandNewsBean;
import com.example.demo.dao.HouseMgrDao;
import com.example.demo.dao.LandNewsDao;

@RestController
public class TestController {

	@Autowired
	private LandNewsDao landNewsDao;
	@Autowired
	private HouseMgrDao houseMgrDao;
	
	@GetMapping("/")
	public String welcome(Map<String, Object> model) {
		
		List<LandNewsBean> result = landNewsDao.selectLandNews("");
		
		for(LandNewsBean re : result) {
			System.out.println("news_date : " + re.getNews_date());
			System.out.println("title : " + re.getTitle());
		}
		
		model.put("value", "Hello Spring And gradle");
		return "main";
	}
	
	@GetMapping("/restTest")
	public HashMap<String, List<LandNewsBean>> restTest(Map<String, Object> model) {
		
		HashMap<String, List<LandNewsBean>> result = new HashMap<String, List<LandNewsBean>>();
		
		result.put("results", landNewsDao.selectLandNews(""));
		return result;
		
	}
	
	@GetMapping("/loginTest")
	public HashMap<String, Boolean> loginTest(@RequestParam("id") String email
			, @RequestParam("passwd") String passwd) {
		
		HashMap<String, Boolean> result = new HashMap<String, Boolean>();
		
		HouseMgrUserBean houseMgrUserBean = new HouseMgrUserBean();
		houseMgrUserBean.setEmail(email);
		houseMgrUserBean.setPasswd(passwd);
		
		result.put("results", houseMgrDao.selectHouseMgrUserCheck(houseMgrUserBean));
		
		System.out.println("result : " + result.get("results"));
		
		return result;
	}
	
}

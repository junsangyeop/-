package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.LandNewsBean;
import com.example.demo.dao.LandNewsDao;

@RestController
public class BoardController {

	@Autowired
	private LandNewsDao landNewsDao;
	
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
	
}

package com.abel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abel.dao.StudentDao;
import com.abel.entity.Student;
import com.abel.utils.Page;
import com.abel.utils.dao.QueryResult;

@Controller
public class IndexController {
	
	private static final Logger logger = Logger.getLogger(IndexController.class);
	
	@Autowired
	private StudentDao studentDao;
	
	@RequestMapping("/")
	public String index(HttpServletRequest req,HttpServletResponse res,Model model){
		
		/*Page<Student> page = new Page<Student>(req,res);
		QueryResult<Student> data = studentDao.getScrollData(page.getFirstIndex(), page.getPageSize());
		page.setResult(data.getResult());
		page.setTotal(data.getTotal());
		model.addAttribute("page", page);
		model.addAttribute("message","test");
		logger.debug("page-->"+page);*/
		
		Page<Student> page = studentDao.getPage(new Page<Student>(req,res));
		model.addAttribute("page", page);
		return "index";
	}
	
	
}

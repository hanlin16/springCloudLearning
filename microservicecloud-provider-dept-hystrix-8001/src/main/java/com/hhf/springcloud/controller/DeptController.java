package com.hhf.springcloud.controller;

import com.hhf.springcloud.entities.Dept;
import com.hhf.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class DeptController {
	@Autowired
	private DeptService service = null;

	@RequestMapping(value="/dept/get/{id}",method=RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "processHystrix_Get")
	public Dept get(@PathVariable("id") Long id)
	{
		Dept dept =  this.service.get(id);
		if(null == dept)
		{
			throw new RuntimeException("该ID："+id+"没有没有对应的信息");
		}
		return dept;
	}

	public Dept processHystrix_Get(@PathVariable("id") Long id) {
		Dept dept =  new Dept("该ID："+id+"没有没有对应的信息,null--@HystrixCommand");
		dept.setDeptno(id);
		dept.setDb_source("no this database in MySQL");

		return dept;

	}
}
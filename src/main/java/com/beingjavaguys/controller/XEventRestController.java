package com.beingjavaguys.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.beingjavaguys.model.Status;
import com.beingjavaguys.model.XEventSMSModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beingjavaguys.model.Employee;
import com.beingjavaguys.services.DataServices;
import com.beingjavaguys.services.SmsService;

@Controller
@RequestMapping("/x37")
public class XEventRestController {

	@Autowired
	DataServices dataServices;

	@Autowired
	SmsService smsServices;
	
	static final Logger logger = Logger.getLogger(XEventRestController.class);
	
	@RequestMapping(value = "/sendsms", method = RequestMethod.POST, 
	  consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Status sendSMS(@RequestBody XEventSMSModel smsModel) {
		try {
			smsServices.sendSMS(smsModel);
			return new Status(1, "SMS sent Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public @ResponseBody
	Employee getEmployee(@PathVariable("id") long id) {
		Employee employee = null;
		try {
			employee = dataServices.getEntityById(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public @ResponseBody
	List<Employee> getEmployee() {

		List<Employee> employeeList = null;
		try {
			employeeList = dataServices.getEntityList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeeList;
	}

}

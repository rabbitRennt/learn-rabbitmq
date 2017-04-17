package com.fan.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fan.common.mqbean.ContractRabbitmq;
import com.fan.common.mqbean.TenantRabbitmq;
import com.fan.service.ContractRabbitmqService;

@RestController
@RequestMapping(value = "/rabbitmq")
public class RabbitMqSendController {
	@Autowired
	private ContractRabbitmqService contractRabbitmqService;

	@RequestMapping(value = "contract/direct", method = RequestMethod.GET)

	public ResponseEntity<Object> contractDirect(String content) {
		ContractRabbitmq contract = new ContractRabbitmq();
		contract.setDateCreated(new Date());
		contract.setId(12L);
		contract.setName("liuhan");
		List<String> strList = new ArrayList<>();
		strList.add("liu");
		strList.add("test str");
		contract.setTestStrList(strList);
		contractRabbitmqService.sendContractRabbitmqDirect(contract);

		return new ResponseEntity<>(new String("ok"), HttpStatus.OK);
	}

	@RequestMapping(value = "contract/topic", method = RequestMethod.GET)
	public ResponseEntity<Object> contractTopic(String content) {
		ContractRabbitmq contract = new ContractRabbitmq();
		contract.setDateCreated(new Date());
		contract.setId(12L);
		contract.setName("liuhan");
		List<String> strList = new ArrayList<>();
		strList.add("liu");
		strList.add("test str");
		contract.setTestStrList(strList);
		contractRabbitmqService.sendContractRabbitmqTopic(contract);

		return new ResponseEntity<>(new String("ok"), HttpStatus.OK);

	}

	@RequestMapping(value = "tenant/direct", method = RequestMethod.GET)
	public ResponseEntity<Object> tenantDirect(String content) {
		TenantRabbitmq tenant = new TenantRabbitmq();
		tenant.setId(12L);
		tenant.setName("liuhan");
		contractRabbitmqService.sendTenantRabbitmqDirect(tenant);
		return new ResponseEntity<>(new String("ok"), HttpStatus.OK);
	}

	@RequestMapping(value = "tenant/topic", method = RequestMethod.GET)
	public ResponseEntity<Object> tenantTopic(String content) {
		TenantRabbitmq tenant = new TenantRabbitmq();
		tenant.setId(12L);
		tenant.setName("liuhan");
		contractRabbitmqService.sendTenantRabbitmqTopic(tenant);
		return new ResponseEntity<>(new String("ok"), HttpStatus.OK);
	}
}
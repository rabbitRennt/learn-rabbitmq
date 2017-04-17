package com.fan.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fan.common.mqbean.ContractRabbitmq;
import com.fan.service.ContractRabbitmqService;

/**
 * 定时发送消息
 * 
 * @author Administrator
 *
 */
@Component
@EnableScheduling
public class ExampleAmqpSend {

	@Autowired
	private ContractRabbitmqService contractRabbitmqService;

	@Scheduled(cron = "0/5 * * * * ?")
	public void sendMsg() {
		ContractRabbitmq contract = new ContractRabbitmq();
		contract.setDateCreated(new Date());
		contract.setId(12L);
		contract.setName("liuhan");

		List<String> strList = new ArrayList<>();
        
		int row=RandomUtils.nextInt(0, 10);
		for (int i = 0; i <row ; i++){
			strList.add(RandomStringUtils.randomAlphanumeric(20));
		}
		contract.setTestStrList(strList);
		
		contractRabbitmqService.sendContractRabbitmqDirect(contract);
	}

}

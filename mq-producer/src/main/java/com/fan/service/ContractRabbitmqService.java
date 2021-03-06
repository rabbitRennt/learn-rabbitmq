package com.fan.service;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fan.common.Constants.RabbitmqExchange;
import com.fan.common.Constants.RabbitmqQueue;
import com.fan.common.mqbean.ContractRabbitmq;
import com.fan.common.mqbean.TenantRabbitmq;

@Service
public class ContractRabbitmqService implements ConfirmCallback{

	@Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;
	
	public void sendContractRabbitmqTopic(final ContractRabbitmq ContractRabbitmq) {
        this.rabbitMessagingTemplate.convertAndSend(RabbitmqExchange.CONTRACT_TOPIC, RabbitmqQueue.CONTRACE_SELF, ContractRabbitmq);
    }

    public void sendContractRabbitmqDirect(final ContractRabbitmq ContractRabbitmq) {
        this.rabbitMessagingTemplate.convertAndSend(RabbitmqExchange.CONTRACT_DIRECT, RabbitmqQueue.CONTRACE_SELF, ContractRabbitmq);
    }
    
    public void sendTenantRabbitmqTopic(final TenantRabbitmq tenantRabbitmq){
        this.rabbitMessagingTemplate.convertAndSend(RabbitmqExchange.CONTRACT_TOPIC, RabbitmqQueue.CONTRACE_TENANT, tenantRabbitmq);
    }
	

    public void sendTenantRabbitmqDirect(final TenantRabbitmq tenantRabbitmq){
        this.rabbitMessagingTemplate.convertAndSend(RabbitmqExchange.CONTRACT_DIRECT, RabbitmqQueue.CONTRACE_TENANT, tenantRabbitmq);
    }
    
    /**
     * 回调函数不起作用，暂时没搞清楚
     */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (ack) {
            System.out.println("消息确认成功");
        } else {
        	System.out.println("消息确认失败");
        }
	}
}
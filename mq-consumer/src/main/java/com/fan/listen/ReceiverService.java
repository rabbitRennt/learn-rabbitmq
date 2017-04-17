package com.fan.listen;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fan.common.Constants.RabbitmqQueue;
import com.fan.common.mqbean.ContractRabbitmq;
import com.fan.common.mqbean.TenantRabbitmq;
import com.google.gson.Gson;

@Component
public class ReceiverService {

    @RabbitListener(queues = RabbitmqQueue.CONTRACE_SELF)
    public void receiveContractQueue(ContractRabbitmq contract) {
        System.out.println("Received contract<" + new Gson().toJson(contract) + ">");
    }

    @RabbitListener(queues = RabbitmqQueue.CONTRACE_TENANT)
    public void receiveTenantQueue(TenantRabbitmq tenant) {
        System.out.println("Received Bar<" + new Gson().toJson(tenant) + ">");
    }
}
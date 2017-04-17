package com.fan.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fan.common.Constants.RabbitmqExchange;
import com.fan.common.Constants.RabbitmqQueue;

/**
 * 基本使用的为topic， 此处更多是以案例给出
 * 
 * @author yjpfj1203
 */
@Configuration
public class ContractExchangeConfig {

	// /**
	// * 合同广播型
	// *
	// * @param rabbitAdmin
	// * @return
	// */
	// @Bean
	// FanoutExchange contractFanoutExchange(RabbitAdmin rabbitAdmin) {
	// FanoutExchange contractFanoutExchange = new
	// FanoutExchange(RabbitmqExchange.CONTRACT_FANOUT);
	// rabbitAdmin.declareExchange(contractFanoutExchange);
	// return contractFanoutExchange;
	// }

	/**
	 * 合同->匹配型 默认：, durable = true, autoDelete = false
	 * 
	 * @param rabbitAdmin
	 * @return
	 */
	@Bean
	TopicExchange contractTopicExchangeDurable(RabbitAdmin rabbitAdmin) {
		TopicExchange contractTopicExchange = new TopicExchange(RabbitmqExchange.CONTRACT_TOPIC);
		rabbitAdmin.declareExchange(contractTopicExchange);
		return contractTopicExchange;
	}

	/**
	 * 合同直连型
	 * 
	 * @param rabbitAdmin
	 * @return
	 */
	@Bean
	DirectExchange contractDirectExchange(RabbitAdmin rabbitAdmin) {
		DirectExchange contractDirectExchange = new DirectExchange(RabbitmqExchange.CONTRACT_DIRECT);
		rabbitAdmin.declareExchange(contractDirectExchange);
		return contractDirectExchange;
	}

	@Bean
	Binding bindingExchangeContract(Queue queueContract, FanoutExchange exchange, RabbitAdmin rabbitAdmin) {
		Binding binding = BindingBuilder.bind(queueContract).to(exchange);
		rabbitAdmin.declareBinding(binding);
		return binding;
	}

	/**
	 * 交换机数据
	 * @param queueContract
	 * @param exchange
	 * @param rabbitAdmin
	 * @return
	 */
	@Bean
	Binding bindingExchangeContract(Queue queueContract, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
		Binding binding = BindingBuilder.bind(queueContract).to(exchange).with(RabbitmqQueue.CONTRACE_SELF);
		rabbitAdmin.declareBinding(binding);
		return binding;
	}

	@Bean
	Binding bindingExchangeContract(Queue queueContract, DirectExchange exchange, RabbitAdmin rabbitAdmin) {
		Binding binding = BindingBuilder.bind(queueContract).to(exchange).with(RabbitmqQueue.CONTRACE_SELF);
		rabbitAdmin.declareBinding(binding);
		return binding;
	}

	@Bean
	Binding bindingExchangeTenant(Queue queueTenant, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
		Binding binding = BindingBuilder.bind(queueTenant).to(exchange).with(RabbitmqQueue.CONTRACE_TENANT);
		rabbitAdmin.declareBinding(binding);
		return binding;
	}

	@Bean
	Binding bindingExchangeTenant(Queue queueTenant, DirectExchange exchange, RabbitAdmin rabbitAdmin) {
		Binding binding = BindingBuilder.bind(queueTenant).to(exchange).with(RabbitmqQueue.CONTRACE_TENANT);
		rabbitAdmin.declareBinding(binding);
		return binding;
	}

	/**
	 * 所有关于contract exchange的queue
	 * 
	 * @param rabbitAdmin
	 * @return
	 */
	@Bean
	Queue queueContract(RabbitAdmin rabbitAdmin) {
		Queue queue = new Queue(RabbitmqQueue.CONTRACE_SELF, true);
		rabbitAdmin.declareQueue(queue);
		return queue;
	}

	@Bean
	Queue queueTenant(RabbitAdmin rabbitAdmin) {
		Queue queue = new Queue(RabbitmqQueue.CONTRACE_TENANT, true);
		rabbitAdmin.declareQueue(queue);
		return queue;
	}

}
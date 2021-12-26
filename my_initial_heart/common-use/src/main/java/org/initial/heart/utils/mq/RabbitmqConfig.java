//package org.initial.heart.utils.mq;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.AcknowledgeMode;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@Slf4j
//public class RabbitmqConfig {
//
//    @Value("${dataGovernance.rabbitMq.host}")
//    private String host;
//    @Value("${dataGovernance.rabbitMq.port}")
//    private int port;
//    @Value("${dataGovernance.rabbitMq.username}")
//    private String username;
//    @Value("${dataGovernance.rabbitMq.password}")
//    private String password;
//
//    @Bean("rabbitWaterQualityMQConnectionFactory")
//    public ConnectionFactory rabbitTragectoryMQConnectionFactory(){
//        CachingConnectionFactory factory = new CachingConnectionFactory();
//        log.info("connectionTrabbitWaterQualityMQConnectionFactory username is {},password is {}. host is {}.port is {},use bean is rabbitWaterQualityMQConnectionFactory",username,password,host,port);
//        factory.setHost(host);
//        factory.setPort(port);
//        factory.setUsername(username);
//        factory.setPassword(password);
//        return factory;
//    }
//
//    @Bean("rabbitWaterQualityMQRabbitAdmin")
//    public RabbitAdmin rabbitTragectoryMQRabbitAdmin(@Qualifier("rabbitWaterQualityMQConnectionFactory") ConnectionFactory connectionFactory){
//        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
//        return rabbitAdmin;
//    }
//    @Bean("rabbitWaterQualityMQRabbitTemplate")
//    public RabbitTemplate rabbitWaterQualityMQRabbitTemplate(@Qualifier("rabbitWaterQualityMQConnectionFactory") ConnectionFactory connectionFactory){
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        MessageConverter messageConverter = new Jackson2JsonMessageConverter();
//        rabbitTemplate.setMessageConverter(messageConverter);
//        return rabbitTemplate;
//    }
//
//    @Bean("waterContainerFactory")
//    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(@Qualifier("rabbitWaterQualityMQConnectionFactory") ConnectionFactory connectionFactory){
//        //SimpleRabbitListenerContainerFactory发现消息中有content_type有text就会默认将其转换成string类型的
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        //开启手动 ack
//        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        return factory;
//    }
//
//}

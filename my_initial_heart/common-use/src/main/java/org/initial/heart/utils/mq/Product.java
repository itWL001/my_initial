//package org.initial.heart.utils.mq;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//
//public class Product {
//    @Autowired(required = false)
//    @Qualifier("rabbitWaterQualityMQRabbitTemplate")
//    private RabbitTemplate rabbitWaterQualityMQRabbitTemplate;
//    public static final String ROUTING_KEY_ENVIRONMENT = "water.quality.result.environment.queue";
//    public static final String ROUTING_KEY_VETERINARY = "water.quality.result.veterinary.queue";
//
//    void send(){
//        rabbitWaterQualityMQRabbitTemplate.convertAndSend(ROUTING_KEY_ENVIRONMENT, "myEnvironmentWaterQualityTestResultVOS");
//    }
//
//}

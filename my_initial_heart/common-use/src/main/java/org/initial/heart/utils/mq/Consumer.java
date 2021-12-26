//package org.initial.heart.utils.mq;
//
//import com.rabbitmq.client.Channel;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.Exchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.QueueBinding;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@Slf4j
//@Service
//public class Consumer {
//    public static final String testPosition = "data.etl.syn.veterinary.hr.position.queue";
//    public static final String testExchange = "dataEtlHrPositionFanoutExchange";
//
//    @RabbitListener(
//            containerFactory = "waterContainerFactory",
//            bindings = {@QueueBinding(
//                    exchange = @Exchange(value = testExchange, type = "fanout"),
//                    value = @Queue(value = testPosition)
//            )}
//    )
//    @Transactional(rollbackFor = Exception.class)
//    public synchronized void onMessage(Message message, Channel channel) {
//        try {
//            //todo 业务操作
//        } catch (Exception e) {
//            log.error(testPosition + "插入数据异常");
//        }
//    }
//
//}

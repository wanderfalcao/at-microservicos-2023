package br.com.infnet.alunoService.conf;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConf {
    private final String MESSAGES_QUEUE = "atividade-submetida-aluno-queue-v1";
    private final String MESSAGES_QUEUE_DLQ = "atividade-submetida-aluno-queue-v1.dlq";
    private final String MESSAGES_EXCHANGE = "atividade-submetida-aluno-queue-v1.exc";
    private final String MESSAGES_EXCHANGE_DLQ = "atividade-submetida-aluno-queue-v1.exc.dlq";
    private final String MESSAGES_PARKING_LOT = "atividade-submetida-aluno-queue-v1.parking-lot";
    private final String MESSAGES_EXCHANGE_PARKING_LOT = "atividade-submetida-aluno-queue-v1.parking-lot.exc";

    @Bean
    Queue messagesParkingLot(){
        return QueueBuilder.durable(MESSAGES_PARKING_LOT).build();
    }
    @Bean
    Queue messagesQueueDLQ(){
        return QueueBuilder.durable(MESSAGES_QUEUE_DLQ).build();
    }

    @Bean
    FanoutExchange messagesExchangeDLQ(){
        return new FanoutExchange(MESSAGES_EXCHANGE_DLQ);
    }
    @Bean
    Binding messagesBindingDLQ(){
        return BindingBuilder.bind(messagesQueueDLQ()).to(messagesExchangeDLQ());
    }
    @Bean
    Queue messagesQueue(){
        return QueueBuilder.durable(MESSAGES_QUEUE)
                .withArgument("x-dead-letter-exchange",MESSAGES_EXCHANGE_DLQ)
                .build();
    }
    @Bean
    DirectExchange messagesExchange(){
        return new DirectExchange(MESSAGES_EXCHANGE);
    }
    @Bean
    Binding messageQueueBinding(){
        return BindingBuilder.bind(messagesQueue()).to(messagesExchange()).with(MESSAGES_QUEUE);
    }
    @Bean
    DirectExchange messagesExchangeParkingLot(){
        return new DirectExchange(MESSAGES_EXCHANGE_PARKING_LOT);
    }
    @Bean
    Binding messageParkingLotBinding(){
        return BindingBuilder.bind(messagesParkingLot()).to(messagesExchangeParkingLot()).with(MESSAGES_PARKING_LOT);
    }
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }
    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener(RabbitAdmin admin){
        return event -> admin.initialize();
    }
}

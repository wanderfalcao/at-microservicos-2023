package br.com.infnet.professorservice.receiver;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;

import java.util.HashMap;
import java.util.Map;

@Component
public class AtividadeEnviadaAlunoReceiver {
    private final String MESSAGES_QUEUE = "atividade-submetida-aluno-queue-v1";
    private final String MESSAGES_PARKING_LOT = "atividade-submetida-aluno-queue-v1.parking-lot";
    private final String X_RETRY_HEADER = "x-dlq-retry";
    private final String MESSAGES_QUEUE_DLQ = "atividade-submetida-aluno-queue-v1.dlq";
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = MESSAGES_QUEUE)
    public void processaAtividadeEnviadaPorAluno(@Payload String body){
        System.out.println("Processando a atividade do aluno " + body);
    }
    @RabbitListener(queues = MESSAGES_PARKING_LOT)
    public void processaAtividadeNoParkingLotEnviadaPorAluno(@Payload String body){
        System.out.println("Processando a atividade do aluno " + body);
    }
    @RabbitListener(queues = MESSAGES_QUEUE_DLQ)
    public void reProcess(@Payload String body, @Headers Map<String,Object> headers){
        Integer retryNumber = (Integer) headers.get(X_RETRY_HEADER);
        if(retryNumber==null) retryNumber=0;
        if(retryNumber<2){
            int tryCount = retryNumber+1;
            HashMap<String, Object> args = new HashMap<>();
            args.put(X_RETRY_HEADER,tryCount);

            final MessagePostProcessor messagePostProcessor = message -> {
                MessageProperties messageProperties = message.getMessageProperties();
                args.forEach(messageProperties::setHeader);
                return message;
            };
            rabbitTemplate.convertAndSend(MESSAGES_QUEUE_DLQ,(Object)body,messagePostProcessor);
        }else {
            rabbitTemplate.convertAndSend(MESSAGES_PARKING_LOT,body);
        }
    }
}

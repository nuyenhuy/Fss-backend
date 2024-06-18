package com.insurance.worker.network;

import com.insurance.worker.util.LoggingUtil;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class NetworkClientImpl implements NetworkClient {
    private static Logger logger = LoggingUtil.createLogger(NetworkClientImpl.class);

    private MessageHandler handler;

    @Autowired
    MessageSender kafkaSender;

    @Autowired
    private KafkaListenerEndpointRegistry endpointRegistry;

    private Map<String, ConcurrentMessageListenerContainer<String, Object>> mapSubcribers = new HashMap<>();



    @Override
    public void start(MessageHandler handler) throws Exception {
        this.handler = handler;
        // You may initialize Kafka listeners or perform other initialization tasks here
    }

    @Override
    public void stop() {
        // Implement if needed
    }

    @Override
    public void subcribeToppic(String topic) throws Exception {
        try {
            ConcurrentMessageListenerContainer<String, Object> container = factoryNewSubcriber(topic);
            mapSubcribers.put(topic, container);
        } catch (Exception e) {
            logger.error("Error:Subscribe:Topic:" + e.getMessage());
            throw e;
        }
    }

    @Override
    public void unSubcribeToppic(String topic) throws Exception {
        try {
            if (mapSubcribers.containsKey(topic)) {
                ConcurrentMessageListenerContainer<String, Object> container = mapSubcribers.get(topic);
                if (container != null && container.isRunning()) {
                    container.stop();
                }
                mapSubcribers.remove(topic);
            }
        } catch (Exception e) {
            logger.error("Error:Unsubscribe:Topic:" + e.getMessage());
            throw e;
        }
    }

    @Override
    public void send(String topic, String message) throws Exception {
        kafkaSender.sendMessage(topic, message);
    }

    @Override
    public void send(String topic, Realtime message) throws Exception {
        kafkaSender.sendMessage(topic, message);
    }

    @Override
    public void send(String topic, Request message) throws Exception {
        kafkaSender.sendMessage(topic, message);
    }

    @Override
    public void send(String topic, Response message) throws Exception {
        kafkaSender.sendMessage(topic, message);
    }

    @Override
    public void broadcastWorker(String message) throws Exception {
        kafkaSender.sendMessage(Topics.WORKER_BROADCAST, message);
    }

    @Override
    public void broadcastWorker(Realtime message) throws Exception {
        kafkaSender.sendMessage(Topics.WORKER_BROADCAST, message);
    }

    @Override
    public void broadcastWorker(Request message) throws Exception {
        kafkaSender.sendMessage(Topics.WORKER_BROADCAST, message);
    }

    @Override
    public void broadcastWorker(Response message) throws Exception {
        kafkaSender.sendMessage(Topics.WORKER_BROADCAST, message);
    }

    private ConcurrentMessageListenerContainer<String, Object> factoryNewSubcriber(String topic) {
        ContainerProperties containerProps = new ContainerProperties(topic);
        containerProps.setMessageListener(new MessageReceiver(this.handler));

        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "your-kafka-broker:9092");
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "your-group-id");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        consumerProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        consumerProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        DefaultKafkaConsumerFactory<String, Object> consumerFactory = new DefaultKafkaConsumerFactory<>(consumerProps,
                new StringDeserializer(), new JsonDeserializer<>(Object.class));

        ConcurrentMessageListenerContainer<String, Object> container =
                new ConcurrentMessageListenerContainer<>(consumerFactory, containerProps);
        container.setConcurrency(3); // Set number of concurrent consumers as needed
        container.start();

        // Register container with endpoint registry for management and control
        endpointRegistry.getListenerContainer("your-container-name").start();

        return container;
    }
}

package com.syrianrevo.foodApp.kafkaConfig;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.*;
//import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.config.AbstractKafkaListenerContainerFactory;
import com.syrianrevo.foodApp.kafkaProducerAndConsumer.KafkaConsumerFromTopic;
import com.syrianrevo.foodApp.model.Entry;
import com.syrianrevo.foodApp.model.Menu;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	/*
	 * public static KafkaConsumer<String, Object> createConsumer(String topic){
	 * 
	 * Properties properties = new Properties();
	 * 
	 * properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
	 * Constants.KAFKA_SERVER);
	 * properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
	 * StringDeserializer.class.getName());
	 * properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
	 * JsonDeserializer.class.getName());
	 * properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, Constants.GROUP_ID);
	 * properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
	 * 
	 * //Create Consumer KafkaConsumer<String, Object> consumer = new
	 * KafkaConsumer<String, Object>(properties);
	 * 
	 * //Subscrive Consumer to the topic consumer.subscribe(Arrays.asList(topic));
	 * 
	 * return consumer; }
	 */

	/*
	 * @Bean public Map<String, Object> createConsumer() {
	 * 
	 * Map<String, Object> properties = new HashMap<String, Object>();
	 * 
	 * properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
	 * Constants.KAFKA_SERVER);
	 * properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
	 * StringDeserializer.class.getName());
	 * properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
	 * JsonDeserializer.class.getName());
	 * properties.put(ConsumerConfig.GROUP_ID_CONFIG, Constants.GROUP_ID);
	 * properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
	 * 
	 * 
	 * return properties; }
	 */

	public Map<String, Object> consumerConfig() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.KAFKA_SERVER);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, Constants.GROUP_ID);
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		return properties;
	}

	@Bean
	public ConsumerFactory<String, Menu> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(),
				new JsonDeserializer<>(Menu.class));
	}

	/*
	 * @Bean public ConsumerFactory<String, Entry> consumerFactory() throws
	 * IOException{ return new DefaultKafkaConsumerFactory<>(createConsumer(), new
	 * StringDeserializer(), new JsonDeserializer<>(Entry.class)); }
	 */

	/*
	 * @Bean public ConcurrentKafkaListenerContainerFactory<String, Entry>
	 * kafkaListenerContainerFactory() throws IOException {
	 * ConcurrentKafkaListenerContainerFactory<String, Entry> factory = new
	 * ConcurrentKafkaListenerContainerFactory<>();
	 * factory.setConsumerFactory(consumerFactory()); factory.setErrorHandler(new
	 * ErrorHandler() {
	 * 
	 * @Override public void handle(Exception thrownException,
	 * List<ConsumerRecord<?, ?>> records, Consumer<?, ?> consumer,
	 * MessageListenerContainer container) { String s =
	 * thrownException.getMessage().
	 * split("Error deserializing key/value for partition ")[1]
	 * .split(". If needed, please seek past the record to continue consumption.")[0
	 * ]; String topics = s.split("-")[0]; int offset =
	 * Integer.valueOf(s.split("offset ")[1]); int partition =
	 * Integer.valueOf(s.split("-")[1].split(" at")[0]);
	 * 
	 * TopicPartition topicPartition = new TopicPartition(topics, partition); //
	 * log.info("Skipping " + topic + "-" + partition + " offset " + offset);
	 * consumer.seek(topicPartition, offset + 1);
	 * 
	 * }
	 * 
	 * @Override public void handle(Exception e, ConsumerRecord<?, ?>
	 * consumerRecord) {
	 * 
	 * }
	 * 
	 * @Override public void handle(Exception e, ConsumerRecord<?, ?>
	 * consumerRecord, Consumer<?, ?> consumer) { String s =
	 * e.getMessage().split("Error deserializing key/value for partition ")[1]
	 * .split(". If needed, please seek past the record to continue consumption.")[0
	 * ]; String topics = s.split("-")[0]; int offset =
	 * Integer.valueOf(s.split("offset ")[1]); int partition =
	 * Integer.valueOf(s.split("-")[1].split(" at")[0]);
	 * 
	 * TopicPartition topicPartition = new TopicPartition(topics, partition); //
	 * log.info("Skipping " + topic + "-" + partition + " offset " + offset);
	 * consumer.seek(topicPartition, offset + 1);
	 * 
	 * } }); return factory; }
	 */

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Menu> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Menu> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		
		

		return factory;
	}

	@Bean
	public KafkaConsumerFromTopic receiver() {
		return new KafkaConsumerFromTopic();
	}
	
	

}
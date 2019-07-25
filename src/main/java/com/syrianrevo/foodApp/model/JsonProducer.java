
  package com.syrianrevo.foodApp.model;
  
  
  
  import java.io.FileNotFoundException; import java.io.FileReader; import
  java.io.IOException; import java.util.ArrayList; import
  java.util.concurrent.ExecutionException;
  
  
  import com.google.gson.JsonArray; import com.google.gson.JsonParser; import
  com.syrianrevo.foodApp.kafkaProducerAndConsumer.KafkaProducer; import
  com.syrianrevo.foodApp.model.Entry;
  
  
  public class JsonProducer {
  
  private KafkaProducer producer = new KafkaProducer();
  
  public JsonProducer(String file) throws FileNotFoundException, IOException {
  JsonParser jsonParser = new JsonParser();
  
  try(FileReader reader = new FileReader(file)){ Object obj =
  jsonParser.parse(reader); JsonArray infoList = (JsonArray) obj;
  
  infoList.forEach(info -> { Entry entry = new Entry(info);
  
  try { producer.send(entry); } catch(InterruptedException | ExecutionException
  e) { e.printStackTrace(); } }); } } }
 
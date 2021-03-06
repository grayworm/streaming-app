package com.asiainfo.ocdc.streaming

import java.util.Properties
import kafka.producer.{KeyedMessage, Producer, ProducerConfig}
import org.apache.spark.rdd.RDD

/**
 * Created by leo on 4/27/15.
 */
object EventWriter {
  def writeData(data: RDD[(String, String)], conf: EventRuleConf) {
    val outputType = conf.get("outputtype")
    if ("kafka".equals(outputType)) {
      data.mapPartitions(p => {
        val topic = conf.get("output_topic")
        val props = new Properties()
        props.put("metadata.broker.list", conf.get("brokerlist"))
        props.put("serializer.class", conf.get("serializerclass"))
        val producer = new Producer[String, String](new ProducerConfig(props))
        p.map(x => {
          val key = x._1
          val out = x._2
          var message = List[KeyedMessage[String, String]]()
          message = new KeyedMessage[String, String](topic, key, out) :: message
          producer.send(message: _*)
          x
        })
      }).count()
    } else if ("hdfs".equals(outputType)) {
      //      data.saveAsTextFile(conf.get("outputdir") + "/" + System.currentTimeMillis())
      throw new Exception("EventSourceType " + outputType + " is not support !")
    } else {
      throw new Exception("EventSourceType " + outputType + " is not support !")
    }
  }

}

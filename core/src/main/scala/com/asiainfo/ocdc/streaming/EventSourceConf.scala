package com.asiainfo.ocdc.streaming

/**
 * Created by tianyi on 3/26/15.
 */
class EventSourceConf(conf: Map[String, String] = null) extends BaseConf(conf) {

  def getClassName(): String = get("classname")

}

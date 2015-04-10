package com.asiainfo.ocdc.streaming

import org.scalatest.{BeforeAndAfter, FunSuite}

import scala.collection.immutable

/**
 * Created by yfq on 15/4/3.
 */
class LabelRuleConfSuite extends FunSuite with BeforeAndAfter {

	test("1 测试 LabelRuleConf 配置设置与获取") {

		val map = immutable.Map[String, String]()
		val lrConf = new LabelRuleConf(map)
		lrConf.setAll(map)
		lrConf.set("classname","com.asiainfo.ocdc.streaming.LocationStayRule")
		lrConf.set("stay.limits", (20 * 60 * 1000).toString)
		lrConf.set("stay.matchMax", "true")
		lrConf.set("stay.outputThreshold", "true")
		lrConf.set("stay.timeout", (30 * 60 * 1000).toString)

		assert(lrConf.getClassName()=="com.asiainfo.ocdc.streaming.LocationStayRule")
		assert(lrConf.get("stay.limits") == 20 * 60 * 1000 +"")
		assert(lrConf.get("stay.matchMax")=="true")
		assert(lrConf.get("stay.outputThreshold")=="true")
		assert(lrConf.get("stay.timeout")==30 * 60 * 1000 +"")
	}
}
package com.asiainfo.ocdc.streaming

import scala.collection.mutable.Map

trait CacheManager {

  def getHashCacheList(key: String): List[String]
  def getHashCacheMap(key: String): Map[String, String]
  def getHashCacheString(key: String): String

  def setHashCacheList(key: String, value: List[String])
  def setHashCacheMap(key: String, value: Map[String, String])
  def setHashCacheString(key: String, value: String)

  def getCommonCacheMap(key: String): Map[String, String]
  def getCommonCacheList(key: String): List[String]
  def getCommonCacheValue(cacheName: String, key: String): String

  def setByteCacheString(key: Array[Byte], value: Array[Byte])

  def getByteCacheString(key: Array[Byte]*): List[Array[Byte]]

}

package com.asiainfo.ocdc.streaming

/**
 * Created by liugang on 15-4-27.
 */
class CityRule extends MCLabelRule{
  def attachMCLabel(mcSourceObj: MCSourceObject, cache: StreamingCache): StreamingCache = {
    val imsi = mcSourceObj.imsi

    // 根据imsi 解析出用户开户地市
    val cityid = cityAnalysis(imsi)
    val propMap = scala.collection.mutable.Map[String, String]()
    mcSourceObj.setLabel(Constant.lABEL_CITY, scala.collection.mutable.Map("code" -> cityid))
    println(" set cityid label !!! ")
    propMap.iterator.foreach(x => {
      println(" cityid : " + x._1 + " flag : " + x._2)
    })



    // 根据imsi 解析出用户开户区县
    val countyid = countyAnalysis(imsi)
    val propMap2 = scala.collection.mutable.Map[String, String]()
    mcSourceObj.setLabel(Constant.lABEL_COUNTY, scala.collection.mutable.Map("code" -> countyid))
    println(" set coutyid label !!! ")
    propMap2.iterator.foreach(x => {
      println(" countyid : " + x._1 + " flag : " + x._2)
    })

    cache

  }

  /**
   * 根据imsi解析出当前开户地市
   * @param imsi: MC信令imsi
   * @return 开户地市
   */
  def cityAnalysis(imsi :String): String = {
    val cityid = CacheFactory.getManager.getCommonCacheValue("userinfo", imsi)
    if(cityid == null || cityid.isEmpty)  null else cityid.split(",")(0)
  }

  def countyAnalysis(imsi :String): String = {
    val countyid = CacheFactory.getManager.getCommonCacheValue("userinfo", imsi)
    if(countyid == null || countyid.isEmpty)  null else countyid.split(",")(1)
  }

}

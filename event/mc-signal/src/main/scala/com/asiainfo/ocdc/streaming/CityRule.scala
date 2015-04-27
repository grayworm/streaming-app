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
    mcSourceObj.setLabel(Constant.lABEL_CITY, scala.collection.mutable.Map(cityid -> "true"))
    println(" set cityid label !!! ")
    propMap.iterator.foreach(x => {
      println(" cityid : " + x._1 + " flag : " + x._2)
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
    if(cityid == null || cityid.isEmpty)  null else cityid
  }
}

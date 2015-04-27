package com.asiainfo.ocdc.streaming

/**
 * Created by liugang on 15-4-27.
 *
 */
class NowCityRule extends MCLabelRule{
  def attachMCLabel(mcSourceObj: MCSourceObject, cache: StreamingCache): StreamingCache = {
    val lac = mcSourceObj.lac
    val ci = mcSourceObj.ci

    // 根据largeCell解析出当前所在地市
    val nowcityid = largeCellAnalysis(lac, ci)
    val propMap = scala.collection.mutable.Map[String, String]()
    //onsiteList.foreach(location => propMap += (location -> "true"))
    mcSourceObj.setLabel(Constant.lABEL_NOW_CITY, scala.collection.mutable.Map("code" ->nowcityid))
    println(" set nowcityid label !!! ")
    propMap.iterator.foreach(x => {
      println(" nowcityid : " + x._1 + " flag : " + x._2)
    })
    cache
  }

  /**
   * 根据largeCell解析出当前所在地市
   * @param lac:MC信令代码
   * @param ci:MC信令代码
   * @return 当前所在地市
   */
  def largeCellAnalysis(lac: String, ci: String): String = {
    val nowcityid = CacheFactory.getManager.getCommonCacheValue("btsinfo", lac+":"+ci)
    if(nowcityid == null || nowcityid.isEmpty)  null else nowcityid
  }
}

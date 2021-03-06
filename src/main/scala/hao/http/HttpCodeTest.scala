package hao.http

import util.control.Breaks._
import com.alibaba.fastjson.{JSON, JSONArray, JSONObject}

import scala.collection.mutable.ArrayBuffer


object HttpCodeTest {
  def main(args: Array[String]): Unit = {
    val author_name_data = "-1007580891651757873,乡园旅行|-1036636649548255581,恒辉会展人|-1071163133918319496,工业互联|-1071804293842358353,美术家|-1077309794761693883,佛山丽源|-1094154641304771147,UNIDOITPO北京|-1101108584396048201,小岚讲科技|-1110770041352431563,眉山微闻|-1165624761583569623,共享教育|-117287091153383714,王王其|-1173072802345431240,旅界观察|-1179504591786775692,品牌观察报|-1181746553674965780,溜达留学|-1193766745927651815,城市一线|-1205191411272733273,轮滑也疯狂|-1208026283949673359,山东温和酒业集团|-1219869270750175115,Bestfood美食|-1270450349161772083,侃侃谈政|-1297562627936360986,力营彩涂应用小知识|-1318806163603443022,搜狐号百科|-1319610504463542199,本缇|-1325882227019795663,观察前言|-1332828722061190565,郭儿Red|-1334786946957226766,遵化发布|-1338229189789468761,北斗卫星手表|-134080390631274264,商汇莱州|-1368943127268928049,会乐人|-1375611356681220783,百年扁氏|-1378279475499826520,深度科幻|-137840234932981248,无锡珍岛快讯|-1383196008820328797,热门科技|-138422597184496875,狮子会龙之队服务队|-138456424974418453,半夏自然理疗馆|-1385826573367058480,哎生活美食|-1398075213020964609,小童童童童|-1398652903956951874,正版UG软件|-1407334924926307378,Fastdata极数|-141386664284462280,盐城顾泽朝聚眼科医院|-1415823206622966889,孤独的老牛|-1416587463050318873,钧富统一|-1416820424689927778,东亚都邑研究院|-1421569005328793859,易博士教风水|-1427609927461810354,昇润付科技|-1427649148850399153,郑钧|-1463759258924999484,重庆众盟数据|-1465967402671369513,草莓essay|-1471850432814170207,一江清水老刘|-1480258658292748616,闪耀的记事本|-1488133148040435993,宏岳管业|-1518692202967462192,百安家中医|-1563381635157383487,锦绣龙都|-1569305592373302842,阳光教育d|-1590073113348529671,康泰劳务|-1602022009518632660,济宁市育才中学|-1610929152324368796,E派速达|-1615893794136904833,inDare格外|-1634536163878685951,橙黄橘绿娱乐|-1636645276663005336,体坛观察员|-1673872760102031801,贵州好前途教育|-1683745813419095723,邹鹏辉（罗伯特）|-1687258939494993822,中美华东网络学院|-1704300664821198837,扒点时尚|-1718092443564520967,开化县行政服务中心|-1726292615728343956,澳洲可爱多|-174044890890266578,睿驰科技|-1765873052836855823,威斯盾物联科技|-1778773455672879957,东城九号高尔夫俱乐部|-1783884872291168362,娄底恒源教育|-1784873968937671641,教书匠|-1790309931718091883,轿车二厂涂装车间|-1790989306917583096,新麦芽教育|-1796317517109143875,莱州弘盛达|-1827333658444308782,灵猫财经|-1830596872828797364,新天龙八部官网|-1856229852288534801,搜易贷|-1861792602719596165,小萝莉分享科技|-1862973759600504261,安生呓语|-1874226395423381518,波澜不惊|-1882970459015764518,中南大学爱尔眼科学院|-1884593771664697329,长安福特濮阳福华4S店|-1900767390363853759,欲叶小仙女|-1907971455253606039,行走的油条|-1910290602751439019,车途小舟|-1913889108005884292,云大培训|-191972751480764109,新疆马南|-1926512311553241225,恒韵跆拳道|-1944725528695656738,文化热辣评|-1954241347107157820,泰玛吉他|-1962379356133137663,汽修技师联盟.|-1994996744966243918,大鱼时尚说|-1996775480269687711,FLAGDream|-1998295954498865729,银亿亿彩购物中心|-2003858485403816007,轻点我坦白|-2005272220619408066,一起了解|-2014797498404356650,趣玩乐享汇|-202482068508022256,青春内师|-2052120567505247854,都爱说实话|-2062358918538629620,九色鹿绒线|-2072923549665373683,包心菜议娱乐"
//    val author_name_data = "-1007580891651757873,乡园旅行|-1020341257851152376,汽车控|-1036636649548255581,恒辉会展人"
    val author_name_data_list  = author_name_data.split("\\|")
    println(author_name_data_list)
    val buffer1 = ArrayBuffer[String]()
    for(info <- author_name_data_list){
      buffer1.append(info)
    }
    println(author_name_data_list.length)
    val data_length = author_name_data_list.length
    val SEND_NUMS = 10
    val count = (data_length/SEND_NUMS) + 1
    var num = 0
    var start = 0
    var end = 0
    breakable{
      for(i <- 0 to count) {
        start = num
        if(data_length <= start) {
          break
        }
        end = num + SEND_NUMS
        if(i == (count - 1)){
          end = data_length
        }
        println("start: " + start + ", end: " + end + ", count: " + count)
        val jsonSend = new JSONObject()
        val jsonArray = new JSONArray()
        for(i <- start until end) {
          val author_name_info_list = buffer1(i).split(",")
          val author_name_hash = author_name_info_list(0)
          val author_name = author_name_info_list(1)
          val jsonObject = new JSONObject()
          jsonObject.put("tag_id", author_name_hash.toLong)
          jsonObject.put("name", author_name)
          jsonArray.add(jsonObject)
        }
        jsonSend.put("data", jsonArray)
        val author_name_info = jsonSend.toJSONString
        println(author_name_info)
        println("******************************************************")
        val url = "http://10.16.70.194:8808/dba_query_redis_info"
//        val url = "http://aedev.adrd.sohuno.com/melon/api/personalmedia"
        val httpClient = new HttpClient1()
        val retInfo: Array[String]= httpClient.sendHttpPostRequest(url, author_name_info)
        var ret_info = ""
        for(info <- retInfo) {
          ret_info += info
        }
//        println(ret_info)
        val ret_json = JSON.toJSON(ret_info)
//        val rowCount = ret_json.
        println(ret_json.toString)
        println("total nums: " + data_length + ", send nums: " + end + ", send time: " + (i+1))
        num += SEND_NUMS
        Thread.sleep(1000)
      }
    }
  }
}

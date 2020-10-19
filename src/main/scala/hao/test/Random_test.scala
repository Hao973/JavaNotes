package hao.test

object Random_test {
  def main(args: Array[String]): Unit = {
    val servers: String = "10.31.71.25:8000,10.31.71.25:8001,10.31.71.25:8002,10.31.71.25:8003,10.31.71.25:8004,10.31.71.26:8000,10.31.71.26:8001,10.31.71.26:8002,10.31.71.26:8003,10.31.71.26:8004,10.18.70.42:8000,10.18.70.42:8001,10.18.70.42:8002,10.18.70.42:8003,10.18.70.42:8004,10.18.70.43:8000,10.18.70.43:8001,10.18.70.43:8002,10.18.70.43:8003,10.18.70.43:8004,10.31.71.19:8000,10.31.71.19:8001,10.31.71.19:8002,10.31.71.19:8003,10.31.71.19:8004,10.31.71.20:8000,10.31.71.20:8001,10.31.71.20:8002,10.31.71.20:8003,10.31.71.20:8004,10.31.71.19:8000,10.31.71.19:8001,10.31.71.19:8002,10.31.71.19:8003,10.31.71.19:8004,10.31.71.20:8000,10.31.71.20:8001,10.31.71.20:8002,10.31.71.20:8003,10.31.71.20:8004,10.31.71.25:8000,10.31.71.25:8001,10.31.71.25:8002,10.31.71.25:8003,10.31.71.25:8004,10.31.71.26:8000,10.31.71.26:8001,10.31.71.26:8002,10.31.71.26:8003,10.31.71.26:8004"
    val serversList = servers.split(",")
    val randomNum = (new util.Random).nextInt(serversList.length)
    val redisHost = serversList(randomNum).split(":")(0)
    val redisPort = serversList(randomNum).split(":")(1).toInt
    println("randomNum: " + randomNum + ", serverInfo: " + serversList(randomNum) + ", redisHost: " + redisHost + ", redisPort:" + redisPort)
  }
}

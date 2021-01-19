package hao.test

object MapDemo {
  def main(args: Array[String]) {
    val colors = Map("red" -> "#FF0000",
      "azure" -> "#F0FFFF",
      "peru" -> "#CD853F")

    val nums: Map[Int, Int] = Map(1 -> 1, 2->1, 3->3)

    println("colors 中的键为 : " + colors.keys)
    println("colors 中的值为 : " + colors.values)
    println("检测 colors 是否为空 : " + colors.isEmpty)
    println(nums.values.toStream.sum)
  }
}

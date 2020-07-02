package cn.edu.xmu
import java.text.SimpleDateFormat
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import scala.util.matching.Regex
object UserOnlineAnalysis {
  def main(args: Array[String]) {
    if (args.length != 1) {
      System.err.println("Usage: UserOnlineAnalysis <input>")
      System.exit(1)
    }
    val conf = new SparkConf().setAppName("UserOnlineAnalysis").setMaster("local[4]")
    val sc = new SparkContext(conf)
    println("输入文件的路径:" + args(0))
    val data = sc.textFile(args(0))
    println("过滤lsecID或ID不存在的数据")
    val cleanData = data.filter(_.contains("ID")).filter(_.contains("rongziguimodengji")).filter(_.contains("city")).filter(_.contains("xunliyaoqiu")).filter(_.contains("company_size")).filter(_.contains("shifouzhaopin")).filter(_.contains("gongzuojinyandengji")).filter(_.contains("pingjungongzuojingtan")).filter(_.contains("dierzhiweileixing"))
    val cleanMap = cleanData.map {
      line =>
        val data = formatLine(line).split(",")
        (data(0), data(1), data(2), data(3), data(4), data(5)) // ,data(6))
    }
    println("打印出cleanMap数据：")
    cleanMap.collect().foreach(println)
    println("明细数据插入数据库")
    cleanMap.foreachPartition(MysqlUtil.cleanMap)
    println("明细数据插入数据库_结束")


    val exportDetailData = data.filter(_.contains("ID")).filter(_.contains("rongziguimodengji")).filter(_.contains("city")).filter(_.contains("xunliyaoqiu")).filter(_.contains("company_size")).filter(_.contains("shifouzhaopin")).filter(_.contains("gongzuojinyandengji")).filter(_.contains("pingjungongzuojingtan")).filter(_.contains("dierzhiweileixing"))
    val exportDetailMap = exportDetailData.map {
      line =>
        val data = formatLine(line).split(",")

        (data(0), data(8), data(7).toInt)// ,data(6))
    }
    println("打印出detail数据：")
    exportDetailMap.collect().foreach(println)
    println("明细数据插入数据库")
    exportDetailMap.foreachPartition(MysqlUtil.exportDetailMap)
    println("明细数据插入数据库_结束")



    val exportSumData = data.filter(_.contains("ID")).filter(_.contains("rongziguimodengji")).filter(_.contains("city")).filter(_.contains("xunliyaoqiu")).filter(_.contains("company_size")).filter(_.contains("shifouzhaopin")).filter(_.contains("gongzuojinyandengji")).filter(_.contains("pingjungongzuojingtan")).filter(_.contains("dierzhiweileixing"))
    val exportSumMap = exportSumData.map {
      line =>
        val data = formatLine(line).split(",")

        (data(0), data(6).toInt, data(7).toInt) // ,data(6))
    }
    println("打印出sum数据：")
    exportSumMap.collect().foreach(println)
    println("明细数据插入数据库")
    exportSumMap.foreachPartition(MysqlUtil.exportSumMap)
    println("明细数据插入数据库_结束")
    /*val cleanMapFilter = cleanData.map {
      line =>
        val data = formatLine(line).split(",")
        (data(0), data(1))
    }
    println("输出时每行分组的第2个元素列表按照时间排序sortByKey()")
    val rdd = cleanMapFilter.groupByKey().map(x => (x._1, x._2.toList.sorted)
    rdd.cache()
    println("导出明细数据:")
    exportDetailData(rdd)
    println("导出统计数据:")
    exportSumData(rdd)
    rdd.unpersist()
    sc.stop()
    println("全部结束")*/
  }

 
  /*def exportDetailData(map: RDD[(String, String,String)]): Unit = {
    val result:RDD[(String, String,String)] = map.flatMap {
      x =>
        val len = x._2.length
        val array = new Array[(String, String, String)](len)
        array=(x._1,x._9,x._8)
        array
    }
    println("输出detail数据：")
    //result.collect().foreach(println)
    println("detail插入mysql数据库_开始")
    result.foreachPartition(MysqlUtil.addsdetail)
    println("detail插入mysql数据库_结束")
  }*/

  
  /*def exportSumData(map: RDD[(String, List[String])]): Unit = {
    val result: RDD[(String, Int, Int)] = map.map {
      x =>
        val a = x._7
        val b = x._8
        //输出ime,登录次数,总时长(秒)
        (x._1, a.toInt, b.toInt)
    }
    println("输出sum数据：")
    //result.collect().foreach(println)
    println("sum插入mysql数据库_开始")
    result.foreachPartition(MysqlUtil.addsum)
    println("sum插入mysql数据库_结束")
  }*/


  def formatLine(line: String): String = {
    val IDRegex = """"ID":"([0-9]+)",""".r
    val rongziguimodengjiRegex = """"rongziguimodengji":"([0-9]+)",""".r
    val pingjungongzuojingtanRegex =""""pingjungongzuojingtan":"([0-9]+)",""".r
    val gongzuojinyandengjiRegex =""""gongzuojinyandengji":"([0-9]+)",""".r
    val xunliyaoqiuRegex =""""xunliyaoqiu":"([0-9]+)",""".r
    //val xunlidengjiRegex =""""xunlidengji":"([0-9]+)",""".r
    val xinziRegex = """"xinzi":"([^\\\/\^\"]+)",""".r
    val dierzhiweileixingRegex = """"dierzhiweileixing":"([^\\\/\^\"]+)",""".r
    val cityRegex = """"city":"([^\\\/\^\"]+)",""".r
    val avg_work_yearRegex = """"avg_work_year":"([0-9]+)",""".r
    val yuexinRegex = """"yuexin":"([^\\\/\^\"]+)",""".r
    val company_sizeRegex = """"company_size":"([0-9\-]+)",""".r
    //val sizeRegex = """"size":"([0-9\-]+)",""".r
    val shifouzhaopinRegex = """"shifouzhaopin":"([0-9\.]+)""".r
    //val zhaopinqingkuangRegex = """"zhaopinqingkuang":"([0-9]+)",""".r


    val ID = getDataByPattern(IDRegex, line)
    val rongziguimodengji = getDataByPattern(rongziguimodengjiRegex, line)
    val pingjungongzuojingtan = getDataByPattern(pingjungongzuojingtanRegex, line)
    val gongzuojinyandengji = getDataByPattern(gongzuojinyandengjiRegex, line)
    val xunliyaoqiu = getDataByPattern(xunliyaoqiuRegex, line)
    // val xunlidengji = getDataByPattern(xunlidengjiRegex, line)
    val xinzi = getDataByPattern(xinziRegex, line)
    val dierzhiweileixing = getDataByPattern(dierzhiweileixingRegex, line)
    val city = getDataByPattern(cityRegex, line)
    val avg_work_year = getDataByPattern(avg_work_yearRegex, line)
    val yuexin = getDataByPattern(yuexinRegex, line)
    val company_size = getDataByPattern(company_sizeRegex, line)
    //val size = getDataByPattern(sizeRegex, line)
    val shifouzhaopin = getDataByPattern(shifouzhaopinRegex, line)
    //val zhaopinqingkuang = getDataByPattern(zhaopinqingkuangRegex, line)

    //输出数据
    ID + "," + rongziguimodengji + "," + city + "," + xunliyaoqiu + "," + company_size + "," + shifouzhaopin + "," + gongzuojinyandengji + "," + pingjungongzuojingtan + "," + dierzhiweileixing
  }

  /* 根据正则表达式,查找相应值*/
  def getDataByPattern(p: Regex, line: String): String = {
    val result = (p.findFirstMatchIn(line)).map(item => {
      val s = item group 1 //返回匹配上正则的第一个字符串。
      s
    })
    result.getOrElse("NULL")
  }
}
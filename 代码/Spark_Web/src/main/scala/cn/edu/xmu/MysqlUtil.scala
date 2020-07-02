package cn.edu.xmu

import java.sql.{Connection, DriverManager, PreparedStatement}
import org.apache.spark.sql.{DataFrame, Row, SQLContext}

object MysqlUtil {

  val url = "jdbc:mysql://localhost:3306/spark_web?useUnicode=true&characterEncoding=UTF-8"
  val prop = new java.util.Properties
  prop.setProperty("user","root")
  prop.setProperty("password","root")
  /**
    * 插入数据
    * @param iterator
    */

  def cleanMap(iterator: Iterator[(String, String, String, String, String, String)]): Unit = {
    var conn: Connection = null
    var ps: PreparedStatement = null
    val sql = "replace into cleanMap values (?, ?, ?, ?, ?, ?)"
    conn = DriverManager.getConnection(url,prop)
    iterator.foreach(data => {
      ps = conn.prepareStatement(sql)
      ps.setString(1, data._1)
      ps.setString(2, data._2)
      ps.setString(3, data._3)
      ps.setString(4, data._4)
      ps.setString(5, data._5)
      ps.setString(6, data._6)
      ps.executeUpdate()
    })
    if (ps != null) {
      ps.close()
    }
    if (conn != null) {
      conn.close()
    }
  }

  /**
    * 插入数据
    * @param iterator
    */
  def exportSumMap(iterator: Iterator[(String,Int,Int)]): Unit = {
    var conn: Connection = null
    var ps: PreparedStatement = null
    val sql = "replace into sum values (?, ?, ?)"
    conn = DriverManager.getConnection(url,prop)
    iterator.foreach(data => {
      ps = conn.prepareStatement(sql)
      ps.setString(1, data._1)
      ps.setInt(2, data._2)
      ps.setInt(3, data._3)
      ps.executeUpdate()
    })
    if (ps != null) {
      ps.close()
    }
    if (conn != null) {
      conn.close()
    }
  }

  def exportDetailMap(iterator: Iterator[(String, String,Int)]): Unit = {
    var conn: Connection = null
    var ps: PreparedStatement = null
    val sql = "replace into detail values (?, ?, ?)"
    conn = DriverManager.getConnection(url,prop)
    iterator.foreach(data => {
      ps = conn.prepareStatement(sql)
      ps.setString(1, data._1)
      ps.setString(2, data._2)
      ps.setInt(3, data._3)
      ps.executeUpdate()
    })
    if (ps != null) {
      ps.close()
    }
    if (conn != null) {
      conn.close()
    }
  }
}
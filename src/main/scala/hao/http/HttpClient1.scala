package hao.http

import java.net.URL
import java.net.HttpURLConnection
import java.io.{DataOutputStream, InputStream, InputStreamReader, BufferedReader}
import java.nio.charset.StandardCharsets
import java.nio.charset.Charset


class HttpClient1 {
  def sendHttpRequest(
                       url: String,
                       method: String,
                       body: String = ""): HttpURLConnection = {
    val conn = new URL(url).openConnection().asInstanceOf[HttpURLConnection]
    conn.setRequestMethod(method)
    if (body.nonEmpty) {
      conn.setDoOutput(true)
      conn.setRequestProperty("Content-Type", "application/json")
      conn.setRequestProperty("Content-Length", Integer.toString(body.length))
      val out = new DataOutputStream(conn.getOutputStream)
      out.write(body.getBytes(StandardCharsets.UTF_8))
      out.close()
    }
    conn
  }

  def sendHttpPostRequest(url: String, body: String = ""): Array[String] = {
    val conn = new URL(url).openConnection().asInstanceOf[HttpURLConnection]
    conn.setRequestMethod("POST")
    conn.setDoOutput(true)

    conn.setRequestProperty("Content-Type", "application/json")
    conn.setRequestProperty("Content-Length", Integer.toString(body.length))
    val out = new DataOutputStream(conn.getOutputStream)
    out.write(body.getBytes(StandardCharsets.UTF_8))
    out.flush()
    println(conn.getResponseCode)
    var result: Array[String] = null
    if (HttpURLConnection.HTTP_OK == conn.getResponseCode){
      val inStream: InputStream = conn.getInputStream
      result = readLines(inStream)
    }
    result
  }

  def readLines(inStream: InputStream, encode: String = "UTF-8"): Array[String] = {
    val reader = new BufferedReader(new InputStreamReader(inStream, Charset.forName(encode)))
    try Iterator.continually(reader.readLine()).takeWhile(_ != null).toArray
    finally reader.close()
  }
}

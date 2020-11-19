package com.hao.common.util.mysql;

import ads.DmpService;
import cm.CommonTypes;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import redis.clients.jedis.Jedis;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MySQLDataOperate {

    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
//    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";

    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    // mysql -uadp_test_rw -pYvPZ29A1IuL3531 -h10.11.166.161 adp_test
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://10.11.166.161:3306/adp_test?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "adp_test_rw";
    static final String PASS = "YvPZ29A1IuL3531";

    public static void queryDemo(){
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "select keyword,hash_id from adp_ct_new_stop_keywords_info where is_new=0;";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                long hash_id  = rs.getLong("hash_id");
                String keyword = rs.getString("keyword");

                // 输出数据
                System.out.print("hash_id: " + hash_id);
                System.out.print(", keyword: " + keyword);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

    public static void queryWriteRedisDemo(){
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "select keyword,hash_id from adp_ct_new_stop_keywords_info where is_new=0;";
            ResultSet rs = stmt.executeQuery(sql);

            // redis
            String redisHost = "10.16.70.194";
            int redisPort = 6379;
            Jedis jedis = new Jedis(redisHost, redisPort);
            // auth
            String redisPassword = "ER8YKWPm";
            jedis.auth(redisPassword);
            System.out.println("connect redis[" + redisHost + ": " + redisHost + "] ok");
            String keyStrStop = "ct_app_stop_492012002";
            String keyStrStop1 = "ct_app_stop_492012002_v1";
            DmpService.PageAttributeResponse.Builder responseBuilder = DmpService.PageAttributeResponse.newBuilder();
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                long hash_id  = rs.getLong("hash_id");
                String keyword = rs.getString("keyword");

                // 输出数据
                System.out.print("hash_id: " + hash_id);
                System.out.print(", keyword: " + keyword);
                System.out.print("\n");

                CommonTypes.Keyword.Builder builder = CommonTypes.Keyword.newBuilder();
                ByteString text = ByteString.copyFromUtf8(keyword);
                builder.setText(text);
                builder.setKeywordId(hash_id);
                builder.setTimestamp(0);
                builder.setWeight(0);
                System.out.println(builder.build());
                responseBuilder.addPageKeyword(builder.build());
            }
            String redisValue = new String(responseBuilder.build().toByteArray());
            System.out.println(redisValue);
            byte[] redisValueBytes = responseBuilder.build().toByteArray();
            jedis.set(keyStrStop.getBytes(), redisValueBytes);
            jedis.set(keyStrStop1, redisValue);
            byte[] retRedis = jedis.get(keyStrStop.getBytes());
            try {
                DmpService.PageAttributeResponse response = DmpService.PageAttributeResponse.parseFrom(retRedis);
                System.out.println(response);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

    public static void queryWriteStopRedisDemo(){
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "select keyword,hash_id from adp_ct_new_stop_keywords_info " +
                    "where keyword in (\"日料\",\"公关事件\",\"品牌\",\"辱华事件\",\"安平镇\",\"假货\",\"三眼井\",\"负面信息\",\"冷空气\",\"改装\",\"音响\",\"大纵湖\",\"搜狐广告\",\"高仿\",\"违法建筑\",\"A货\",\"教师\",\"形象危机\",\"重庆市\",\"北京丰台\",\"洋葱炒鸡蛋\",\"危机公关\",\"飞机票\",\"往返机票\",\"新兴产业\");";
            System.out.println("sql: " + sql);
            ResultSet rs = stmt.executeQuery(sql);

            // redis
            String redisHost = "10.16.70.194";
            int redisPort = 6379;
            Jedis jedis = new Jedis(redisHost, redisPort);
            // auth
            String redisPassword = "ER8YKWPm";
            jedis.auth(redisPassword);
            System.out.println("connect redis[" + redisHost + ": " + redisHost + "] ok");

            // 展开结果集数据库
            HashMap<String, String> stopWordMap = new HashMap<String, String>();
            while(rs.next()){
                // 通过字段检索
                long hash_id  = rs.getLong("hash_id");
                String keyword = rs.getString("keyword");
                // 输出数据
                System.out.print("hash_id: " + hash_id);
                System.out.print(", keyword: " + keyword);
                System.out.print("\n");
                stopWordMap.put(keyword, Long.toString(hash_id));
            }
            for (Map.Entry<String, String> entry : stopWordMap.entrySet()){
                System.out.println("stopWordMap: " + entry.getKey() + ": " + entry.getValue());
            }
            String stopWordData = "369574479|日料,369574479|公关事件,390296177|品牌,390296177|辱华事件,456179841|安平镇,462408950|假货,462408950|三眼井,468105180|负面信息,468105180|冷空气,470512522|改装,470512522|音响,477562601|大纵湖,477562601|搜狐广告,478790442|高仿,478790442|违法建筑,479126905|A货,479126905|教师,483064605|形象危机,483064605|重庆市,484037705|北京丰台,484037705|洋葱炒鸡蛋,490634630|危机公关,490634630|飞机票,491910353|往返机票,491980458|新兴产业";
            String[] stopWordDataList = stopWordData.split(",");

            HashMap<String, ArrayList<String>> DocidStopWordMap = new HashMap<>();
            for(String docidWord: stopWordDataList){
                String docID = docidWord.split("\\|")[0];
                String word = docidWord.split("\\|")[1];
                System.out.println(docID + ": " + word);
                if(DocidStopWordMap.containsKey(docID)){
                    DocidStopWordMap.get(docID).add(word);
                }else{
                    ArrayList<String> word_list = new ArrayList<String>();
                    word_list.add(word);
                    DocidStopWordMap.put(docID, word_list);
                }
            }
            for(Map.Entry<String, ArrayList<String>> entry: DocidStopWordMap.entrySet()){
                DmpService.PageAttributeResponse.Builder responseBuilder = DmpService.PageAttributeResponse.newBuilder();
                System.out.println("---------------------------");
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
                for(String word: entry.getValue()){
                    System.out.println(word);
                    String strHashID = stopWordMap.get(word);
                    if(null == strHashID){
                        continue;
                    }
                    System.out.println("find " + word + ": " + strHashID);
                    long hash_id = Long.parseLong(strHashID);
                    CommonTypes.Keyword.Builder builder = CommonTypes.Keyword.newBuilder();
                    ByteString text = ByteString.copyFromUtf8(word);
                    builder.setText(text);
                    builder.setKeywordId(hash_id);
                    builder.setTimestamp(0);
                    builder.setWeight(0);
                    System.out.println(builder.build());
                    responseBuilder.addPageKeyword(builder.build());
                    System.out.println(responseBuilder.build().toString());
                }
                String t_keyStrStop = "ct_app_stop_" + entry.getKey();
                jedis.set(t_keyStrStop.getBytes(), responseBuilder.build().toByteArray());
                String t_keyStrStopNews = "ct_news_stop_" + entry.getKey();
                jedis.set(t_keyStrStopNews.getBytes(), responseBuilder.build().toByteArray());
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }


    public static void main(String[] args) {
//        queryWriteRedisDemo();
//        queryDemo();
        queryWriteStopRedisDemo();
    }
}
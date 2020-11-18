package com.hao.common.util.mysql;

import ads.DmpService;
import cm.CommonTypes;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import redis.clients.jedis.Jedis;

import java.sql.*;

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


    public static void main(String[] args) {
        queryWriteRedisDemo();
//        queryDemo();
    }
}
package com.hao.common.protos;

import ads.DmpService;
import cm.CommonTypes;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.Base64;


public class ProtosTest {
    public static void testProtosTest() throws InvalidProtocolBufferException {
        ByteString text = ByteString.copyFromUtf8("用户");
        CommonTypes.Keyword.Builder builder = CommonTypes.Keyword.newBuilder();
        builder.setText(text);
        builder.setKeywordId(1234456);
        builder.setTimestamp(0);
        builder.setWeight(0);
        System.out.println(builder.build());
        DmpService.PageAttributeResponse.Builder responseBuilder = DmpService.PageAttributeResponse.newBuilder();
        responseBuilder.addPageKeyword(builder.build());
        responseBuilder.addPageKeyword(builder.build());
//        responseBuilder.addPageKeyword(builder.build());
        System.out.println("toString" + responseBuilder.build().toString());
        System.out.println("toByteString" + responseBuilder.build());
        byte[] ret = responseBuilder.build().toByteArray();
        System.out.println(ret);
        String encoded = Base64.getEncoder().encodeToString(ret);
        System.out.println("encoded: " +  encoded);
        String strString = new String(ret);
        System.out.println("strString: " +  strString);
        DmpService.PageAttributeResponse response = DmpService.PageAttributeResponse.parseFrom(strString.getBytes());
        System.out.println(response);
        for(cm.CommonTypes.Keyword keyword: response.getPageKeywordList()){
            Long keywordId = keyword.getKeywordId();
            System.out.println(keywordId);
        }
    }
    public static void testRedisData(){
        //redis-cli -h 10.16.70.194 -p 6379 -a ER8YKWPm
        String redisHost = "10.16.70.194";
        int redisPort = 6379;
        Jedis jedis = new Jedis(redisHost, redisPort);
        // auth
        String redisPassword = "ER8YKWPm";
        jedis.auth(redisPassword);
        System.out.println("connect redis[" + redisHost + ": " + redisHost + "] ok");
        String keyStr = "ct_app_492012002";
        String keyStr1 = "ct_app_stop_492012002";
        byte[] ret = jedis.get(keyStr1.getBytes());
//        System.out.println(Arrays.toString(ret));
        try {
            DmpService.PageAttributeResponse response = DmpService.PageAttributeResponse.parseFrom(ret);
            System.out.println(response);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        System.out.println("******************************");
        byte[] redisValue = jedis.get(keyStr.getBytes());
        parseCTRedisInfo(redisValue);
    }

    public static void queryRedisInfo192(){
        //redis-cli -h 10.16.70.192 -p 6379 -a PEJlpxSiA2vg
        String redisHost = "10.16.70.192";
        int redisPort = 6379;
        Jedis jedis = new Jedis(redisHost, redisPort);
        String redisPassword = "PEJlpxSiA2vg";
        jedis.auth(redisPassword);
        System.out.println("connect redis[" + redisHost + ": " + redisHost + "] ok");
        String[] keyList = {"ct_news_stop_432338728", "ct_news_stop_432340255"};
        for(String key: keyList){
            System.out.println("-------------------------------------");
            System.out.println("key: " + key);
            byte[] redisValue = jedis.get(key.getBytes());
            parseCTRedisInfo(redisValue);
        }
        jedis.close();
    }

    public static void parseCTRedisInfo(byte[] redisValue){
        try {
            DmpService.PageAttributeResponse response = DmpService.PageAttributeResponse.parseFrom(redisValue);
            for(cm.CommonTypes.Keyword keyword: response.getPageKeywordList()){
                System.out.println(keyword.getKeywordId());
                System.out.println(keyword.getText().toStringUtf8());
            }
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        testProtosTest();
//        testRedisData();
        queryRedisInfo192();
    }
}

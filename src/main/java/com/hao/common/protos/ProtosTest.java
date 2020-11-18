package com.hao.common.protos;

import ads.DmpService;
import cm.CommonTypes;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import redis.clients.jedis.Jedis;

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
        DmpService.PageAttributeResponse response = DmpService.PageAttributeResponse.parseFrom(ret);
        System.out.println(response);
        for(cm.CommonTypes.Keyword keyword: response.getPageKeywordList()){
            Long keywordId = keyword.getKeywordId();
            System.out.println(keywordId);
        }
    }
    public static void testRedisData(){
        String redisHost = "127.0.0.1";
        int redisPort = 6379;
        Jedis jedis = new Jedis(redisHost, redisPort);
        // auth
        String redisPassword = "123456";
        jedis.auth(redisPassword);
        System.out.println("connect redis[" + redisHost + ": " + redisHost + "] ok");
    }
    public static void main(String[] args) throws InvalidProtocolBufferException {
        testProtosTest();
    }
}

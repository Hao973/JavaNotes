package com.hao.common.protos;

import ads.DmpService;
import cm.CommonTypes;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.*;


public class ProtosTest {
    public static void testProtosTest() throws InvalidProtocolBufferException {
        ByteString text = ByteString.copyFromUtf8("用户");
        CommonTypes.Keyword.Builder builder = CommonTypes.Keyword.newBuilder();
        builder.setText(text);
        builder.setKeywordId(1234456);
        long timeStamp = new Date().getTime();
        builder.setTimestamp(timeStamp);
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

    public static Jedis getRandomRedis(String redisConfInfo, String redisPassword){
        String[] redisConfList = redisConfInfo.split(",");
        int randomNum = (new Random()).nextInt(redisConfList.length);
        String randomConf = redisConfList[randomNum];
        String redisHost = randomConf.split(":")[0];
        int redisPort = Integer.parseInt(randomConf.split(":")[1]);
        Jedis jedis = new Jedis(redisHost, redisPort);
        System.out.println("connect redis[" + redisHost + ": " + redisPort + "] ok");
        jedis.auth(redisPassword);
        return jedis;
    }


    public static void queryRedisInfo194(){
        //redis-cli -h 10.16.70.192 -p 6379 -a PEJlpxSiA2vg
//        String redisHost = "10.16.70.192";
//        int redisPort = 6379;
//        Jedis jedis = new Jedis(redisHost, redisPort);
        String redisConfInfo = "10.16.70.194:6379,10.16.70.194:6379,10.16.70.194:6379,10.16.70.194:6379";
        String redisPassword = "ER8YKWPm";
        Jedis jedis = getRandomRedis(redisConfInfo, redisPassword);
        String[] keyList = {"ct_news_stop_test_428779059", "ct_app_stop_498600890"};
        for(String key: keyList){
            System.out.println("-------------------------------------");
            System.out.println("key: " + key);
            byte[] redisValue = jedis.get(key.getBytes());
            parseCTRedisInfo(redisValue);
        }
        jedis.close();
    }

    public static void queryRedisInfo(String redisConfInfo, String redisPassword, String[] keyList){
        Jedis jedis = getRandomRedis(redisConfInfo, redisPassword);
        for(String key: keyList){
            System.out.println("-------------------------------------");
            System.out.println("key: " + key);
            byte[] redisValue = jedis.get(key.getBytes());
            if(null == redisValue) {
                System.out.println("key: " + key + "is null");
                continue;
            }
            parseCTRedisInfo(redisValue);
        }
        jedis.close();
    }

    public static void queryRedisInfoV2(String redisConfInfo, String redisPassword, ArrayList<String> keyList){
        Jedis jedis = getRandomRedis(redisConfInfo, redisPassword);
        for(String key: keyList){
            System.out.println("-------------------------------------");
            System.out.println("key: " + key);
            byte[] redisValue = jedis.get(key.getBytes());
            if(null == redisValue) {
                System.out.println("key: " + key + "is null");
                continue;
            }
            parseCTRedisInfo(redisValue);
        }
        jedis.close();
    }

    public static void queryRedisCTNewsStopWordsInfoOnline(){
        String redisConfInfo = "10.18.70.87:20000,10.18.70.87:30000,10.18.70.88:20000,10.18.70.88:30000";
        String redisPassword = "YIw3IDWu";
        Jedis jedis = getRandomRedis(redisConfInfo, redisPassword);
        Map<String, String> ctNewsStopWordsMap = jedis.hgetAll("ct_news_stop_words");
        jedis.close();
        for (Map.Entry<String, String> entry : ctNewsStopWordsMap.entrySet()) {
          System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

    public static void queryRedisInfoOnlineV2(){
        String redisConfInfo = "10.18.70.87:20000,10.18.70.87:30000,10.18.70.88:20000,10.18.70.88:30000";
        String redisPassword = "YIw3IDWu";
//        String[] keyList = {"ct_news_stop_433929513", "ct_app_stop_498727841", "ct_news_stop_433930279", "ct_news_stop_433936734"};
//        String[] keyList = {"ct_news_stop_432446338", "ct_news_432446338"};
//        ProtosTest.queryRedisInfo(redisConfInfo, redisPassword, keyList);
        String[] docIDs = {"430221262", "429931352", "430739347", "431843754", "430782364", "432446338", "433338705"};
        ArrayList<String> keyList = new ArrayList<>();
        for(String docId: docIDs){
            keyList.add("ct_news_stop_" + docId);
            keyList.add("ct_news_" + docId);
        }
        ProtosTest.queryRedisInfoV2(redisConfInfo, redisPassword, keyList);
    }

    public static void queryRedisInfo192(){
        String redisConfInfo = "10.16.70.192:6379,10.16.70.192:6379,10.16.70.192:6379,10.16.70.192:6379";
        String redisPassword = "PEJlpxSiA2vg";
        String[] keyList = {"ct_news_stop_434395535", "ct_app_stop_499274105", "ct_app_stop_499274977"};
        ProtosTest.queryRedisInfo(redisConfInfo, redisPassword, keyList);
    }

    public static void queryRedisInfoOnline(){
        String redisConfInfo = "10.18.70.87:20000,10.18.70.87:30000,10.18.70.88:20000,10.18.70.88:30000";
        String redisPassword = "YIw3IDWu";
        String[] keyList = {"ct_news_stop_434395535", "ct_app_stop_499274105", "ct_app_stop_499274977"};
        ProtosTest.queryRedisInfo(redisConfInfo, redisPassword, keyList);
    }


    public static void parseCTRedisInfo(byte[] redisValue){
        try {
            DmpService.PageAttributeResponse response = DmpService.PageAttributeResponse.parseFrom(redisValue);
            StringBuffer keywordInfo = new StringBuffer();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        long timeStamp= new Long(s);
            for(cm.CommonTypes.Keyword keyword: response.getPageKeywordList()){
//                System.out.println(keyword.getKeywordId() + ": ");
//                System.out.println(keyword.getText().toStringUtf8());
//                String dateInfo = keyword.getTimestamp();
                Date date = new Date(keyword.getTimestamp());
                String time= simpleDateFormat.format(date);
                keywordInfo.append(time).append(",")
                        .append(keyword.getKeywordId()).append(":")
                        .append(keyword.getText().toStringUtf8()).append("|");
            }
            System.out.println(keywordInfo.toString());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    public static void timeTest(){
        long time3 = new Date().getTime();
        System.out.println(time3);
        //s是String类型的时间戳
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        long timeStamp= new Long(s);
        Date date = new Date(time3);
        String time= simpleDateFormat.format(date);
        System.out.println(time);
    }

    public static void main(String[] args) throws Exception {
//        testProtosTest();
//        testRedisData();
        queryRedisInfo192();
        queryRedisInfoOnline();
//        queryRedisInfoOnlineV2();
//        queryRedisCTNewsStopWordsInfoOnline();
    }
}

package work;


import ads.DmpService;
import cm.CommonTypes;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Random;

public class ADPCTNewsStopFuncTest {
    final static String REDIS_KEY_PREFIX_CT_NEWS_STOP = "ct_news_stop_";
    final static String REDIS_KEY_PREFIX_CT_APP_STOP = "ct_app_stop_";
    final static int REDIS_TTL = 356 * 24 * 3600;
    final static String REDIS_CONF_INFO = "10.16.70.192:6379,10.16.70.192:6379,10.16.70.192:6379,10.16.70.192:6379";
    final static String REDIS_PASSWORD = "PEJlpxSiA2vg";

    public static Jedis getRandomRedis(String redisConfInfo, String redisPassword){
        String[] redisConfList = redisConfInfo.split(",");
        int randomNum = (new Random()).nextInt(redisConfList.length);
        String randomConf = redisConfList[randomNum];
        String redisHost = randomConf.split(":")[0];
        int redisPort = Integer.parseInt(randomConf.split(":")[1]);
        Jedis jedis = new Jedis(redisHost, redisPort);
//        System.out.println("connect redis[" + redisHost + ": " + redisPort + "] ok");
        jedis.auth(redisPassword);
        return jedis;
    }

    public static void testRedisDataDoubleWrite() throws InvalidProtocolBufferException {
        String redisDocIdKey = "ct_news_stop_test_428779059";
//        String[] newStopWords = {"-1243707204303623957|好,", "-7730154239353320099|B"};
        String[] newStopWords = {"-1243707204303623957|好,", "-7730154239353320099|B", "-8371696889271044179|代步", "3087204794252502570|出行"};
        Jedis jedis = getRandomRedis(REDIS_CONF_INFO, REDIS_PASSWORD);
        byte[] docIDStopWords = jedis.get(redisDocIdKey.getBytes());
        HashMap<String, String> redisStopWordsMap = new HashMap<>();
        DmpService.PageAttributeResponse.Builder responseBuilder = DmpService.PageAttributeResponse.newBuilder();
        if (null != docIDStopWords) {
            DmpService.PageAttributeResponse response = DmpService.PageAttributeResponse.parseFrom(docIDStopWords);
            System.out.println(response);
            for(cm.CommonTypes.Keyword keyword: response.getPageKeywordList()){
                Long keywordId = keyword.getKeywordId();
                ByteString text = keyword.getText();
                CommonTypes.Keyword.Builder builder = CommonTypes.Keyword.newBuilder();
                builder.setText(text);
                builder.setKeywordId(keywordId);
                System.out.println(keywordId);
                redisStopWordsMap.put(keywordId.toString(), keywordId.toString());
                responseBuilder.addPageKeyword(builder.build());
            }
            for(String newStopWord: newStopWords){
                String DelKeyWordHasHID = newStopWord.split("\\|")[0];
                String DelKeyWord = newStopWord.split("\\|")[1];
                if (redisStopWordsMap.containsKey(DelKeyWordHasHID)) {
                    continue;
                }
                ByteString text = ByteString.copyFromUtf8(DelKeyWord);
                CommonTypes.Keyword.Builder builder = CommonTypes.Keyword.newBuilder();
                builder.setText(text);
                builder.setKeywordId(Long.parseLong(DelKeyWordHasHID));
                responseBuilder.addPageKeyword(builder.build());
            }
        } else {
            for(String newStopWord: newStopWords){
                String DelKeyWordHasHID = newStopWord.split("\\|")[0];
                String DelKeyWord = newStopWord.split("\\|")[1];
                ByteString text = ByteString.copyFromUtf8(DelKeyWord);
                CommonTypes.Keyword.Builder builder = CommonTypes.Keyword.newBuilder();
                builder.setText(text);
                builder.setKeywordId(Long.parseLong(DelKeyWordHasHID));
                responseBuilder.addPageKeyword(builder.build());
            }
        }
        byte[] redisValue = responseBuilder.build().toByteArray();
        jedis.setex(redisDocIdKey.getBytes(), REDIS_TTL, redisValue);
        jedis.close();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        testRedisDataDoubleWrite();
    }
}

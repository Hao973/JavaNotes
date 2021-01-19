package work.work;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hao.http.HttpClientDemo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PageDetail {
    private static final String appKey = "newbrandcalculation";
    private static final String appSecrect = "16aef3df2b8bd674873efa8b1c52d9a5";
    private static final String PREFIX = "http://gateway.os.sohuno.com/mpdata";
    private static final String POSTFIX = "&appKey=" + appKey + "&sign=";

    /**
     * 获取文章详情接口
     * @param newsId
     * @param userId
     * @return
     */
    public static String apiNews(String newsId, String userId)
    {
        long currentTime = System.currentTimeMillis();

        /*String url = PREFIX + "/news/" + newsId +
                "?userId=" + userId + POSTFIX +
                sign +
                "&time=" + time;*/
        String url = PREFIX + "/news/" + newsId +
                "?userId=" + userId + POSTFIX +
                SignUtils.generateSignature2(appKey, appSecrect, currentTime) +
                "&time=" + Long.toString(currentTime);
//        String response = HttpUtils.getString(url, 3);
        System.out.println("url: " + url);
        return url;
    }

    public static void getUrlInfo(){
//        String[] newsIdAndUserID = {"133304156,134553", "436504910,99961087", "436507440,120065679",
//                "325707677,99999937", "431414291,120736667", "431295494,120851825", "425201929,120761663",
//        "386069808,120622052", "431816846,120866565", "432407437,120747338"};
        String[] newsIdAndUserID = {"279762464,120009451", "294215225,120026264", "320640243,575624", "366351267,120176868"};
        for(String info: newsIdAndUserID){
            String newsId = info.split(",")[0];
            String user_id = info.split(",")[1];
            apiNews(newsId, user_id);
        }
    }

    public static String getTimeInfo(long curTime){
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date(curTime));
    }

    public static void getNewsInfo(String newsId, String user_id){
        String newUrlInfo = apiNews(newsId, user_id);
        String result = HttpClientDemo.doGet(newUrlInfo);
//        System.out.println(result);
        MpNewsBean mpNewsInfo;
        try{
            mpNewsInfo = JSON.toJavaObject(JSONObject.parseObject(result), MpNewsBean.class);
//            System.out.println("newsID: " + mpNewsInfo.getId());
            String mpNewsInfoDetail = "newsID:" +  mpNewsInfo.getId() + "\t" +
                                       "PostTime:[" + getTimeInfo(mpNewsInfo.getPostTime()) + "," + mpNewsInfo.getPostTime() + "] \t" +
                                       "ModifiedTime:[" + getTimeInfo(mpNewsInfo.getModifiedTime()) + "," + mpNewsInfo.getModifiedTime() + "]\t" +
                                       "GatherTime:[" + getTimeInfo(mpNewsInfo.getGatherTime()) + "," + mpNewsInfo.getGatherTime() + "]";
            System.out.println(mpNewsInfoDetail);
        }catch(Exception e){
        }

    }
    public  static  void getAllNewsInfo(){
        String[] newsIdAndUserID = {"279762464,120009451", "294215225,120026264", "320640243,575624", "366351267,120176868"};
        for(String info: newsIdAndUserID){
            String newsId = info.split(",")[0];
            String user_id = info.split(",")[1];
            getNewsInfo(newsId, user_id);
        }
    }
    public static void main(String[] args) {
//        getUrlInfo();
        getAllNewsInfo();
    }
}

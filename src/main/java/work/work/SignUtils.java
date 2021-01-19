package work.work;

public class SignUtils {
    public final static String generateSignature2(String appKey, String secret, long time) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(appKey);
        stringBuilder.append(time);
        stringBuilder.append(secret);

        String codes = stringBuilder.toString();

        String sign = DigestSelfUtils.sha1Hex(codes);
        return sign;
    }
}

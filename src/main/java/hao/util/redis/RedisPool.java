package hao.util.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created with IntelliJ IDEA.
 * Explain:Redis连接池
 */
public final class RedisPool {
    //Redis服务器IP
    private final static String ADDR = "10.16.70.192";
    //Redis的端口号
    private final static Integer PORT = 6379;
    //Redis服务器列表
    private final static String REDIS_HOSTS = "10.16.70.190:6379,10.16.70.191:6379,10.16.70.192:6379,10.16.70.193:6379,10.16.70.194:6379";
    //访问密码
    private final static String AUTH = "PEJlpxSiA2vg";

    //可用连接实例的最大数目，默认为8；
    //如果赋值为-1，则表示不限制，如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)
    private final static Integer MAX_TOTAL = 1024;
    //控制一个pool最多有多少个状态为idle(空闲)的jedis实例，默认值是8
    private final static Integer MAX_IDLE = 200;
    //等待可用连接的最大时间，单位是毫秒，默认值为-1，表示永不超时。
    //如果超过等待时间，则直接抛出JedisConnectionException
    private final static Integer MAX_WAIT_MILLIS = 10000;
    private final static Integer TIMEOUT = 10000;
    //在borrow(用)一个jedis实例时，是否提前进行validate(验证)操作；
    //如果为true，则得到的jedis实例均是可用的
    private final static Boolean TEST_ON_BORROW = true;
    private static JedisPool jedisPool = null;

    /*
     * 静态块，初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
        /*注意：
            在高版本的jedis jar包，比如本版本2.9.0，JedisPoolConfig没有setMaxActive和setMaxWait属性了
            这是因为高版本中官方废弃了此方法，用以下两个属性替换。
            maxActive  ==>  maxTotal
            maxWait==>  maxWaitMillis
         */
            config.setMaxTotal(MAX_TOTAL);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT_MILLIS);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config,ADDR,PORT,TIMEOUT,AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public static HashMap<String, Integer> getRedisHost(String redisServers){
//        String[] serversList = redisServers.split(",");
//        int serversLen = serversList.length;
//        ArrayList<HashMap<String, Integer>> redisHost = new ArrayList<HashMap<String, Integer>>();
//        for(String server: serversList){
//            System.out.println(server);
//            String[] hostPort = server.split(":");
//            Map<String, nt> map = new
//            redisHost.add(new HashMap<String, Integer>("host", hostPort[0]));
//        }
//        Random rand = new Random(serversLen);
//        int index = rand.nextInt();
//        return redisHost.get(index);
//    }

    /*
     * 获取Jedis实例
     * @return
     */
    public synchronized static Jedis getJedis(){
        try {
            if(jedisPool != null){
                return jedisPool.getResource();
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void returnResource(final Jedis jedis){
        //方法参数被声明为final，表示它是只读的。
        if(jedis!=null){
            jedisPool.returnResource(jedis);
            //jedis.close()取代jedisPool.returnResource(jedis)方法将3.0版本开始
            //jedis.close();
        }
    }

    /*
     * Redis操作字符串
     */
    public static void testString(Jedis jedis) {
        System.out.println("Redis String");
        //添加数据
        jedis.set("name", "chx"); //key为name放入value值为chx
        System.out.println("拼接前:" + jedis.get("name"));//读取key为name的值

        //向key为name的值后面加上数据 ---拼接
        jedis.append("name", " is my name;");
        System.out.println("拼接后:" + jedis.get("name"));

        //删除某个键值对
        jedis.del("name");
        System.out.println("删除后:" + jedis.get("name"));

        //s设置多个键值对
        jedis.mset("name", "chenhaoxiang", "age", "20", "email", "chxpostbox@outlook.com");
        jedis.incr("age");//用于将键的整数值递增1。如果键不存在，则在执行操作之前将其设置为0。 如果键包含错误类型的值或包含无法表示为整数的字符串，则会返回错误。此操作限于64位有符号整数。
        System.out.println(jedis.get("name") + " " + jedis.get("age") + " " + jedis.get("email"));
    }

    public static void main(String[] args) {
        Jedis jedis = RedisPool.getJedis();
        if (jedis != null){
            RedisPool.testString(jedis);
        }
    }
}

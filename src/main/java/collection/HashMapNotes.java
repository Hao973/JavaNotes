package collection;

import java.util.HashMap;

public class HashMapNotes {
    public static void HashMapValues(){
        // 创建一个 HashMap
        HashMap<Integer, String> sites = new HashMap<>();

        // 往 HashMap 添加一些元素
        sites.put(1, "Google");
        sites.put(2, "Runoob");
        sites.put(3, "Taobao");
        System.out.println("sites HashMap: " + sites);

        // 返回所有value值组成的视图
        System.out.println("Values: " + sites.values());
        System.out.println("Values Split: " + String.join(",", sites.values()));
    }
    public static void main(String[] args) {
        HashMapValues();
    }
}

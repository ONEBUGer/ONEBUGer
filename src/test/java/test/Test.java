package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZhengChangBing
 * @Date 2022/8/15 14:01
 * @Description
 */
public class Test {

    public static void main(String[] args) {

        String str = "imagebase64=/9j/4AAQSkZJRgABAQAAAQABAAD/W2g==";
        str = str.replaceFirst("=",":::");
        System.out.println(str);
        String[] split = str.split(":::");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(split[0], split[1]);
        System.out.println(hashMap);

        String str1 = "/carImage/1.jpg";
        String s = str1.replaceAll("/", "//");
        System.out.println(s);

        String s1 = "/carImage//carImage/1.jpg";
        System.out.println(s1.substring(10));
    }
}

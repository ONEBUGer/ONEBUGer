package base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * @author: 程彬彬
 * @date: 2018.11.20
 * Describe:
 */
@Component
public class Base64Image {

    /**
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param imagePath 图片地址
     * @return base64值
     */
    public static String GetImageStr(String imagePath) {
        File file = new File(imagePath);
        if (!file.exists()) {
            return null;
        }
        InputStream in = null;
        byte[] data = null;
        try {
            /* 读取图片字节数组 */
            in = new FileInputStream(imagePath);
            data = new byte[in.available()];
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != in) {
                    in.read(data);
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /* 返回Base64编码过的字节数组字符串 */
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

//    public static String GetImageStr(String imagePath) {
//        InputStream inputStream = ImgUtil.getInputStreamOfUrl(imagePath);
//        if (Objects.isNull(inputStream)) {
//            return null;
//        }
//        byte[] data = null;
//        try {
//            /* 读取图片字节数组 */
//            data = new byte[inputStream.available()];
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != inputStream) {
//                    inputStream.read(data);
//                    inputStream.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        /* 返回Base64编码过的字节数组字符串 */
//        BASE64Encoder encoder = new BASE64Encoder();
//        return encoder.encode(data);
//    }


    /**
     * 对字节数组字符串进行Base64解码并生成图片
     *
     * @param outPath   输出地址
     * @param base64Str 图片base64字符串
     */
    public static void generateImage(String outPath, String imageName, String base64Str) {
        File file = new File(outPath);
        if (!file.exists()) {
            if (file.mkdirs()) ;
        }
        /* Base64解码 */
        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream out = null;
        try {
            byte[] b = decoder.decodeBuffer(base64Str + imageName);
            for (int i = 0; i < b.length; ++i) {
                /* 调整异常数据 */
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            /* 新生成的图片 */
            out = new FileOutputStream(outPath + imageName);
            out.write(b);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 保存base64文本
     *
     * @param outPath   输出地址
     * @param base64Str 图片base64字符串
     */
    public static void saveBase64(String outPath, String base64Str) {
        File file = new File(outPath, "utf-8");
        if (!file.exists()) {
            if (file.mkdirs()) {

            }
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(outPath);
            fw.write(base64Str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Value("${web.service-url:D}")
    private static String str;
    public static void main(String[] args) {
        String fileName = "\\carImage\\31\\58_50176.jpg";
        String s = GetImageStr(str + fileName);
        System.out.println(s);
    }
}

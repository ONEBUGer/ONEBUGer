package base64;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: 陈康
 * @Date: 2022/4/19 17:49
 * @Description：图片处理的相关工具
 */
@Slf4j
public class ImgUtil {

    /**
     * 得到图片的输入流数据
     * @param imageUrl 图片的url地址
     */
    public static InputStream getInputStreamOfUrl(String imageUrl) {
        // 服务器返回的状态
        int httpResult;
        try {
            URL url = new URL(imageUrl);
            // 连接URL并取得返回状态
            URLConnection urlConn = url.openConnection();
            HttpURLConnection httpUrlConnection = (HttpURLConnection) urlConn;
            httpResult = httpUrlConnection.getResponseCode();
            // 只有状态为200才说明连接成功
            if (httpResult == HttpURLConnection.HTTP_OK) {
                return urlConn.getInputStream();
            }
        } catch (IOException e) {
            log.error("获取图片输入流失败，图片地址：{}", imageUrl, e);
        }
        return null;
    }

}

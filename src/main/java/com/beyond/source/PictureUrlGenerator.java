package com.beyond.source;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author beyondlov1
 * @date 2018/11/18
 */
public class PictureUrlGenerator implements Generator<String> {

    public String generate() {
        String pageUrl = "https://github.com/Alvin9999/new-pac/wiki/ss%E5%85%8D%E8%B4%B9%E8%B4%A6%E5%8F%B7";
        HttpGet httpGet = new HttpGet(pageUrl);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200){
                InputStream content = response.getEntity().getContent();
                String pageContent = IOUtils.toString(content, Charset.defaultCharset());
                Pattern pattern = Pattern.compile("(https://raw.githubusercontent.com/Alvin9999/PAC/master/ss/.*)\" alt=\"\"></p>");
                Matcher matcher = pattern.matcher(pageContent);
                if (matcher.find()){
                    return matcher.group(1);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("地址獲取不到");
    }

}

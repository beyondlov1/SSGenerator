package com.beyond.race;

import com.beyond.entity.SSConfigEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.Callable;

/**
 * @author beyondlov1
 * @date 2018/11/20
 */
public class SSRunner implements  Callable{
    private SSConfigEntity config;

    public SSConfigEntity getConfig() {
        return config;
    }

    public void setConfig(SSConfigEntity config) {
        this.config = config;
    }

    public Object call() throws Exception {
        long start = System.currentTimeMillis();
        connectGoogle();
        long end = System.currentTimeMillis();
        return end-start;
    }

    private void connectGoogle() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpGet实例
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        //设置代理IP，设置连接超时时间 、 设置 请求读取数据的超时时间 、 设置从connect Manager获取Connection超时时间、
        HttpHost proxy = new HttpHost("61.138.33.20",808);
        RequestConfig requestConfig = RequestConfig.custom()
                .setProxy(proxy)
                .setConnectTimeout(10000)
                .setSocketTimeout(10000)
                .setConnectionRequestTimeout(3000)
                .build();
        httpGet.setConfig(requestConfig);
        //设置请求头消息
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        CloseableHttpResponse response = httpClient.execute(httpGet);

        if (response != null){
            HttpEntity entity = response.getEntity();  //获取返回实体
            if (entity != null){
                System.out.println("网页内容为:"+ EntityUtils.toString(entity,"utf-8"));
            }
        }
        if (response != null){
            response.close();
        }
        httpClient.close();
    }

    public static void main(String[] args) {
        SSRunner ssRunner = new SSRunner();
        try {
            ssRunner.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

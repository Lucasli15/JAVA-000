package com.example.demo;


import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {
    public static void main(String[] args) {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 1、创建Get请求
        HttpGet httpGet = new HttpGet("http://localhost:8801");
        // 2、响应模型
        CloseableHttpResponse response = null;
        try {
            // 3、发送GET请求
            response = httpClient.execute(httpGet);
            // 4、获取返回数据
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                System.out.println("返回内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5、释放资源
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

package com.leezhong.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HttpClientWithProxy {
    String url = "http://www.yxinbao.com";
    String ip = "193.19.135.235";
    int port = 53281;
    String username = "";
    String password = "";

    public static void main(String[] args) {
        try {
            new HttpClientWithProxy().test1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用HttpClient4实现代理 202.107.233.85 8080
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        HttpClientBuilder build = HttpClients.custom();
        HttpHost proxy = new HttpHost(ip, port);
        CloseableHttpClient client = build.setProxy(proxy).build();
        HttpPost request = new HttpPost(url);
        CloseableHttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        System.out.println(EntityUtils.toString(entity,"utf-8"));
    }

    /**
     * 使用httpclient3实现代理
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        HttpClient httpClient = new HttpClient();
        httpClient.getHostConfiguration().setProxy(ip, port);
        GetMethod method = new GetMethod(url);
        httpClient.executeMethod(method);
        String result = new String(method.getResponseBody());
        System.out.println(result);
    }

    /**
     * 使用httpclient4实现代理(带密码的代理)
     *
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        HttpClientBuilder build = HttpClients.custom();
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        AuthScope authscope = new AuthScope(ip, port);
        Credentials credentials = new UsernamePasswordCredentials(username,
                password);
        credentialsProvider.setCredentials(authscope, credentials);
        CloseableHttpClient client = build.setDefaultCredentialsProvider(
                credentialsProvider).build();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        System.out.println(EntityUtils.toString(entity));
    }

    /**
     * 使用httpclient3实现代理(带密码的代理)
     *
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        HttpClient httpClient = new HttpClient();
        org.apache.commons.httpclient.auth.AuthScope authscope = new org.apache.commons.httpclient.auth.AuthScope(
                ip, port);
        org.apache.commons.httpclient.Credentials credentials = new org.apache.commons.httpclient.UsernamePasswordCredentials(
                username, password);
        httpClient.getState().setProxyCredentials(authscope, credentials);
        GetMethod method = new GetMethod(url);
        httpClient.executeMethod(method);
        String result = new String(method.getResponseBody());
        System.out.println(result);
    }

    /**
     * 模拟登录官网（http://mis.pyc.com.cn�?
     *
     * @throws Exception
     */
    @Test
    public void testLogin() throws Exception {
        HttpClientBuilder build = HttpClients.custom();
        CloseableHttpClient client = build.build();
        HttpPost post = new HttpPost("http://mis.pyc.com.cn/login.aspx");
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("__VIEWSTATE",
                "/wEPDwUJNjUwNzE0MTM4ZGQzh+vF2xGjdG8Q15kIqgR0CpxhmPucdCqZOPcglRZr/w=="));
        params.add(new BasicNameValuePair(
                "__EVENTVALIDATION",
                "/wEWBQLYtKSdCALEhISFCwKd+7qdDgKC3IeGDAK7q7GGCOqhJpRD8S8yy3ZAlPTSsmPzRUoXMK0mQvGgzlk6hm+G"));
        params.add(new BasicNameValuePair("txtName", "xxxxx"));
        params.add(new BasicNameValuePair("txtPwd", "xxxxxx"));
        params.add(new BasicNameValuePair("btnLogin", "xxxx"));
        HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
        post.setEntity(entity);
        CloseableHttpResponse response = client.execute(post);
        int statusCode = response.getStatusLine().getStatusCode();
        System.err.println("状态" + statusCode);
        if (statusCode == 302) {
            Header[] location = response.getHeaders("location");
            String rediretUrl = null;
            if (location.length == 1) {
                rediretUrl = "http://mis.pyc.com.cn" + location[0].getValue();
                System.err.println("跳转地址: " + rediretUrl);
            }
            Header[] allHeaders = response.getAllHeaders();
            System.out.println("==================response===================");
            for (Header header : allHeaders) {
                System.err.println(header.getName() + ": " + header.getValue());
            }
            Header cookieHeader = response.getFirstHeader("Set-Cookie");
            String cookie = cookieHeader.getValue();
            System.out.println("cookie: " + cookie);
            HttpGet httpGet = new HttpGet(rediretUrl);
            httpGet.addHeader("Accept",
                    "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            // httpGet.addHeader("Accept-Encoding", "gzip, deflate, sdch");
            // httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
            httpGet.addHeader("Connection", "keep-alive");
            httpGet.addHeader("Cookie", cookie);
            httpGet.addHeader("Host", "mis.pyc.com.cn");
            httpGet.addHeader("Referer", "http://mis.pyc.com.cn/login.aspx");
            httpGet.addHeader(
                    "User-Agent",
                    "ozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.115 Safari/537.36");
            response = client.execute(httpGet);
            HttpEntity entity2 = response.getEntity();
            System.out
                    .println("----------------------------------------------");
            System.out.println(EntityUtils.toString(entity2));
        }
    }
}

package com.moses.cloud.commons.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.util.CollectionUtils;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.*;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author HanKeQi
 * @Date 2021/2/2 下午4:26
 * @Version 1.0
 **/
public class HttpClientUtils {


    public static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

    public static final String APPLICATION_JSON = "application/json";

    private static final String CHARTSET = "UTF-8";
    private static final int CONNTIMEOUT = 30000;
    private static final int READTIMEOUT = 30000;
    private static CloseableHttpClient httpClient = null;

    static {
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory ssl = SSLConnectionSocketFactory.getSocketFactory();

        // SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
        // public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        // return true;
        // }
        // }).build();
        // SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", plainsf)
                .register("https", ssl).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        // 将最大连接数增加到200 根据业务调整
        cm.setMaxTotal(200);
        // 将每个路由基础的连接增加到20 根据业务调整
        cm.setDefaultMaxPerRoute(20);

        // 请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = (exception, executionCount, context) -> {
            if (executionCount >= 5) {// 如果已经重试了5次，就放弃
                return false;
            }
            if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                return true;
            }
            if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                return false;
            }
            if (exception instanceof InterruptedIOException) {// 超时
                return false;
            }
            if (exception instanceof UnknownHostException) {// 目标服务器不可达
                return false;
            }
            if (exception instanceof SSLException) {// ssl握手异常
                return false;
            }

            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            // 如果请求是幂等的，就再次尝试
            if (!(request instanceof HttpEntityEnclosingRequest)) {
                return true;
            }
            return false;
        };

        httpClient = HttpClients.custom().setConnectionManager(cm).setRetryHandler(httpRequestRetryHandler).build();
    }


    public static <T> T postJson(String url, Map<String, Object> params, Map<String, String> headers, Class<?> clazz) throws Exception {
        String json = postJson(url, params, headers, CONNTIMEOUT, READTIMEOUT);
        return (T) JsonUtils.parseObject(json, clazz);
    }

    public static String postJson(String url, Map<String, Object> params) throws Exception {
        return postJson(url, params, null, CONNTIMEOUT, READTIMEOUT);
    }

    public static String postJson(String url, Map<String, Object> params, Map<String, String> headers) throws Exception {
        return postJson(url, params, headers, CONNTIMEOUT, READTIMEOUT);
    }

    /**
     * Post请求
     *
     * @throws Exception
     */
    public static String postPlain(String url, String body, Map<String, String> headers, String mimeType, String charset, Integer connTimeout, Integer readTimeout)
            throws Exception {
        HttpPost post = new HttpPost(url);
        String result = null;
        try {
            if (StringUtils.isNotBlank(body)) {
                StringEntity entity = new StringEntity(body, StandardCharsets.UTF_8);
                post.setEntity(entity);
            }

            if (!CollectionUtils.isEmpty(headers)) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    post.addHeader(entry.getKey(), entry.getValue());
                }
            }
            RequestConfig customReqConfig = getCustomReqConfig(connTimeout, readTimeout);
            post.setConfig(customReqConfig);
            HttpResponse res = httpClient.execute(post);
            result = convertInputStreamToString(res.getEntity().getContent(), charset);
        } finally {
            post.releaseConnection();
        }
        return result;
    }

    public static <T> T postForm(String url, Map<String, String> params, Map<String, String> headers, Class<?> clazz) throws Exception {
        String json = postForm(url, params, headers, CONNTIMEOUT, READTIMEOUT, Charset.defaultCharset());
        return (T) JsonUtils.parseObject(json, clazz);
    }

    /**
     * 提交form表单
     *
     * @param url
     * @throws Exception
     */
    public static String postForm(String url, Map<String, String> params, Map<String, String> headers, Integer connTimeout,
                                  Integer readTimeout, Charset charset) throws Exception {
        HttpPost post = new HttpPost(url);
        try {
            if (!CollectionUtils.isEmpty(params)) {
                List<NameValuePair> formParams = new ArrayList<>();
                Set<Map.Entry<String, String>> entrySet = params.entrySet();
                for (Map.Entry<String, String> entry : entrySet) {
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, charset);
                post.setEntity(entity);
            }
            if (!CollectionUtils.isEmpty(headers)) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    post.addHeader(entry.getKey(), entry.getValue());
                }
            }
            RequestConfig customReqConfig = getCustomReqConfig(connTimeout, readTimeout);
            post.setConfig(customReqConfig);
            HttpResponse res = httpClient.execute(post);
            return convertInputStreamToString(res.getEntity().getContent(), CHARTSET);
        } finally {
            post.releaseConnection();
        }
    }

    /**
     * 提交postJson
     *
     * @param url
     * @throws Exception
     */
    public static String postJson(String url, Map<String, Object> params, Map<String, String> headers, Integer connTimeout, Integer readTimeout) throws Exception {
        HttpPost post = new HttpPost(url);
        try {
            if (!CollectionUtils.isEmpty(params)) {
                StringEntity entity = new StringEntity(JsonUtils.toJSONString(params), CHARTSET);
                entity.setContentEncoding(CHARTSET);
                post.setEntity(entity);
            }
            if (!CollectionUtils.isEmpty(headers)) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    post.addHeader(entry.getKey(), entry.getValue());
                }
            }
            RequestConfig customReqConfig = getCustomReqConfig(connTimeout, readTimeout);
            post.setConfig(customReqConfig);
            HttpResponse res = httpClient.execute(post);
            return convertInputStreamToString(res.getEntity().getContent(), CHARTSET);
        } finally {
            post.releaseConnection();
        }
    }



    /*********************************************get req and  config****************************************************************************/

    public static <T> T getForObject(String url, Map<String, String> headers, Class<T> toClass) throws Exception {
        String res = get(url, CHARTSET, headers, null, null);
        return JsonUtils.parseObject(res, toClass);
    }

    /**
     * get 请求
     * @param url url
     * @return  {@link String}
     * @throws Exception
     */
    public static String get(String url) throws Exception {
        return get(url,  CHARTSET, null, CONNTIMEOUT, READTIMEOUT);
    }


    /**
     * get 请求
     * @param url  url
     * @param charset 编码
     * @return  {@link String}
     * @throws Exception
     */
    public static String get(String url, String charset) throws Exception {
        return get(url, charset, null, CONNTIMEOUT, READTIMEOUT);
    }


    /**
     * get 请求
     * @param url  url
     * @param charset 编码
     * @param headers 请求头
     * @return  {@link String}
     * @throws Exception
     */
    public static String get(String url, String charset, Map<String, String> headers) throws Exception {
        return get(url, charset, null, CONNTIMEOUT, READTIMEOUT);
    }

    /**
     * get 请求
     * @param url  url
     * @param charset 编码
     * @param connTimeout 连接超时时间
     * @param readTimeout 请求成功后读取超时时间
     * @return  {@link String}
     * @throws Exception
     */
    public static String get(String url, String charset, Map<String, String> headers, Integer connTimeout, Integer readTimeout) throws Exception {
        HttpGet get = new HttpGet(url);
        String result = "";
        try {
            if (!CollectionUtils.isEmpty(headers)) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    get.addHeader(entry.getKey(), entry.getValue());
                }
            }
            RequestConfig customReqConfig = getCustomReqConfig(connTimeout, readTimeout);
            get.setConfig(customReqConfig);
            HttpResponse res = httpClient.execute(get);
            result = convertInputStreamToString(res.getEntity().getContent(), charset);

        } finally {
            get.releaseConnection();
        }
        return result;
    }

    private static RequestConfig getCustomReqConfig(Integer connTimeout, Integer readTimeout) {
        RequestConfig.Builder customReqConf = RequestConfig.custom();
        if (connTimeout != null) {
            customReqConf.setConnectTimeout(connTimeout);
        }
        if (readTimeout != null) {
            customReqConf.setSocketTimeout(readTimeout);
        }
        return customReqConf.build();
    }

    private static String convertInputStreamToString(InputStream inputStream, String charset) throws UnsupportedEncodingException,
            IOException {
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset); BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String str = null;
            StringBuilder buffer = new StringBuilder();
            boolean flag = Boolean.FALSE.booleanValue();
            while ((str = bufferedReader.readLine()) != null) {
                if (flag) {
                    buffer.append(System.getProperty("line.separator"));
                } else {
                    flag = Boolean.TRUE;
                }
                buffer.append(str);
            }
            return buffer.toString();
        }
    }
}

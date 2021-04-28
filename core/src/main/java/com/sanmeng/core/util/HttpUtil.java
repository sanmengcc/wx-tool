package com.sanmeng.core.util;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * http网络请求工具类
 */
public class HttpUtil {

    /**
     * post payload方式请求
     */
    public static byte[] payload(String url, String content) {
        return getResponseStream(sendPost(url, content));
    }

    /**
     * post payload方式请求
     */
    public static byte[] post(String url, String content, Map<String, String> header) {
        return getResponseStream(sendPost(url, content, header));
    }

    /**
     * post form表单
     */
    public static String post(String url, Map<String, String> parameters) {
        String content = generatorParamString(parameters);
        return getResponseBody(sendPost(url, content));
    }

    /**
     * post form表单
     */
    public static String post(String url, Map<String, String> parameters,Map<String, String> header) {
        String content = generatorParamString(parameters);
        return getResponseBody(sendPost(url, content, header));
    }

    /**
     * 完整路径
     */
    public static String get(String url) {
        return get(url, null, null);
    }

    /**
     * 带参数的GET请求
     * @param url
     * @param param
     * @return
     */
    public static String get(String url,Map<String, String> param) {
        return get(url, param, null);
    }

    /**
     * 带参数的GET请求
     * @param url
     * @param param
     * @return
     */
    public static String get(String url,Map<String, String> param,Map<String, String> header) {
        return sendGet(url, param, header);
    }

    /**
     * 完整路径
     */
    public static byte[] getPayload(String url) {
        return sendGetStream(url, null, null);
    }

    /**
     * 带参数的GET请求
     * @param url
     * @param param
     * @return
     */
    public static byte[] getPayload(String url,Map<String, String> param) {
        return sendGetStream(url, param, null);
    }

    /**
     * 带参数的GET请求
     * @param url
     * @param param
     * @return
     */
    public static byte[] getPayload(String url,Map<String, String> param,Map<String, String> header) {
        return sendGetStream(url, param, header);
    }

    /**
     * 获取响应体
     * @param urlConn
     * @return
     */
    private static String getResponseBody(HttpURLConnection urlConn) {
        try {
            String responseContent = null;
            InputStream in = urlConn.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String tempLine = rd.readLine();
            StringBuffer tempStr = new StringBuffer();
            String crlf = System.getProperty("line.separator");
            while (tempLine != null) {
                tempStr.append(tempLine);
                tempStr.append(crlf);
                tempLine = rd.readLine();
            }
            responseContent = tempStr.toString();
            rd.close();
            in.close();
            return responseContent;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 获取响应的流
     * @param urlConn
     * @return
     */
    private static byte[] getResponseStream(HttpURLConnection urlConn) {
        try {
            InputStream inputStream = urlConn.getInputStream();
            //不要使用input来读取数组
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int len = -1;
            byte[] data = new byte[1024];
            while ((len = inputStream.read(data)) != -1) {
                out.write(data, 0, len);
            }
            if(inputStream!=null){
                inputStream.close();
            }
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 发送字符串的post请求
     * @param url
     * @param content
     * @return
     */
    private static HttpURLConnection sendPost(String url, String content) {
        return sendPost(url, content, null);
    }

    /**
     * 发送get请求
     * @param url
     * @param param
     * @param header
     * @return
     */
    private static String sendGet(String url, Map param, Map<String, String> header) {
        try {
            URL httpUrl = new URL(url + generatorParamString(param));
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int i = 0; (i = in.read(buf)) > 0; ) {
                out.write(buf, 0, i);
            }
            out.flush();
            return new String(out.toByteArray(), "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static byte[] sendGetStream(String url, Map param, Map<String, String> header) {
        try {
            String spec = url + generatorParamString(param);
            System.out.println(spec);
            URL httpUrl = new URL(spec);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int i = 0; (i = in.read(buf)) > 0; ) {
                out.write(buf, 0, i);
            }
            out.flush();
            System.out.println(new String(out.toByteArray(), "UTF-8"));
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 发送字符串的post请求携带请求头
     * @param url
     * @param content
     * @param header
     * @return
     */
    private static HttpURLConnection sendPost(String url, String content, Map<String, String> header) {
        HttpURLConnection urlConn = null;
        try {
            URL httpUrl = new URL(url);
            urlConn = (HttpURLConnection) httpUrl.openConnection();
            urlConn.setRequestMethod("POST");
            urlConn.setConnectTimeout(50000);
            urlConn.setReadTimeout(50000);
            if (Objects.nonNull(header) && !header.isEmpty()) {
                for (String h : header.keySet()) {
                    urlConn.setRequestProperty(h, header.get(h));
                }
            }
            urlConn.setDoOutput(true);
            byte[] b = content.getBytes();
            urlConn.getOutputStream().write(b);
            urlConn.getOutputStream().flush();
            urlConn.getOutputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }
        return urlConn;
    }

    /**
     将parameters中数据转换成用"&"链接的http请求参数形式
     * @param parameters
     * @return
     */
    private static String generatorParamString(Map<String, String> parameters) {
        StringBuffer params = new StringBuffer();
        if (parameters != null) {
            for (Iterator<String> iter = parameters.keySet().iterator(); iter.hasNext(); ) {
                String name = iter.next();
                String value = parameters.get(name);
                params.append(name + "=");
                try {
                    params.append(value);
                } catch (Exception e) {
                    String message = String.format("'%s'='%s'", name, value);
                    throw new RuntimeException(message, e);
                }
                if (iter.hasNext()) {
                    params.append("&");
                }
            }
        }
        return "?" + params.toString();
    }


    /**
     用于生成升序排列的请求参数字符串（支付回调常用的方法）
     * @param params
     * @param sign
     * @return
     */
    public static String sortParam(Map<String, String> params, String sign) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            if (sign.equals(key)) {
                continue;
            }
            String value = (String) params.get(key);
            if (value != null) {
                content.append((i == 0 ? "" : "&") + key + "=" + value);
            } else {
                content.append((i == 0 ? "" : "&") + key + "=");
            }
        }
        return content.toString();
    }

}
package com.ckong.schedule.utils.cqcet;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * 重庆电子工程职业学院的的HttpClient Response 响应处理类
 * @author kongzhiqing
 */
public class ResponseHandlerCq implements ResponseHandler<String> {

    private static final String FAIL_TITLE = "登录";
    private static final String CSS_QUERY = "head > title";

    /**
     * @param response 处理结果
     * @return 登录成功则返回html, 否则返回null
     * @throws ClientProtocolException 客户端协议异常
     * @throws IOException IO异常
     */
    @Override
    public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {

        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == HttpStatus.SC_OK) {
            String html = EntityUtils.toString(response.getEntity());
            Document document = Jsoup.parse(html);
            // 账号或者密码错误
            if (FAIL_TITLE.equals(document.select(CSS_QUERY).text())) {
                return null;
            } else {
                return html;
            }
        } else {
            return null;
        }
    }

}

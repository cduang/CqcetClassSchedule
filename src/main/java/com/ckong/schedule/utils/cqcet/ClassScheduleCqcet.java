package com.ckong.schedule.utils.cqcet;

import com.ckong.schedule.exceptions.GetClassScheduleNetWorkException;
import com.ckong.schedule.exceptions.UserNameOrPasswordException;
import com.ckong.schedule.utils.interfaces.IBaseGetClassSchedule;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

/**
 * 针对重庆电子工程职业学院智慧校园系统的实现
 * @author kongzhiqiang
 */
public class ClassScheduleCqcet implements IBaseGetClassSchedule {

    private String userName;
    private String password;
    private static final String LOGIN_URL = "http://sso.cqcet.edu.cn/login_process";
    private static final List<Header> HEADERS;
    private static final RequestConfig REQUEST_CONFIG;
    private static final String CLASS_SCHEDULE_URL = "http://42.247.8.142:8080/Logon.do?method=logonByCqdzzy&url=xsdxqllkb";
    private List<NameValuePair> formData = new ArrayList<>();

    static {

        HEADERS = new LinkedList<>();
        HEADERS.add(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9," +
                "image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3"));
        HEADERS.add(new BasicHeader("Accept-Encoding", "gzip, deflate"));
        HEADERS.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.9"));
        HEADERS.add(new BasicHeader("Cache-Control", "max-age=0"));
        HEADERS.add(new BasicHeader("Connection", "keep-alive"));
        HEADERS.add(new BasicHeader("Upgrade-Insecure-Requests", "1"));
        HEADERS.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36"));

        REQUEST_CONFIG = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.STANDARD)
                // 允许循环重定向
                .setCircularRedirectsAllowed(true)
                .build();
    }

    /**
     * 构造
     * @param userName 智慧校园系统用户名
     * @param password 系统密码
     */
    public ClassScheduleCqcet(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.formData.add(new BasicNameValuePair("username", userName));
        this.formData.add(new BasicNameValuePair("password", password));
    }

    @Override
    public String getClassSchedule() throws UserNameOrPasswordException, GetClassScheduleNetWorkException {

        // 请求课程表的httpClient
        CloseableHttpClient getClient = HttpClients.custom()
                .setDefaultHeaders(HEADERS)
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();

        CookieStore cookies = clientLogin();

        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(cookies);

        HttpGet getMethod = new HttpGet(CLASS_SCHEDULE_URL);
        String res;

        try (CloseableHttpResponse  response = getClient.execute(getMethod, context)) {
            res = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new GetClassScheduleNetWorkException("获取课表发生错误!", e);
        } finally {
            try {
                getClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return res;
    }

    /**
     * 登录智慧校园系统
     * @return 登录成功后的全部Cookie信息
     */
    private CookieStore clientLogin() throws UserNameOrPasswordException {


        // 进行登录操作的httpClient
        CloseableHttpClient loginClient = HttpClients.custom()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .setDefaultRequestConfig(REQUEST_CONFIG)
                .build();

        HttpClientContext context = HttpClientContext.create();

        try {

            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(this.formData, "utf-8");
            HttpPost firstPost = new HttpPost(LOGIN_URL);
            firstPost.setEntity(formEntity);
            // 设置默认请求头
            Header[] headers = new Header[HEADERS.size()];
            firstPost.setHeaders(HEADERS.toArray(headers));

            String response  = loginClient.execute(firstPost, new ResponseHandlerCq(), context);

            if (response == null) {
                throw new UserNameOrPasswordException("用户名: " + this.userName + " 或" + "密码: " + this.password + "错误!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                loginClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return context.getCookieStore();
    }

}

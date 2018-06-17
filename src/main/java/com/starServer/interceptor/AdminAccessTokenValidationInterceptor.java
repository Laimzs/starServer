package com.starServer.interceptor;

import com.starServer.entity.Admin;
import com.starServer.entity.ResponseData;
import com.starServer.util.TokenConfig;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 访问token校验拦截器。在校验成功后，会设置相应的请求属性。
 * <p>
 * <p>
 * 待校验的token及其设置的属性如下所示：
 * <ul>
 * <li>access token: jwt字符串，存储于HTTP请求头中，
 * 请求头的名字由应用参数"token.access-token.holder-name"指定，默认值为"accessToken"。
 * 验证成功后，会更新access token的最后访问时间， 并将用户标识写入到请求属性中，
 * 属性名为由应用参数"token.access-token.user-id-request-attribute-name"指定。</li>
 * </ul>
 */
public class AdminAccessTokenValidationInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(AdminAccessTokenValidationInterceptor.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("************:" + request.getRequestURI());
        if (request.getMethod().equals("OPTIONS") || request.getMethod().equals("options")) {
            ResponseData responseData = new ResponseData();
            responseData.jsonFill(2, null, true);
            ServletOutputStream outputStream = response.getOutputStream();
            Gson gson = new Gson();
            outputStream.write(gson.toJson(responseData).getBytes());
            outputStream.flush();
            outputStream.close();
            return false;
        }
        if (request.getRequestURI().equals("/manage/auth")) {
            return super.preHandle(request, response, handler);
        }
        this.checkLogin(request, response, handler);
        return super.preHandle(request, response, handler);
    }

    private boolean checkLogin(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String AccessToken = request.getHeader("Authorization");
        if (AccessToken == null) {
            logger.info(">>>>>>>>> 当前请求的AccessToken为空。>>>>>>>>>>");
            response.setStatus(401);
            throw new LoginException("登录失效");
        }
        try {
            Admin admin = (Admin) redisTemplate.opsForValue().get(AccessToken);
            if (admin == null) {
                logger.info(">>>>>>>>登录失效>>>>>>>>当前请求的AccessToken为：" + AccessToken + "。>>>>>>>>>>>");
                response.setStatus(401);
                throw new LoginException("登录失效");
            }
            request.setAttribute(TokenConfig.DEFAULT_USERID_REQUEST_ATTRIBUTE_NAME, admin);
        } catch (LoginException e) {
            throw e;
        } catch (Exception e) {
            logger.error(">>>>>>>> redis的访问出错 >>>>>>>>");
            logger.error("", e);
            response.setStatus(200);
            throw new Exception("服务器错误，验证权限失败。");
        }
        return true;
    }
}

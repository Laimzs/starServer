package com.starServer.interceptor;


import com.starServer.entity.User;
import com.starServer.util.JedisUtil;
import com.starServer.util.ObjectAndByte;
import com.starServer.util.TokenConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.Jedis;

import javax.security.auth.login.LoginException;
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
 *
 * @author caoxudong
 * @see
 */
public class AccessTokenValidationInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger(AccessTokenValidationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        this.checkLogin(request, response, handler);

        return super.preHandle(request, response, handler);
    }

    /**
     * 检验请求的登录状态
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    private boolean checkLogin(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String AccessToken = request.getHeader(TokenConfig.DEFAULT_ACCESS_TOKEN_HEADER_NAME);
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            byte[] bytes = jedis.get(AccessToken.getBytes());
            if (bytes == null) {
                response.setStatus(401);
                throw new LoginException("登录失效");
            } else {
                User user = (User) ObjectAndByte.toObject(bytes);
                user.setAccessToken(AccessToken);
                if (user == null) {
                    response.setStatus(401);
                    throw new LoginException("登录失效");
                } else {
                    request.setAttribute(TokenConfig.DEFAULT_USERID_REQUEST_ATTRIBUTE_NAME, user);
                }
            }

        } catch (Exception e) {
            response.setStatus(401);
/*            throw new LoginException("登录失效");*/
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return true;
    }


}

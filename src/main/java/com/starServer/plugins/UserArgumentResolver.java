package com.starServer.plugins;


import com.starServer.util.TokenConfig;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;

public class UserArgumentResolver implements WebArgumentResolver {
    @Override
    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest nativeWebRequest) throws Exception {
        if (methodParameter.getParameterType() != null
                && methodParameter.getParameterType().equals(User.class)) {
            HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
            Object user = request.getAttribute(TokenConfig.DEFAULT_USERID_REQUEST_ATTRIBUTE_NAME);
            return user;
        }
        return UNRESOLVED;
    }
}

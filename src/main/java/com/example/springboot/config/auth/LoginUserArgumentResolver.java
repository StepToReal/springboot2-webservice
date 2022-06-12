package com.example.springboot.config.auth;

import com.example.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver  implements HandlerMethodArgumentResolver {
    //HandlerMethodArgumentResolver - 조건 (supportsParameter) 에 맞는 메서드가 있다면 구현체가 지정한 값으로 parameter 넘길 수 있음

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        System.out.println("HandlerMethodArgumentResolver check method");

        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer
            , NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        return httpSession.getAttribute("user");
    }
}

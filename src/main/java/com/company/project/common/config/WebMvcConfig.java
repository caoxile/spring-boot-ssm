package com.company.project.common.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Spring MVC 配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);


    //使用阿里 FastJson Http的message转换工具
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue);//保留空的字段
        //SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
        //SerializerFeature.WriteNullNumberAsZero//Number null -> 0
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(converter);
    }


//    //统一异常处理
//    @Override
//    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//        exceptionResolvers.add((HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) ->{
//                Result result = new Result();
//                if(e instanceof IllegalArgumentException){
//                    //参数异常
//                    result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
//                    logger.info(e.getMessage());
//                } else if (e instanceof ServiceException) {
//                    //业务失败的异常，如“账号或密码错误”
//                    result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
//                    logger.info(e.getMessage());
//                } else if (e instanceof NoHandlerFoundException) {
//                    //接口不存在异常
//                    result.setCode(ResultCode.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
//                } else if (e instanceof ServletException) {
//                    result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
//                } else {
//                    result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
//                    String message;
//                    if (handler instanceof HandlerMethod) {
//                        HandlerMethod handlerMethod = (HandlerMethod) handler;
//                        message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
//                                request.getRequestURI(),
//                                handlerMethod.getBean().getClass().getName(),
//                                handlerMethod.getMethod().getName(),
//                                e.getMessage());
//                    } else {
//                        message = e.getMessage();
//                    }
//                    logger.error(message, e);
//                }
//                responseResult(response, result);
//                return new ModelAndView();
//        });
//    }

//    //服务器支持跨域
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("*");
//    }

//    /**
//     * 所有的HTTP请求都在这里拦截
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new HandlerInterceptorAdapter() {
//            @Override
//            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//                //验证JWT
//                boolean pass = validateAuthToken(request);
//                if (pass) {
//                    return true;
//                } else {
//                    logger.warn("JWT认证失败，请求接口：{}，请求IP：{}，请求参数：{}",
//                            request.getRequestURI(), HttpUtil.getIpAddress(request), JSON.toJSONString(request.getParameterMap()));
//
//                    Result result = new Result();
//                    result.setCode(ResultCode.UNAUTHORIZED).setMessage("认证失败");
//                    responseResult(response, result);
//                    return false;
//                }
//            }
//        }).excludePathPatterns("/auth/login");
//    }
//
//    private void responseResult(HttpServletResponse response, Result result) {
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-type", "application/json;charset=UTF-8");
//        response.setStatus(200);
//        try {
//            response.getWriter().write(JSON.toJSONString(result));
//        } catch (IOException ex) {
//            logger.error(ex.getMessage());
//        }
//    }
//
//    /**
//     * 校验Jwt
//     */
//    private boolean validateAuthToken(HttpServletRequest request) {
//        String token = request.getHeader(AuthConstants.HEADER_AUTH);
//        if (StringUtils.isEmpty(token)) {
//            return false;
//        }
//        return JwtTokenUtil.validateToken(token);
//    }

}

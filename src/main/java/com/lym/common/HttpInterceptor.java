package com.lym.common;

import com.lym.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/17 18:07
 * @Description: 请求前后监听工具
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {
    /**
     * 在请求之前
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURL().toString();
        Map parameterMap = request.getParameterMap();
        log.info("prehandler:url:{},params:{}", url, JsonMapper.obj2String(parameterMap));
        long start = System.currentTimeMillis();
        request.setAttribute("start", start);
        return true;
    }


    /**
     * 在一个请求正常结束之后
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String url = request.getRequestURL().toString();
        Map parameterMap = request.getParameterMap();
        long end = System.currentTimeMillis();
        long start = (long) request.getAttribute("start");
        long cost = end - start;
        log.info("postHandle:url:{},params:{}", url, JsonMapper.obj2String(parameterMap));
        log.info("cost:{}", cost);
    }

    /**
     * 在一个请求结束之后(包括异常结束)
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURL().toString();
        Map parameterMap = request.getParameterMap();
        log.info("afterCompletion:url:{},params:{}", url, JsonMapper.obj2String(parameterMap));
    }
}

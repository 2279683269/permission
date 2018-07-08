package com.lym.common;

import com.lym.Enum.ExceptionEnum;
import com.lym.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/8 22:41
 * @Description: 自定义异常处理类
 */
@Slf4j
public class SpringExceptionRosolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest Request, HttpServletResponse Response, Object handler, Exception e) {

        /**获取异常请求的URL*/
        String url = Request.getRequestURL().toString();
        ModelAndView mv;

        String DefaultMsg = ExceptionEnum.DEFAULT_MSG.errorDesc;

        /**获知请求是json请求还是页面请求
         * 方法1:现获取请求header,从header中可以得到
         * 方法2:事先规定,json请求以.json结尾,页面请求以.page结尾(当前项目规范)
         */
        if (url.endsWith(".json")) {
            if (e instanceof PermissionException) {
                JsonData result = JsonData.fail(e.getMessage());
                mv = new ModelAndView("jsonView", result.toMap());
            } else {
                log.error("unknow json exception,url:" + url + e);
                JsonData result = JsonData.fail(DefaultMsg);
                mv = new ModelAndView("jsonView", result.toMap());
            }
        } else if (url.endsWith(".page")) {
            if (e instanceof PermissionException) {
                JsonData result = JsonData.fail(e.getMessage());
                mv = new ModelAndView("exception", result.toMap());
            } else {
                log.error("unknow page exception,url:" + url + e);
                JsonData result = JsonData.fail(DefaultMsg);
                mv = new ModelAndView("exception", result.toMap());

            }
        } else {
            log.error("unknow exception,url:" + url + e);
            JsonData result = JsonData.fail(DefaultMsg);
            mv = new ModelAndView("jsonView", result.toMap());

        }


        return mv;
    }
}

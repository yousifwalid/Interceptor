 package com.example.interceptorTask.Configuration;

import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sender = request.getHeader("User-Agent");
        log.info("Before sending request to controller: {}", request.getRequestURI());

        if (sender != null) {
            //parse user agent
            UserAgent userAgent = UserAgent.parseUserAgentString(sender);
            //determine device type based on user agent
            DeviceType deviceType = userAgent.getOperatingSystem().getDeviceType();
            //set request attribute
            request.setAttribute("isMobile", deviceType);
            if (deviceType==DeviceType.MOBILE) {
                log.warn("Request" + request.getRequestURI() + " came from Mobile: {}", userAgent.getOperatingSystem().getName());
            } else {
                log.warn("Request:" + request.getRequestURI() + " came from the browser: {}", userAgent.getBrowser().getName()
                        + " with operating system:" + userAgent.getOperatingSystem().getName());
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("Before sending response to client");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("Request and Response is completed");
    }
}

//String sender = request.getHeader("X-Source");
//        log.info("Before sending request to controller: {}", request.getRequestURI());
//
//        if (sender != null) {
//        if ("mobile".equalsIgnoreCase(sender)) {
//        log.warn("Request from Mobile: {}", request.getRequestURI());
//        } else if ("web".equalsIgnoreCase(sender)) {
//        log.warn("Request from Web browser: {}", request.getRequestURI());
//        } else {
//        log.error("Request from an unknown source");
//            }


//if(sender != null) {
//boolean isMobileDevice = isMobile(sender);
//            request.setAttribute("isMobile", isMobileDevice);
//        }
//private boolean isMobile(String userAgent) {
//    String lowerCaseUserAgent = userAgent.toLowerCase();
//    return lowerCaseUserAgent.contains("mobi") || lowerCaseUserAgent.contains("android") ||
//            lowerCaseUserAgent.contains("iphone") || lowerCaseUserAgent.contains("ipad");
//}
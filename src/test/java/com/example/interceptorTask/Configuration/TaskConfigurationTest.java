package com.example.interceptorTask.Configuration;

import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskConfigurationTest {

    @Autowired
    RequestInterceptor requestInterceptor;

    @MockBean
    HttpServletRequest request;

    @MockBean
    HttpServletResponse response;

    @MockBean
    Object handler;

    @Test
    void testPreHandleWithMobileUserAgent() throws Exception {
        //Arrange
        String sender = "Mozilla/5.0 (Linux; Android 6.0.1; Nexus 5X Build/MMB29P) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.83 Mobile Safari/537.36";
        UserAgent userAgent = UserAgent.parseUserAgentString(sender);
        DeviceType deviceType = userAgent.getOperatingSystem().getDeviceType();

        //Act
        when(request.getHeader("User-Agent")).thenReturn(sender);
        when(userAgent.getOperatingSystem().getDeviceType()).thenReturn(DeviceType.MOBILE);
        when(userAgent.getOperatingSystem().getName()).thenReturn("Android");
        when(userAgent.getBrowser().getName()).thenReturn("Chrome");

        boolean result = requestInterceptor.preHandle(request, response, handler);

        //Assert
        verify(request).setAttribute("isMobile", deviceType);
        assertTrue(result);
    }

    @Test
    void testPreHandleWithWebUserAgent() throws Exception {
        //Arrange
        String sender  = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.83 Safari/537.36";
        UserAgent userAgent = UserAgent.parseUserAgentString(sender);
        DeviceType deviceType = userAgent.getOperatingSystem().getDeviceType();

        //Act
        when(request.getHeader("User-Agent")).thenReturn(sender);
        when(userAgent.getOperatingSystem().getDeviceType()).thenReturn(DeviceType.COMPUTER);
        when(userAgent.getOperatingSystem().getName()).thenReturn("Windows");
        when(userAgent.getBrowser().getName()).thenReturn("Chrome");

        boolean result = requestInterceptor.preHandle(request, response, handler);

        //Assert
        verify(request).setAttribute("isMobile", deviceType);
        assertTrue(result);
    }
}
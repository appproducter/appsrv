package com.ruiliang.appsrv.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AppAllParamIntercetor extends HandlerInterceptorAdapter{
	private static final Logger log = LoggerFactory.getLogger(AppAllParamIntercetor.class);  
	
	@Override  
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {  
        log.info("---------------------开始进入请求地址拦截----------------------------");  
        String bodyParam = getBodyString(httpServletRequest);
        httpServletRequest.setAttribute("params", bodyParam);
        return true;
        
    }  
  
    @Override  
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {  
    	
    }  
  
    @Override  
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {  
    }  
    /**
	 * 获取请求Body
	 *
	 * @param request
	 * @return
	 */
	public static String getBodyString(final ServletRequest request) {
		StringBuilder sb = new StringBuilder();
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			inputStream = cloneInputStream(request.getInputStream());
			reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
 
	/**
	 * Description: 复制输入流</br>
	 * 
	 * @param inputStream
	 * @return</br>
	 */
	public static InputStream cloneInputStream(ServletInputStream inputStream) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		try {
			while ((len = inputStream.read(buffer)) > -1) {
				byteArrayOutputStream.write(buffer, 0, len);
			}
			byteArrayOutputStream.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		return byteArrayInputStream;
	}

}
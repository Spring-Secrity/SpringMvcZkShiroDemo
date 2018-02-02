/**
 * 
 */
package com.anlu.shiro.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @desc org.chench.test.shiroweb.util.MyUtil
 * @author chench9@lenovo.com
 * @date 2017年3月9日
 */
public class MyUtil {
	public static String redirect(HttpServletRequest req, HttpServletResponse resp, String uri) throws IOException {
		return new StringBuilder().append("redirect:").append(uri).toString().trim();
	}

	private MyUtil(){
	}
}

/**
 * 
 */
package com.easyway.java.basic.utils;

import java.util.Map;
import java.util.Map.Entry;

import com.easyway.java.basic.utils.StringUtils;


/**
 * 表单生成器用于表单生成
 * 
 * @author longgangbai 2014-8-8 上午10:26:25
 */
public class FormGenerator {
    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */

    private static final String input_charset = "utf-8";
    private static final String INPUT_CHARSET_KEY = "_input_charset";
    private static final String GATEWAY_ACTION = "payUrl";
    private static final String GATEWAY_ACTION_METHOD = "GATEWAY_ACTION_METHOD";
    private static final String GATEWAY_ACTION_METHOD_GET = "post";
    private static final String GATEWAY_ACTION_BUTTON = "submitBtn";

    /**
     * 目前可以支持多种方式 建立请求，以表单HTML形式构造（默认）
     * 
     * @param paywayParam
     *            请求参数数组
     * @return 提交表单HTML文本
     */
    public static String buildRequest(Map<String, String> paywayParam) {
	// 待请求参数数组
	// 获取请求的动作
	String gatewayAction = paywayParam.get(GATEWAY_ACTION);
	// 判断当前测试的模式
	Boolean isDeBug = Boolean.valueOf(paywayParam.get("isDeBug"));
	// 如果是debug模式采用测试配置的测试路径
	String notify_url_debug = paywayParam.get("notify_url_debug");
	if (isDeBug) {
	    gatewayAction = notify_url_debug;
	}
	// 获取请求的方法
	String gatewayMethod = paywayParam.get(GATEWAY_ACTION_METHOD);
	if (gatewayMethod==null) {
	    gatewayMethod = GATEWAY_ACTION_METHOD_GET;
	}
	// 构建请求表单信息
	StringBuffer sbHtml = new StringBuffer();
	if (paywayParam.containsKey(INPUT_CHARSET_KEY)) {
	    sbHtml.append("<form id=\"paywaySubmit\" name=\"alipaysubmit\" action=\"" + gatewayAction + "?_input_charset=" + input_charset
		    + "\" method=\"" + gatewayMethod + "\">");
	} else {
	    sbHtml.append("<form id=\"paywaySubmit\" name=\"alipaysubmit\" action=\"" + gatewayAction + "\" method=\"" + gatewayMethod + "\">");
	}
	for (Entry<String, String> entry : paywayParam.entrySet()) {
	    if (entry.getKey().equals("paymethod") || entry.getKey().equals(GATEWAY_ACTION) || entry.getKey().equals("isDeBug")
		    || entry.getKey().equals("notify_url_debug")) {
		continue;
	    }
	    sbHtml.append("<input type=\"hidden\" name=\"" + entry.getKey() + "\" value=\"" + entry.getValue() + "\"/>");
	}
	// submit按钮控件请不要含有name属性
	sbHtml.append("<input type=\"submit\" value=\"" + GATEWAY_ACTION_BUTTON + "\" style=\"display:none;\"></form>");
	sbHtml.append("<script>document.forms['paywaySubmit'].submit();</script>");
	return sbHtml.toString();
    }

}

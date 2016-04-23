package com.wsbm.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class Utils {

    public static void writeBack(HttpServletRequest request,
        HttpServletResponse response, JSONObject jv) {
        try {
            String callback = request.getParameter("callback");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(Utils.getJsonResult(callback, jv));
        } catch (Exception ex) {}
    }

    public static String getJsonResult(String callback, JSONObject json) {
        if (StringUtils.isEmpty(callback)) {
            return json.toJSONString();
        }
        return callback + "("
            + json.toJSONString() + ")";
    }

    public static void writeBack(HttpServletRequest request,
        HttpServletResponse response, String str) {
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(str);
            response.getWriter().flush();
        } catch (Exception ex) {}
    }
}

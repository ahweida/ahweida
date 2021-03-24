package com.weida.epzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.weida.epcommon.jwt.JwtUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

@Component
public class MyFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(MyFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest  request = requestContext.getRequest();
        boolean flag = request.getRequestURL().toString().contains("login")
                || request.getRequestURL().toString().contains("illegal");
        if (flag){
            return false;
         }
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        String accessToken = request.getHeader(JwtUtils.AUTH_HEADER_KEY);


        if(StringUtils.isEmpty(accessToken)) {
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){}

            return null;
        } else {
            try {
                JwtUtils.parserToken(accessToken);
                //token 继续向下传递
                ctx.addZuulRequestHeader(JwtUtils.AUTH_HEADER_KEY, accessToken);
            } catch (Exception e) {
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(401);
                return null;
            }
        }
        log.info("ok");
        return null;
    }
}
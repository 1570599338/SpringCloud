package com.lquan.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;


import javax.servlet.http.HttpServletRequest;

/**
 * ClassName AccessFilter
 *
 * @Author lquan
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/
public class AccessFilter extends ZuulFilter {

    /***
     * 过滤器的类型，它决定过滤器在请求的那个生命周期中执行，
     * 这里定义为 pre，代表会在请求被路由之前执行。
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序，当请求在一个阶段中存在多个过滤器时，需要根据该
     * 方法返回的值来一次执行。
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     *判断该过滤器是否需要被执行。这里我们直接返回true，因为该过滤器对所有的请求都会生效
     * 实际运用中我们可以利用函数来执行过滤器的有效范围。
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑。这里我们通过ctx。setSendZuulResponse(false)令zuul过滤该请求，不过其进行
     * 路由，然后通过ctx.setResponseStatusCode(401)设置了返回的错误代码，当我们也可以进一步优化我
     * 们的返回，譬如，通过cxt.setResponseBody(body)对返回的body内容进行编辑。
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest  request = ctx.getRequest();


        System.out.println("send"+request.getMethod()+" request To{}"+request.getRequestURL().toString());

        Object token = request.getParameter("accessToken");
//        if (token==null){
//            System.out.println("*******************");
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            ctx.setResponseBody("XXXXXXXXXY************************************XXXXXXXXX");
//        }

        System.out.println("access token ok");
        return null;
    }








}















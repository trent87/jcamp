package org.trent.jcamp.nio2.filter.biz;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.trent.jcamp.nio2.filter.HttpRequestFilter;


@Slf4j
public class ProxyBizFilter implements HttpRequestFilter {

    public static ProxyBizFilter newInstance(){
        return new ProxyBizFilter();
    }

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String uri = fullRequest.uri();
        log.info("filter (FullHttpRequest,ChannelHandlerContext) 接收到请求，url：{} ",uri);
        if(uri.startsWith("/hello")){
            //
        }
        else{
            throw new RuntimeException("不支持的uri:"+uri);
        }
        HttpHeaders headers = fullRequest.headers();
        if(headers == null){
            headers = new DefaultHttpHeaders();
        }
        headers.add("proxy-tag",this.getClass().getSimpleName());
    }
}

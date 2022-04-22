package com.wobenwudi.simple;

import com.wobenwudi.chat.ChatServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2022/4/20 21:24
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class HttpInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("my-http-server-codec",new HttpServerCodec());
        pipeline.addLast("my-http-server-handler",new ChatServerHandler());



    }
}

package com.wobenwudi.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2022/4/21 09:50
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg.trim());
    }
}

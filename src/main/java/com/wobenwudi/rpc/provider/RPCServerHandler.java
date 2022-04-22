package com.wobenwudi.rpc.provider;

import com.wobenwudi.rpc.Constant;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2022/4/22 14:01
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class RPCServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("server read " + msg.toString());

        if (msg.toString().startsWith(Constant.providerName)) {
            TestImpl test = new TestImpl();
            String ioMsg = test.call("io msg: " + msg.toString());
            ctx.writeAndFlush(ioMsg);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }
}

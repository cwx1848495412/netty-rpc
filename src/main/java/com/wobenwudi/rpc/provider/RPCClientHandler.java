package com.wobenwudi.rpc.provider;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2022/4/22 14:01
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class RPCClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context;
    private String result;
    private String param;

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public synchronized void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = msg.toString();
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }

    /**
     * 被代理对象调用 发送数据给服务端
     * 阻塞等服务端返回 等待被唤醒
     *
     * @return
     * @throws Exception
     */
    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(param);
        wait();
        return result;
    }

}

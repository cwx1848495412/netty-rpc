package com.wobenwudi.rpc.provider;

import com.wobenwudi.rpc.Constant;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2022/4/20 20:08
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class NettyClient {
    private static ExecutorService executor = Executors.newFixedThreadPool(5);

    private static RPCClientHandler clientHandler;

    private static void initClient() throws Exception {
        clientHandler = new RPCClientHandler();
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventExecutors)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(clientHandler);
                    }
                });
        System.out.println("客户端 ok ...");

        bootstrap.connect(Constant.host, Constant.port).sync();
    }

    public Object getBean(final Class<?> serviceClass, final String providerName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        return Proxy.newProxyInstance(classLoader, new Class<?>[]{serviceClass}, (proxy, method, args) -> {
            if (clientHandler == null) {
                initClient();
            }

            // 设置要发送给服务端的信息
            // providerName 协议头
            // args[0] 具体的 第一个参数
            clientHandler.setParam(providerName + args[0]);

            return executor.submit(clientHandler).get();
        });
    }
}

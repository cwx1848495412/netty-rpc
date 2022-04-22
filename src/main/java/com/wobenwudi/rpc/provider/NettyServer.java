package com.wobenwudi.rpc.provider;

import com.wobenwudi.rpc.Constant;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2022/4/22 13:57
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class NettyServer {

    public static void startServer(int port) throws Exception {
        startServer0(port);
    }

    private static void startServer0(int port) throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new RPCServerHandler());

                        }
                    });


            System.out.println("...服务器 is ready...");

            ChannelFuture cf = bootstrap.bind(Constant.host,port).sync();

            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
        }

    }
}

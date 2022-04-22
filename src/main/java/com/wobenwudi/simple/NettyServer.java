package com.wobenwudi.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2022/4/20 19:48
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class NettyServer {
    public static void main(String[] args) throws Exception {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
//                    .childHandler(new ChannelInitializer<SocketChannel>() {
//
//                        @Override
//                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(new NettyServerHandler());
//                        }
//                    });
            .childHandler(new HttpInitializer());


            System.out.println("...服务器 is ready...");

            ChannelFuture cf = bootstrap.bind(815).sync();

            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
        }
    }
}

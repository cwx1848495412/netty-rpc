package com.wobenwudi.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2022/4/21 09:11
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class WebSocketServer {
    private int port;

    public WebSocketServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {

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
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new ChunkedWriteHandler());
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            pipeline.addLast(new WebSocketServerProtocolHandler("/"));
                            pipeline.addLast(new WebSocketFrameHandler());


                        }
                    });


            System.out.println("...服务器 is ready...");

            ChannelFuture cf = bootstrap.bind(port).sync();

            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        WebSocketServer heartServer = new WebSocketServer(815);
        heartServer.run();
    }
}

package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    public static void main(String[] args) throws Exception {

        /** 创建线两个程组，都是无限循环
         * bossGroup 只处理连接请求，业务处理交给worderGroup
         * worderGroup 进行网络通信的（网络读写的）
         **/
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup worderGroup = new NioEventLoopGroup();

        try {
            // 服务器端启动对象bootstrap
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 设置bootstrap参数
            bootstrap.group(bossGroup, worderGroup)        // 绑定俩个线程组
                    .channel(NioServerSocketChannel.class)        // 指定NIO的 通道实现模式
                    .option(ChannelOption.SO_BACKLOG, 1024)        // 设置线程队列连接个数，tcp缓冲区
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024)    // 设置发送缓冲大小
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024)    // 这是接收缓冲大小
                    .option(ChannelOption.SO_KEEPALIVE, true)    // 保持连接
                    .childHandler(new ChannelInitializer<SocketChannel>() {     // 创建一个通道测试对象
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            // 给 管道pipeline 设置 处理器Handler
                            sc.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("===服务器准备就绪===");

            // 启动服务器，绑定并同步端口
            ChannelFuture cf = bootstrap.bind(8888).sync();

            // 监听关闭通道，监听到关闭事件时执行
            cf.channel().closeFuture().sync();
        } finally {
            // 优雅的关闭
            bossGroup.shutdownGracefully();
            worderGroup.shutdownGracefully();
        }
    }
}
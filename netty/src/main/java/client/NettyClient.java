package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    public static void main(String[] args) throws Exception {

        // 客户端事件循环组
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            // 客户端启动对象
            Bootstrap bootstrap = new Bootstrap();
            // 设置bootstrap参数
            bootstrap.group(eventExecutors) // 绑定线程组
                    .channel(NioSocketChannel.class)    //设置客户端通道的实现类
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 加入处理器
                            ch.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            System.out.println("===客户端准备就绪===");

            // 启动客户端，连接服务器端口
            ChannelFuture cf = bootstrap.connect("127.0.0.1", 8888).sync();

            // 监听关闭通道，监听到关闭事件时执行
            cf.channel().closeFuture().sync();
        }finally {
            eventExecutors.shutdownGracefully();
        }
    }
}

package client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/*
客户端Handler
*/
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    // 通道就绪时触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端通道就绪，上下文对象：" + ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 服务端，我准备好了", CharsetUtil.UTF_8));
    }

    // 通道Channel有数据时触发
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("接收到服务端" + ctx.channel().remoteAddress() + "的消息：" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器上下文对象" + ctx);
    }

    // 异常处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

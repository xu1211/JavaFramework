package server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/*
服务端Handler
*/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    // 通道Channel有数据时触发
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务器上下文对象" + ctx);

        ByteBuf buf = (ByteBuf) msg;
        System.out.println("接收到客户端" + ctx.channel().remoteAddress() + "的消息：" + buf.toString(CharsetUtil.UTF_8));
    }

    // 读取数据完毕后执行
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端", CharsetUtil.UTF_8));
    }

    // 异常处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

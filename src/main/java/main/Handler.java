package main;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Date;


@ChannelHandler.Sharable
public class Handler extends ChannelInboundHandlerAdapter {

    /**
     * 服务器的连接被建立后调用
     * 建立连接后该 channelActive() 方法被调用一次
     *
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("有客户端建立过来啦。。。");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("收到的消息是"+ msg);

//        ByteBuf in = (ByteBuf) msg;
//        System.out.println("客户端发来消息: " + in.toString(CharsetUtil.UTF_8));
        // ChannelHandlerContext提供各种不同的操作用于触发不同的I/O时间和操作
        // 调用write方法来逐字返回接收到的信息
        // 这里我们不需要调用释放，因为Netty会在写的时候自动释放
        // 只调用write是不会释放的，它会缓存，直到调用flush
        // 将所接收的消息返回给发送者。注意，这还没有冲刷数据
         // ctx.write(msg);
         ctx.writeAndFlush(Unpooled.copiedBuffer(msg.toString() ,
                CharsetUtil.UTF_8));
    }
}

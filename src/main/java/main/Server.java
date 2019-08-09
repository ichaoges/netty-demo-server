package main;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;


public class Server {
    public static void main(String[] args) throws Exception {

        // 主线程循环（监听新连接）
        NioEventLoopGroup boos = new NioEventLoopGroup();
        // 从线程循环（负责数据读取和业务处理）
        NioEventLoopGroup worker = new NioEventLoopGroup();

        // 服务端创建
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(boos, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChildChannelInitializer());

        ChannelFuture f = serverBootstrap.bind(8000).sync();

        System.out.println(Server.class.getName() + " started and listen on " + 8000);

        f.channel().closeFuture().sync();
    }

    private static class ChildChannelInitializer extends ChannelInitializer<NioSocketChannel> {
        @Override
        protected void initChannel(NioSocketChannel ch) {
            ch.pipeline().addLast(new StringDecoder());
            ch.pipeline().addLast(new Handler());
        }
    }
}

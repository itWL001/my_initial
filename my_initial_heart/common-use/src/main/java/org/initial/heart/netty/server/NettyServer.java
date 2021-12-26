package org.initial.heart.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * netty服务端
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        //1.创建bossGroup线程组，处理网络事件-连接事件
        EventLoopGroup bootGroup = new NioEventLoopGroup(1);
        //2.创建workerGroup线程组，处理网络事件-读写事件
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        //3.创建服务端启动助手
        ServerBootstrap bootstrap = new ServerBootstrap();
        //4.设置bossGroup线程组和workerGroup线程组[线程数是逻辑处理器数的两倍]
        bootstrap.group(bootGroup, workGroup)
                //5.设置服务端通道实现为NIO
                .channel(NioServerSocketChannel.class)
                //6.创建一个通道初始化对象
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //7.向pipeline中添加自定义业务处理handler
                        socketChannel.pipeline().addLast(new StringDecoder());
                        socketChannel.pipeline().addLast(new StringEncoder());
                        socketChannel.pipeline().addLast(new NettyServerHandler());
                    }
                });
        //8.启动服务端并绑定端口，同时将异步设为同步
        ChannelFuture channelFuture = bootstrap.bind(8090).sync();
        System.out.println("==服务端启动成功！！！==");
        //9.关闭通道和关闭连接池
        channelFuture.channel().closeFuture().sync();
        workGroup.shutdownGracefully();
        bootGroup.shutdownGracefully();
    }
}

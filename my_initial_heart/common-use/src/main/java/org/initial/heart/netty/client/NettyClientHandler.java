package org.initial.heart.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 客户端自定义处理类
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<String> {
    /**
     * 通道读取事件-读取服务端发送的消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //super.channelRead(ctx, msg);
        System.out.println("客户端接受的消息："+msg);
    }

    /**
     *
     * @param channelHandlerContext
     * @param s
     * @throws Exception
     */
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {

    }

    /**
     * 通道连接就绪事件-与服务端建立连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //super.channelActive(ctx);
        ctx.writeAndFlush("netty客户端:))");
    }
}

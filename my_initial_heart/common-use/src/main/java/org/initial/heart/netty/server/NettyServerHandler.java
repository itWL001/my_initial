package org.initial.heart.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 服务端业务处理类
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {
    /**
     *
     * @param channelHandlerContext
     * @param s
     * @throws Exception
     */
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println();
    }

    /**
     * 通道读取完毕事件,给客户端响应
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        ctx.writeAndFlush("你好！！！netty:)");
    }

    /**
     * 通道读取就绪事件--接受客户端请求
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        System.out.println("服务端接收到的消息："+msg);
    }
}

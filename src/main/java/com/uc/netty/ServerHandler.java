package com.uc.netty;

import com.uc.Calculator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;
public class ServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
		ByteBuf in = (ByteBuf) msg;
		byte[] req = new byte[in.readableBytes()];
		in.readBytes(req);
		String body = new String(req,"utf-8");
		System.out.println("收到客户端消息:"+body+",name:" + Thread.currentThread().getName());
		String calrResult = null;
		try{
			calrResult = Calculator.cal(body).toString();
		}catch(Exception e){
			calrResult = "错误的表达式：" + e.getMessage();
		}
		//ctx.write(Unpooled.copiedBuffer(calrResult.getBytes()));
		ConnectionManager.getInstance().put(new ConnBean(ctx,calrResult));
	}
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	/**
	 * 异常处理
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
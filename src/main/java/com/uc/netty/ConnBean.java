package com.uc.netty;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by yangzhen on 17/7/14.
 */
public class ConnBean {

    public final ChannelHandlerContext ctx;

    public final String text;


    public ConnBean(ChannelHandlerContext ctx, String text) {
        this.ctx = ctx;
        this.text = text;
    }
}

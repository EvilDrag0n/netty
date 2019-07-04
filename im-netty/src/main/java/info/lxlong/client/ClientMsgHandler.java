package info.lxlong.client;

import info.lxlong.model.Message;
import info.lxlong.util.Util;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Scanner;

@Slf4j
@ChannelHandler.Sharable
public class ClientMsgHandler extends SimpleChannelInboundHandler<Message> {

    private static final String IM_CLIENT_NAME = "netty_im_client";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Message msg) throws Exception {
        try {
            Util.printMsg(msg);
            Scanner scanner = new Scanner(System.in);
            log.info(IM_CLIENT_NAME + ", please input msg: ");
            String reply = scanner.nextLine();

            Message message = new Message(IM_CLIENT_NAME, new Date(), reply);
            ctx.writeAndFlush(message);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}

package info.lxlong.server;

import info.lxlong.model.Message;
import info.lxlong.util.Util;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Scanner;

@Slf4j
@ChannelHandler.Sharable
public class ServerMsgHandler extends ChannelInboundHandlerAdapter {

    private static final String SERVER_NAME = "netty_im_server";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("client进入聊天室。");
        Message message = new Message(SERVER_NAME, new Date(), "Hello, I'm server side.");
        ctx.writeAndFlush(message);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg1) throws Exception {
        try {
            Message msg = (Message) msg1;
            Util.printMsg(msg);
            Scanner scanner = new Scanner(System.in);
            log.info(SERVER_NAME + ", please input msg: ");
            String reply = scanner.nextLine();

            Message message = new Message(SERVER_NAME, new Date(), reply);
            ctx.writeAndFlush(message);
        } finally {
            ReferenceCountUtil.release(msg1);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

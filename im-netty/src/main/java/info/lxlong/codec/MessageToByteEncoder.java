package info.lxlong.codec;

import info.lxlong.model.Message;
import info.lxlong.util.Util;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

public class MessageToByteEncoder extends io.netty.handler.codec.MessageToByteEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf byteBuf) throws Exception {
        String content = Util.encodeMsg(message);
        byteBuf.writeBytes(content.getBytes(CharsetUtil.UTF_8));
    }
}

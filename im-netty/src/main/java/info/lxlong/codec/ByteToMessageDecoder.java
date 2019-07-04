package info.lxlong.codec;

import info.lxlong.model.Message;
import info.lxlong.util.Util;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

import java.util.List;

public class ByteToMessageDecoder extends io.netty.handler.codec.ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {

        final int length = in.readableBytes();
        final byte[] array = new byte[length];
        in.readBytes(array);
        String totalMsg = new String(array, CharsetUtil.UTF_8);
        String[] content = totalMsg.split("~");
        out.add(new Message(content[0], Util.parseDateTime(content[1]), content[2]));
    }
}

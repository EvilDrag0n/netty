package info.lxlong.client;

import info.lxlong.codec.ByteToMessageDecoder;
import info.lxlong.codec.MessageToByteEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

    public void start() throws InterruptedException {
        NioEventLoopGroup workLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap clientBootstrap = new Bootstrap();
            clientBootstrap.group(workLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new MessageToByteEncoder())
                                    .addLast(new ByteToMessageDecoder())
                                    .addLast(new ClientMsgHandler());
                        }
                    })
                    .option(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture channelFuture = clientBootstrap.connect("localhost", 8888).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            workLoopGroup.shutdownGracefully().sync();
        }
    }
}

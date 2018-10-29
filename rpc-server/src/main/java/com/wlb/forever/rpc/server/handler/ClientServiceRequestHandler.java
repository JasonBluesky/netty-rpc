package com.wlb.forever.rpc.server.handler;

import com.wlb.forever.rpc.common.protocol.request.ConsumerServiceRequestPacket;
import com.wlb.forever.rpc.common.protocol.response.ConsumerServiceResponsePacket;
import com.wlb.forever.rpc.server.executor.ClientRequestExecutor;
import com.wlb.forever.rpc.server.executor.ExecutorLoader;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import static com.wlb.forever.rpc.common.constant.RpcResponseCode.SERVER_EXCEPTION;


/**
 * @Auther: william
 * @Date: 18/10/19 10:09
 * @Description:
 */
@Slf4j
@ChannelHandler.Sharable
public class ClientServiceRequestHandler extends SimpleChannelInboundHandler<ConsumerServiceRequestPacket> {

    public static final ClientServiceRequestHandler INSTANCE = new ClientServiceRequestHandler();

    private ClientServiceRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ConsumerServiceRequestPacket consumerServiceRequestPacket) throws Exception {

        try {
            ClientRequestExecutor clientRequestExecutor = ExecutorLoader.CLIENT_REQUEST_EXECUTOR;
            clientRequestExecutor.executeTask(channelHandlerContext, consumerServiceRequestPacket);
        } catch (Exception e) {
            ConsumerServiceResponsePacket consumerServiceResponsePacket = new ConsumerServiceResponsePacket();
            consumerServiceResponsePacket.setRequestId(consumerServiceRequestPacket.getRequestId());
            consumerServiceResponsePacket.setCode(SERVER_EXCEPTION);
            consumerServiceResponsePacket.setDesc("RPC服务器出现异常");
            consumerServiceResponsePacket.setResult(null);
            channelHandlerContext.writeAndFlush(consumerServiceResponsePacket);
            log.warn("{}调用{}的RPC服务出现异常", consumerServiceRequestPacket.getFromServiceName(), consumerServiceRequestPacket.getToServiceName());
        }

    }

}
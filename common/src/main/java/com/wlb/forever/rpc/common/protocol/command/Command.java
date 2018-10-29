package com.wlb.forever.rpc.common.protocol.command;

/**
 * @Auther: william
 * @Date: 18/10/17 16:33
 * @Description:
 */
public interface Command {
    Byte REGISTER_SERVER_REQUEST = 1;

    Byte REGISTER_SERVER_RESPONSE = 2;

    Byte HEARTBEAT_REQUEST = 3;

    Byte HEARTBEAT_RESPONSE = 4;

    Byte CONSUMER_SERVICE_REQUEST = 5;

    Byte CONSUMER_SERVICE_RESPONSE = 6;

    Byte PRODUCER_SERVICE_REQUEST = 7;

    Byte PRODUCER_SERVICE_RESPONSE = 8;

}

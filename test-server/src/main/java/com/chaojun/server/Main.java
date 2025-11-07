package com.chaojun.server;

import com.chaojun.rpc.config.RpcServiceConfig;
import com.chaojun.rpc.transmission.RpcServer;
import com.chaojun.rpc.transmission.socket.server.SocketRpcServer;
import com.chaojun.server.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {


        RpcServer rpcServer = new SocketRpcServer(8888);


        rpcServer.start();


    }
}

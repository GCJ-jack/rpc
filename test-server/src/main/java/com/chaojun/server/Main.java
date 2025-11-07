package com.chaojun.server;

import com.chaojun.api.UserService;
import com.chaojun.rpc.config.RpcServiceConfig;
import com.chaojun.rpc.transmission.RpcServer;
import com.chaojun.rpc.transmission.socket.server.SocketRpcServer;
import com.chaojun.server.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig(userService);

        RpcServer rpcServer = new SocketRpcServer(8888);

        rpcServer.publishService(rpcServiceConfig);

        rpcServer.start();


    }
}

package com.chaojun.rpc.transmission;

import com.chaojun.rpc.config.RpcServiceConfig;

public interface RpcServer {

    void start();

    void publishService(RpcServiceConfig rpcServiceConfig);
}

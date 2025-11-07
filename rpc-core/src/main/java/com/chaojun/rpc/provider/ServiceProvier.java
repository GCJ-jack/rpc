package com.chaojun.rpc.provider;

import com.chaojun.rpc.config.RpcServiceConfig;

public interface ServiceProvier {

    void publishService(RpcServiceConfig rpcServiceConfig);

    Object getService(String rpcServiceName);
}

package com.chaojun.rpc.transmission;

import com.chaojun.rpc.dto.RpcReq;
import com.chaojun.rpc.dto.RpcResp;

public interface RpcClient {

    RpcResp<?> sendReq(RpcReq rpcReq);
}

package com.chaojun.client;

import com.chaojun.rpc.dto.RpcReq;
import com.chaojun.rpc.dto.RpcResp;
import com.chaojun.rpc.transmission.RpcClient;
import com.chaojun.rpc.transmission.socket.client.SocketRpcClient;

public class Main {

    public static void main(String[] args) {


        RpcClient rpcClient = new SocketRpcClient("127.0.0.1", 8888);

        RpcReq req = RpcReq.builder()
                .reqID("1213")
                .interfaceName("com.chaojun.api.UserService")
                .methodName("getUser")
                .params(new Object[]{1L})
                .paramTypes(new Class[]{Long.class})
                .build();
        RpcResp<?> rpcResp = rpcClient.sendReq(req);

        System.out.println(rpcResp.getData());
    }
}

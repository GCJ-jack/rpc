package com.chaojun.client;

import com.chaojun.rpc.dto.RpcReq;
import com.chaojun.rpc.dto.RpcResp;
import com.chaojun.rpc.transmission.RpcClient;
import com.chaojun.rpc.transmission.socket.client.SocketRpcClient;

public class Main {

    private static <T> T invoke(Long id) {

        RpcClient rpcClient = new SocketRpcClient("127.0.0.1",8888);

        RpcReq req = RpcReq.builder()
                .reqID("1213")
                .interfaceName("com.chaojun.server.service.UserServiceImpl")
                .methodName("getUser")
                .params(new Object[]{id})
                .paramTypes(new Class[]{Long.class})
                .build();


        RpcResp<?> rpcResp = rpcClient.sendReq(req);
        System.out.println(rpcResp.getData());
        return (T) rpcResp.getData();
    }

    public static void main(String[] args) {

//        invoke(1L);

        RpcClient rpcClient = new SocketRpcClient("127.0.0.1",8888);


        RpcReq req = RpcReq.builder()
                .reqID("1213")
                .interfaceName("com.chaojun.api.UserService")
                .methodName("getUser")
                .params(new Object[]{1L})
                .paramTypes(new Class[]{Long.class})
                .build();


        RpcResp<?> rpcResp = rpcClient.sendReq(req);
//        System.out.println(rpcResp.getData());
    }
}

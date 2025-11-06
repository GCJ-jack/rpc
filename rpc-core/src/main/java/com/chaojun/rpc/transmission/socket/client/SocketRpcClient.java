package com.chaojun.rpc.transmission.socket.client;

import com.chaojun.rpc.dto.RpcReq;
import com.chaojun.rpc.dto.RpcResp;
import com.chaojun.rpc.transmission.RpcClient;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
@Slf4j
public class SocketRpcClient implements RpcClient {


    private final String host;
    private final int port;


    public SocketRpcClient(String host, int port){
        this.host = host;
        this.port = port;
    }
    @Override
    public RpcResp<?> sendReq(RpcReq rpcReq) {

        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcReq);
            objectOutputStream.flush();


            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object o = objectInputStream.readObject();

            return (RpcResp<?>) o;
        } catch (Exception e) {
            log.error("send the request failed " + e);
        }
        return null;
    }
}

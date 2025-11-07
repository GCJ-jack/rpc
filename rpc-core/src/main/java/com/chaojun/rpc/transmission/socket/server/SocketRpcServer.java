package com.chaojun.rpc.transmission.socket.server;

import com.chaojun.rpc.config.RpcServiceConfig;
import com.chaojun.rpc.dto.RpcReq;
import com.chaojun.rpc.dto.RpcResp;
import com.chaojun.rpc.handler.RpcReqHandler;
import com.chaojun.rpc.provider.ServiceProvier;
import com.chaojun.rpc.provider.impl.SimpleServiceProvider;
import com.chaojun.rpc.transmission.RpcServer;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
@Slf4j
public class SocketRpcServer implements RpcServer {

    private final int port;
    private final ServiceProvier serviceProvier;
    private final RpcReqHandler rpcReqHandler;

    public SocketRpcServer(int port,ServiceProvier serviceProvier){
        this.port = port;
        this.serviceProvier = serviceProvier;
        this.rpcReqHandler = new RpcReqHandler(serviceProvier);
    }

    public SocketRpcServer(int port){
        this(port,new SimpleServiceProvider());
    }

    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket;
            log.info("server starts on {}",port);
            while ((socket = serverSocket.accept()) != null){
                ObjectInputStream inputStream = new ObjectInputStream(socket
                        .getInputStream());
                RpcReq rpcReq = (RpcReq) inputStream.readObject();
                System.out.println(rpcReq);


                Object data = rpcReqHandler.invoke(rpcReq);

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                RpcResp<?> rpcResp = RpcResp.success(rpcReq.getReqID(),data);
                objectOutputStream.writeObject(rpcResp);
                objectOutputStream.flush();
            }
        } catch (Exception e) {
            log.error("receive error " + e);
        }
    }

    @Override
    public void publishService(RpcServiceConfig rpcServiceConfig) {
        serviceProvier.publishService(rpcServiceConfig);
    }

//    private Object invoke(RpcReq rpcReq) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        String rpcServiceProvider = rpcReq.rpcServiceName();
//        Object service = serviceProvier.getService(rpcServiceProvider);
//
//        log.info("getting the service {} ", (Object) service.getClass().getAnnotations());
//
//        Method method = service.getClass().getDeclaredMethod(rpcReq.getMethodName(),
//                rpcReq.getParamTypes());
//
//        return method.invoke(service, rpcReq.getParams());
//    }
}

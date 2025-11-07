package com.chaojun.rpc.handler;

import com.chaojun.rpc.dto.RpcReq;
import com.chaojun.rpc.provider.ServiceProvier;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class RpcReqHandler {

    private final ServiceProvier serviceProvier;

    public RpcReqHandler(ServiceProvier serviceProvier){
        this.serviceProvier = serviceProvier;
    }

    public Object invoke(RpcReq rpcReq) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String rpcServiceProvider = rpcReq.rpcServiceName();
        Object service = serviceProvier.getService(rpcServiceProvider);

        log.info("getting the service {} ", (Object) service.getClass().getAnnotations());

        Method method = service.getClass().getDeclaredMethod(rpcReq.getMethodName(),
                rpcReq.getParamTypes());

        return method.invoke(service, rpcReq.getParams());
    }
}

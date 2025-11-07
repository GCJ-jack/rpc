package com.chaojun.rpc.provider.impl;

import cn.hutool.core.collection.CollUtil;
import com.chaojun.rpc.config.RpcServiceConfig;
import com.chaojun.rpc.provider.ServiceProvier;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class SimpleServiceProvider implements ServiceProvier {

    private final static Map<String,Object> SERVICE_CACHE = new HashMap<>();

    @Override
    public void publishService(RpcServiceConfig rpcServiceConfig) {

        List<String> interfaceList = rpcServiceConfig.rpcServiceName();

        if(CollUtil.isEmpty(interfaceList)){
            throw new RuntimeException("this service did not implement any interface");
        }

        log.info("publish service successfully {}",interfaceList);

        interfaceList.forEach(rpcServiceName -> SERVICE_CACHE.put(
                rpcServiceName,rpcServiceConfig.getService()
        ));

        for(Map.Entry<String,Object> entry:SERVICE_CACHE.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    @Override
    public Object getService(String rpcServiceName) {
        if(!SERVICE_CACHE.containsKey(rpcServiceName)){
            throw new RuntimeException("There is not such service");
        }

        return SERVICE_CACHE.get(rpcServiceName);
    }

}

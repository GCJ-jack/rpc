package com.chaojun.rpc.dto;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcReq implements Serializable {

    private String reqID;
    private String interfaceName;
    private String methodName;
    private Object[] params;
    private Class<?>[] paramTypes;
    private String version;
    private String group;


    public String rpcServiceName(){
//        return getInterfaceName() + getVersion() + getGroup();

        return StrUtil.blankToDefault(getInterfaceName(),StrUtil.EMPTY) + StrUtil.blankToDefault(getVersion(),StrUtil.EMPTY) + StrUtil.blankToDefault(getGroup(),StrUtil.EMPTY);
    }
}

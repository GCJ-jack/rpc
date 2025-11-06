package com.chaojun.rpc.dto;

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
}

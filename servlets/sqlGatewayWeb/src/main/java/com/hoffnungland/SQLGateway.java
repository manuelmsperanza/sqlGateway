package com.hoffnungland;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.hoffnungland.impl.SQLGatewayException;
import com.hoffnungland.model.SQLGatewayRequest;
import com.hoffnungland.model.SQLGatewayResponse;

import javax.jws.WebMethod;

@WebService
@SOAPBinding(style=SOAPBinding.Style.DOCUMENT, use=SOAPBinding.Use.LITERAL,parameterStyle=SOAPBinding.ParameterStyle.BARE)
public interface SQLGateway{
    
    @WebMethod
    public SQLGatewayResponse invokeSql(SQLGatewayRequest request) throws SQLGatewayException;
}
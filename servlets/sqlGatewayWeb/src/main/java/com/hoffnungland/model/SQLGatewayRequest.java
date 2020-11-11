package com.hoffnungland.model;

public class SQLGatewayRequest  implements java.io.Serializable {

	private static final long serialVersionUID = 3122961387268149732L;
	
	private String invokeName;
	private ValueTypeEnum returnValueType;
	private SQLGatewayParam[] params;
	
	public String getInvokeName() {
		return invokeName;
	}
	public void setInvokeName(String invokeName) {
		this.invokeName = invokeName;
	}
	public ValueTypeEnum getReturnValueType() {
		return returnValueType;
	}
	public void setReturnValueType(ValueTypeEnum returnValueType) {
		this.returnValueType = returnValueType;
	}
	public SQLGatewayParam[] getParams() {
		return params;
	}
	public void setParams(SQLGatewayParam[] params) {
		this.params = params;
	}
	
	
	
}

package com.hoffnungland.model;

public class SQLGatewayResponse  implements java.io.Serializable {
	
	private static final long serialVersionUID = -4182624384663367523L;
	
	private String returnValue;
	private SQLGatewayParam[] params;
	
	public String getReturnValue() {
		return returnValue;
	}
	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}
	public SQLGatewayParam[] getParams() {
		return params;
	}
	public void setParams(SQLGatewayParam[] params) {
		this.params = params;
	}
	
	
	
}

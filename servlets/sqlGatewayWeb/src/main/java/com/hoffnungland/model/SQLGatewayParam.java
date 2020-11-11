package com.hoffnungland.model;

public class SQLGatewayParam implements java.io.Serializable {
	
	private static final long serialVersionUID = -3395577033042911564L;
	
	private String name;
	private ValueTypeEnum dataType;
	private String value;
	private String format;
	private DataDirectionEnum dataDirection;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ValueTypeEnum getDataType() {
		return dataType;
	}
	public void setDataType(ValueTypeEnum dataType) {
		this.dataType = dataType;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public DataDirectionEnum getDataDirection() {
		return dataDirection;
	}
	public void setDataDirection(DataDirectionEnum dataDirection) {
		this.dataDirection = dataDirection;
	}
	
	
	
}

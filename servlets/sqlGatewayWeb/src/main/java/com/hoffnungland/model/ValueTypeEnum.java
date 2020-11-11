package com.hoffnungland.model;

public enum ValueTypeEnum {
	NUMBER("NUMBER"),
	VARCHAR2("VARCHAR2"),
	BOOLEAN("BOOLEAN"),
	DATE("DATE");
	
	private String dataType ;

	private ValueTypeEnum(String dataType) {
		this.dataType = dataType;
	}

	@Override
	public String toString() {
		
		return this.dataType;
	}
	
}

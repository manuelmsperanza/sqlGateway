package com.hoffnungland.model;

public enum DataDirectionEnum {
	
	IN("IN"),
	OUT("OUT"),
	INOUT("INOUT");
	
	private String direction;

	private DataDirectionEnum(String direction) {
		this.direction = direction;
	}
	
	@Override
	public String toString() {
		
		return this.direction;
	}
	
}

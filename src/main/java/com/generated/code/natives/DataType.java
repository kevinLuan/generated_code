package com.generated.code.natives;

public class DataType {
	public String sqlType;
	public Long length;
	public String javaType;
	public Integer code;

	public DataType() {
	}

	public DataType(String sqlType, Long length, String javaType, Integer code) {
		this.sqlType = sqlType;
		this.length = length;
		this.javaType = javaType;
		this.code = code;
	}

	public DataType(String sqlType, String javaType, Integer code) {
		this.sqlType = sqlType;
		this.javaType = javaType;
		this.code = code;
	}
}

package com.generated.code.natives;

import java.util.HashMap;
import java.util.Map;

import com.generated.code.exception.NotFountExcetion;

public class PostgreNative implements Native {
	public Map<String, DataType> map = new HashMap<String, DataType>();

	public void ini() {
		register(new DataType("varchar", "java.lang.String", 12));
		register(new DataType("char", "java.lang.String", 1));
		register(new DataType("cidr", "java.lang.Object", 1111));
		register(new DataType("inet", "java.lang.Object", 1111));
		register(new DataType("macaddr", "java.lang.Object", 1111));
		register(new DataType("text", "java.lang.String", 12));
		register(new DataType("int8", "java.lang.Long", -5));
		register(new DataType("bytea", "byte", -2));
		register(new DataType("box", "java.lang.Object", 1111));
		register(new DataType("circle", "java.lang.Object", 1111));
		register(new DataType("float8", "java.lang.Double", 8));
		register(new DataType("int4", "java.lang.Integer", 4));
		register(new DataType("interval", "java.lang.Object", 1111));
		register(new DataType("line", "java.lang.Object", 1111));
		register(new DataType("lseg", "java.lang.Object", 1111));
		register(new DataType("money", "java.lang.Double", 8));
		register(new DataType("numeric", "java.math.BigDecimal", 2));
		register(new DataType("path", "java.lang.Object", 1111));
		register(new DataType("point", "java.lang.Object", 1111));
		register(new DataType("polygon", "java.lang.Object", 1111));
		register(new DataType("float4", "java.lang.Float", 7));
		register(new DataType("int2", "java.lang.Integer", 5));
		register(new DataType("int4", "java.lang.Integer", 4));
		register(new DataType("time", "java.sql.Time", 92));
		register(new DataType("timestamp", "java.sql.Timestamp", 93));
		register(new DataType("bit", "java.lang.Boolean", -7));
		register(new DataType("varbit", "java.lang.Object", 1111));
		register(new DataType("bool", "java.lang.Boolean", -7));

	}

	public void register(DataType dataType) {
		map.put(dataType.sqlType, dataType);
	}

	@Override
	public boolean containsKey(String key) {
		return false;
	}

	@Override
	public DataType get(String key) {
		if (containsKey(key)) {
			return map.get(key);
		} else {
			throw new NotFountExcetion("没有找到匹配的数据类型:`" + key + "`");
		}
	}

}

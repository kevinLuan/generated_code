package com.generated.code.natives;

import java.util.HashMap;
import java.util.Map;

import com.generated.code.exception.NotFountExcetion;

public class MysqlNative implements Native {

	public Map<String, DataType> map = new HashMap<String, DataType>();

	public void init() {
		map.put("VARCHAR", new DataType("VARCHAR", "java.lang.String", 12));
		map.put("CHAR", new DataType("CHAR", "java.lang.String", 1));
		map.put("BLOB", new DataType("BLOB", "java.lang.Byte[]", -4));
		map.put("TEXT", new DataType("TEXT", 65535l, "java.lang.String", -1));
		map.put("INTEGER", new DataType("INTEGER", "java.lang.Integer", 4));
		// map.put("INTEGER",new JavaDataType("INTEGER", "java.lang.Long", 4));
		map.put("TINYINT", new DataType("TINYINT", "java.lang.Integer", -6));
		map.put("SMALLINT", new DataType("SMALLINT", "java.lang.Integer", 5));
		map.put("MEDIUMINT", new DataType("MEDIUMINT", "java.lang.Integer", 4));
		map.put("BIT", new DataType("BIT", "java.lang.Boolean ", -7));
		map.put("BIGINT", new DataType("BIGINT", "java.lang.Long", -5));
		// map.put("BIGINT",new JavaDataType("BIGINT",
		// "java.math.BigInteger",-5));
		map.put("FLOAT", new DataType("FLOAT", "java.lang.Float", 7));
		map.put("DOUBLE", new DataType("DOUBLE", "java.lang.Double", 8));
		map.put("DECIMAL", new DataType("DECIMAL ", "java.math.BigDecimal", 3));
		map.put("BOOLEAN", new DataType("BOOLEAN", "java.lang.Integer ", -6));
		map.put("DATE", new DataType("DATE", "java.sql.Date", 91));
		map.put("TIME", new DataType("TIME", "java.sql.Time", 92));
		map.put("DATETIME", new DataType("DATETIME", "java.sql.Timestamp", 93));
		map.put("TIMESTAMP", new DataType("TIMESTAMP", "java.sql.Timestamp ", 93));
		map.put("YEAR", new DataType("YEAR", "java.sql.Date", 91));
		map.put("ID", new DataType("ID", "java.lang.Long", 4));

		map.put("BINARY", new DataType("BINARY", "java.lang.byte[]", -2));

	}

	@Override
	public boolean containsKey(String key) {
		return map.containsKey(key);
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

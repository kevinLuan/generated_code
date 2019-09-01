package com.generated.code.natives;

import java.util.HashMap;
import java.util.Map;

import com.generated.code.exception.NotFountExcetion;

public class PostgreNative implements Native {
	public Map<String, DataType> map = new HashMap<String, DataType>();

	@Override
	public boolean containsKey(String key) {
		// TODO Auto-generated method stub
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

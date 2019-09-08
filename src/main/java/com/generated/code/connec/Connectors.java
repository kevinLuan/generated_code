package com.generated.code.connec;

import com.generated.code.util.Config;

public class Connectors {
	public static Connector createMysqlConnector(Config config) {
		return MysqlConnector.of(config);
	}

	public static Connector createPostgreConnector(Config config) {
		return PostgreConnector.of(config);
	}
}

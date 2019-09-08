package com.generated.code.util;

import java.util.Properties;

import com.generated.code.connec.Connector;
import com.generated.code.connec.Connectors;

import lombok.Data;

@Data(staticConstructor = "of")
public class Config {
	final Properties properties;

	public String getPojoPack() {
		return properties.getProperty("pojo.package");
	}

	public String getMapperPack() {
		return properties.getProperty("mapper.package");
	}

	public String getDaoPack() {
		return properties.getProperty("dao.package");
	}

	public String getDatabaseType() {
		return properties.getProperty("database.type");
	}

	public String getJdbcUrl() {
		return properties.getProperty(getDatabaseType() + ".jdbc.url");
	}

	public String getUserName() {
		return properties.getProperty(getDatabaseType() + ".jdbc.userName");
	}

	public String getPwd() {
		return properties.getProperty(getDatabaseType() + ".jdbc.password");
	}

	public String getDriver() {
		return properties.getProperty(getDatabaseType() + ".jdbc.driver");
	}

	public Connector getConnector() {
		if (getDatabaseType().equals("mysql")) {
			return Connectors.createMysqlConnector(this);
		} else {
			return Connectors.createPostgreConnector(this);
		}
	}

	public String getCodePath() {
		return properties.getProperty("code.source.path");
	}

	public String getCodePath(String filePath) {
		return getCodePath() + filePath;
	}
}

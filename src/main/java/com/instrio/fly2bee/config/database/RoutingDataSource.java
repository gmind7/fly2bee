package com.instrio.fly2bee.config.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.instrio.fly2bee.config.database.DataSourceContextHolder;

public class RoutingDataSource extends AbstractRoutingDataSource {
	
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSourceType();
	}
	
}

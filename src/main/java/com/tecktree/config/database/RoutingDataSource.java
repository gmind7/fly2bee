package com.tecktree.config.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.tecktree.config.database.DataSourceContextHolder;

public class RoutingDataSource extends AbstractRoutingDataSource {
	
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSourceType();
	}
	
}

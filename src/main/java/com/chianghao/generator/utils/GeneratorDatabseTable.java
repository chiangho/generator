package com.chianghao.generator.utils;

import java.util.List;

import com.chianghao.generator.TableInfo;

public class GeneratorDatabseTable {

	private TableInfo tableInfo;
	
	public TableInfo getTableInfo() {
		return tableInfo;
	}

	public void setTableInfo(TableInfo tableInfo) {
		this.tableInfo = tableInfo;
	}

	/**
	 * 构造函数
	 * @param tableInfo 表信息
	 */
	public GeneratorDatabseTable(TableInfo tableInfo) {
		this.tableInfo = tableInfo;
	}
	/**
	 * 往数据库中写
	 */
	public void write() {
		DBHelper dbHelper  = new DBHelper();
		List<String> tables = dbHelper.queryAllTable();
		if(isExistTable(tables,tableInfo.getTableName())) {
			System.out.println(tableInfo.getDropTableSql());
			//dbHelper.execute(tableInfo.getDropTableSql());
		}
		System.out.println(tableInfo.getCreateTableSql());
		//dbHelper.execute(tableInfo.getCreateTableSql());
	}
	/**
	 * 判断表是否存在
	 * @param tables
	 * @param name
	 * @return
	 */
	private boolean isExistTable(List<String> tables,String name) {
		for(String tableName:tables) {
			if(tableName.equals(name)) {
				return true;
			}
		}
		return false;
	}
	
}

package com.chianghao.generator;

import java.util.ArrayList;
import java.util.List;

import com.chianghao.generator.ColumnInfo.ColumnType;
import com.chianghao.generator.utils.StringUtils;

public class TableInfo {

	private String tableName;
	private String dropTableSql;
	private String createTableSql;
	private List<String[]> dataList;
	private List<ColumnInfo> columnList;
	private String     beanSimpleClassName;
	private String     packageName;
	
	public String getClassName() {
		return this.packageName+"."+this.beanSimpleClassName;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getBeanSimpleClassName() {
		return beanSimpleClassName;
	}
	public void setBeanSimpleClassName(String beanSimpleClassName) {
		this.beanSimpleClassName = beanSimpleClassName;
	}

	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
		this.beanSimpleClassName = StringUtils.underlineToCamel(tableName, false);
	}
	public String getDropTableSql() {
		return dropTableSql;
	}
	public void setDropTableSql(String dropTableSql) {
		this.dropTableSql = dropTableSql;
	}
	public String getCreateTableSql() {
		return createTableSql;
	}
	public void setCreateTableSql(String createTableSql) {
		this.createTableSql = createTableSql;
	}
	public List<String[]> getDataList() {
		return dataList;
	}
	public List<ColumnInfo> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<ColumnInfo> columnList) {
		this.columnList = columnList;
	}
	public void setDataList(List<String[]> dataList) {
		this.dataList = dataList;
		StringBuffer sb = new StringBuffer();
		sb.append("create table  "+tableName+"( \n\t");
		List<ColumnInfo> columninfos = new ArrayList<ColumnInfo>();
		String primaryKey="";
		for(int i=0;i<dataList.size();i++) {
			String[] rowData = dataList.get(i);
			String title       = rowData[0].trim();
			String columnName  = rowData[1].trim();
			String columnType  = rowData[2].trim().toLowerCase();
			String isNullStr   = rowData[3]==null?"":rowData[3].trim().toLowerCase();
			String remark      = rowData[4].trim();
			if(title==null||title.length()==0) {
				continue;
			}
			if(columnName==null||columnName.length()==0) {
				continue;
			}
			if(columnType==null||columnType.length()==0) {
				continue;
			}
			boolean isNotNull = false;
			if(isNullStr!=null&&isNullStr.equals("y")) {
				isNotNull = true;
			}
			if(i==0) {
				sb.append(" `"+columnName+"` "+columnType+" "+(isNotNull?"not null":"")+" AUTO_INCREMENT comment '["+title+"]"+remark+"' \n\t");
				primaryKey = columnName;
			}else {
				sb.append(" ,`"+columnName+"` "+columnType+" "+(isNotNull?"not null":"")+" comment '["+title+"]"+remark+"' \n\t");
			}
			
			ColumnInfo columnInfo = new ColumnInfo(columnName,ColumnType.get(columnType),title,remark);
			columninfos.add(columnInfo);
		}
		this.columnList = columninfos;
		sb.append(" ,PRIMARY KEY (`"+primaryKey+"`) \n\t");
		sb.append(")");
		setCreateTableSql(sb.toString());
		setDropTableSql("drop table "+tableName);
		
	}
	
	
	
	
}

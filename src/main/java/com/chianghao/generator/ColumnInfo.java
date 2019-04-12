package com.chianghao.generator;

import com.chianghao.generator.utils.StringUtils;

public class ColumnInfo {

	public enum ColumnType{
		
		string("varchar","String"),
		bigint("bigint","Long"),
		integer("int","Integer"),
		data("date","Date"),
		time("datetime","Date"),
		text("text","String");
		
		private String dbType;
		private String javaType;
		
		private ColumnType(String dbType,String javaType) {
			this.dbType   = dbType;
			this.javaType = javaType;
		}
		public String getDbType() {
			return dbType;
		}
		public void setDbType(String dbType) {
			this.dbType = dbType;
		}
		public String getJavaType() {
			return javaType;
		}
		public void setJavaType(String javaType) {
			this.javaType = javaType;
		}
		
		/**
		 * 通过数据库类型获取枚举
		 * @param dbType
		 * @return
		 */
		public static ColumnType get(String dbType) {
			if(StringUtils.isEmpty(dbType)) {
				return null;
			}
			for(ColumnType c:ColumnType.values()) {
				if(dbType.contains(c.getDbType())) {
					return c;
				}
			}
			return null;
		}
		
	}
	
	private String      columnName;
	private ColumnType  columnType;
	private String      title;
	private String      remark;
	
	public ColumnInfo(String name,ColumnType type,String title,String remark) {
		this.columnName = name;
		this.columnType = type;
		this.title      = title;
		this.remark     = remark;
	}
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public ColumnType getColumnType() {
		return columnType;
	}
	public void setColumnType(ColumnType columnType) {
		this.columnType = columnType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}

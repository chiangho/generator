package com.chianghao.generator.utils;

import java.io.File;
import java.io.FileWriter;

import com.chianghao.generator.ColumnInfo;
import com.chianghao.generator.TableInfo;

/**
 * 输出Java bean对象
 * @author chianghao
 *
 */
public class CodingJaveBeanUtils {

	private String     filePath;
	private TableInfo  tableInfo;
	private String     packageInfo;
	
	/**
	 * 
	 * @param filePath      写路径
	 * @param tableInfo     表信息
	 * @param packageInfo   包名
	 */
	public CodingJaveBeanUtils(String filePath, TableInfo tableInfo,String packageInfo) {
		this.filePath    = filePath;
		this.tableInfo   = tableInfo;
		this.packageInfo = packageInfo;
	}

	
	public String getPackageInfo() {
		return packageInfo;
	}
	public void setPackageInfo(String packageInfo) {
		this.packageInfo = packageInfo;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public TableInfo getTableInfo() {
		return tableInfo;
	}
	public void setTableInfo(TableInfo tableInfo) {
		this.tableInfo = tableInfo;
	}
	
	public void write() {
		File outWriteDir = new File(this.filePath);
		if(!outWriteDir.exists()) {
			outWriteDir.mkdirs();
		}
		StringBuffer sb = new StringBuffer();
		
		sb.append("package "+packageInfo+";\n");
		sb.append("import com.ml.common.model.Po;\n");
		sb.append("import java.util.Date;\n\r");
		
		sb.append("public class "+StringUtils.underlineToCamel(tableInfo.getTableName(),false)+" extends Po {\n");
		sb.append("\t private static final long serialVersionUID = 1L;\n");
		//定义属性
		for(ColumnInfo c:this.tableInfo.getColumnList()) {
			String firstCharacterLowerCase = StringUtils.firstCharacterLowerCase(c.getColumnName());
			sb.append("\t //"+c.getTitle()+"["+c.getRemark()+"]\n");
			sb.append("\t private "+c.getColumnType().getJavaType()+" "+firstCharacterLowerCase+";\n");
		}
		//定义get  set 方法
		for(ColumnInfo c:this.tableInfo.getColumnList()) {
			String firstCharacterLowerCase = StringUtils.firstCharacterLowerCase(c.getColumnName());
			String firstCharacterUpperCase = StringUtils.firstCharacterUpperCase(c.getColumnName());
			
			sb.append("\t public "+c.getColumnType().getJavaType()+" get"+firstCharacterUpperCase+"() { \n");
			sb.append("\t    return "+firstCharacterLowerCase+"; \n");
			sb.append("\t } \n");

			sb.append("\t public void set"+firstCharacterUpperCase+"("+c.getColumnType().getJavaType()+" "+firstCharacterLowerCase+") { \n");
			sb.append("\t    this."+firstCharacterLowerCase+" = "+firstCharacterLowerCase+"; \n");
			sb.append("\t } \n");
		}
		sb.append("}\n");
		
		try {
			File file =new File(outWriteDir,StringUtils.underlineToCamel(tableInfo.getTableName(),false)+".java");
	        if(!file.exists()){
	        	file.createNewFile();
	        }
			FileWriter fileWritter = new FileWriter(file);
			fileWritter.write(sb.toString());
			fileWritter.flush();
			fileWritter.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

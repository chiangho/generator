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
public class CodingMybatisXml {

	private String     filePath;
	private TableInfo  tableInfo;
	private String     packageInfo;
	
	/**
	 * 
	 * @param filePath      写路径
	 * @param tableInfo     表信息
	 * @param packageInfo   包名
	 */
	public CodingMybatisXml(String filePath, TableInfo tableInfo,String packageInfo) {
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
		
		File FileDaoDir = new File(outWriteDir,tableInfo.getTableName());
		if(!FileDaoDir.exists()) {
			FileDaoDir.mkdirs();
		}
		
		//拼xml
		StringBuffer xmlsb = new StringBuffer();
		xmlsb.append(""+
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
				"<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\"> \n"+
				"<mapper namespace=\""+packageInfo+"."+tableInfo.getTableName()+"."+StringUtils.underlineToCamel(tableInfo.getTableName(), false)+"Dao\" > \n"+
				"");
		xmlsb.append(" <resultMap id=\""+StringUtils.underlineToCamel(tableInfo.getTableName(), true)+"\" type=\"\" >\r\n" );
				  
	    for(ColumnInfo c:this.tableInfo.getColumnList()) {
				String firstCharacterLowerCase = StringUtils.firstCharacterLowerCase(c.getColumnName());
				xmlsb.append(" <result column=\""+c.getColumnName()+"\" property=\""+firstCharacterLowerCase+"\" />\r\n");
		}
		xmlsb.append(" </resultMap>\r\n");
		xmlsb.append(" </mapper>");
		//拼dao
		StringBuffer daosb = new StringBuffer();
		daosb.append("package "+packageInfo+"."+tableInfo.getTableName()+";\n");
		daosb.append("public interface "+StringUtils.underlineToCamel(tableInfo.getTableName(), false)+"Dao{ \n" );
		
		daosb.append("}" );
		
		try {
			//写xml
			File file =new File(FileDaoDir,StringUtils.underlineToCamel(tableInfo.getTableName(),false)+"DaoMapper.xml");
	        if(!file.exists()){
	        	file.createNewFile();
	        }
			FileWriter fileWritter = new FileWriter(file);
			fileWritter.write(xmlsb.toString());
			fileWritter.flush();
			fileWritter.close();
			
			//写dao
			File daoFile =new File(FileDaoDir,StringUtils.underlineToCamel(tableInfo.getTableName(),false)+"Dao.java");
			if(!daoFile.exists()){
				daoFile.createNewFile();
			}
			FileWriter daoFileWritter = new FileWriter(daoFile);
			daoFileWritter.write(daosb.toString());
			daoFileWritter.flush();
			daoFileWritter.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

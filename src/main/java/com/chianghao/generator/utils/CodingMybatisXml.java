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
		
//		File FileDaoDir = new File(outWriteDir,tableInfo.getTableName());
//		if(!FileDaoDir.exists()) {
//			FileDaoDir.mkdirs();
//		}
		
		
		
		
		//拼xml
		StringBuffer xmlsb = new StringBuffer();
		xmlsb.append(""+
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
				"<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\"> \n"+
				"<mapper namespace=\""+this.tableInfo.getClassName()+"\" > \n"+
				"");
		xmlsb.append(" <resultMap id=\""+StringUtils.underlineToCamel(tableInfo.getTableName(), true)+"\" type=\""+this.tableInfo.getClassName()+"\" >\r\n" );
				  
	    for(ColumnInfo c:this.tableInfo.getColumnList()) {
				String firstCharacterLowerCase = StringUtils.firstCharacterLowerCase(c.getColumnName());
				xmlsb.append(" <result column=\""+c.getColumnName()+"\" property=\""+firstCharacterLowerCase+"\" />\r\n");
		}
		xmlsb.append(" </resultMap>\r\n");
		xmlsb.append(" </mapper>");
		
		
		
		
		
		//拼dao
		StringBuffer daosb = new StringBuffer();
		daosb.append("package "+packageInfo+".dao;\n");
		daosb.append("import "+this.tableInfo.getClassName()+";\n");
		daosb.append("public interface "+tableInfo.getBeanSimpleClassName()+"Mapper extends CoreBaseDao<"+tableInfo.getBeanSimpleClassName()+">{ \n" );
		
		daosb.append("}" );
		
		
		//拼daoimpl
		
		StringBuffer daoImplSb = new StringBuffer();
		daoImplSb.append("package "+packageInfo+".daoImpl;\n");
		daoImplSb.append("import "+this.tableInfo.getClassName()+";\n");
		daoImplSb.append("import "+packageInfo+".dao."+tableInfo.getBeanSimpleClassName()+"Mapper;\n");
		daoImplSb.append("import org.springframework.stereotype.Repository;\n");
		
		daoImplSb.append("@Repository\r");
		daoImplSb.append("public class "+this.tableInfo.getBeanSimpleClassName()+"MapperImpl extends CoreBaseDaoImpl<"+this.tableInfo.getBeanSimpleClassName()+"> implements "+this.tableInfo.getBeanSimpleClassName()+"Mapper{ \n" );
		
		daoImplSb.append("}" );
		
		
		
		try {
			//写xml
			File fileXml = new File(outWriteDir,"sqlmapper");
			if(!fileXml.exists()) {
				fileXml.mkdirs();
			}
			File file =new File(fileXml,tableInfo.getBeanSimpleClassName()+"Mapper.xml");
	        if(!file.exists()){
	        	file.createNewFile();
	        }
			FileWriter fileWritter = new FileWriter(file);
			fileWritter.write(xmlsb.toString());
			fileWritter.flush();
			fileWritter.close();
			
			//写dao
			File fileDao = new File(outWriteDir,"dao");
			if(!fileDao.exists()) {
				fileDao.mkdirs();
			}
			File daoFile =new File(fileDao,tableInfo.getBeanSimpleClassName()+"Mapper.java");
			if(!daoFile.exists()){
				daoFile.createNewFile();
			}
			FileWriter daoFileWritter = new FileWriter(daoFile);
			daoFileWritter.write(daosb.toString());
			daoFileWritter.flush();
			daoFileWritter.close();
			
			
			
			//写daoImpl
			File fileDaoImpl = new File(outWriteDir,"daoImpl");
			if(!fileDaoImpl.exists()) {
				fileDaoImpl.mkdirs();
			}
			File daoImplFile =new File(fileDaoImpl,tableInfo.getBeanSimpleClassName()+"MapperImpl.java");
			if(!daoImplFile.exists()){
				daoImplFile.createNewFile();
			}
			FileWriter daoImplFileWritter = new FileWriter(daoImplFile);
			daoImplFileWritter.write(daoImplSb.toString());
			daoImplFileWritter.flush();
			daoImplFileWritter.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

package com.chianghao.generator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import com.chianghao.generator.utils.CodingJaveBeanUtils;
import com.chianghao.generator.utils.CodingMybatisXml;
import com.chianghao.generator.utils.GeneratorDatabseTable;
import com.chianghao.generator.utils.XLSHelper;

/**
 * Hello world!
 *
 */
public class App {
	
	public static final String URL="jdbc:mysql://127.0.0.1:3306/gateway?useUnicode=true&useSSL=false&characterEncoding=utf-8&serverTimezone=Hongkong";
	public static final String USER="root";
	public static final String PASSWORD="111111";
	
	
	public static void main(String[] args) {
//		String databasePath = "E:\\03Doc\\设备管理\\database.xls";
		String databasePath = "E:\\03Doc\\ml-doc\\云平台二期文档\\MDC\\MDC_DB.xls";
		XLSHelper xlsHelper = new XLSHelper(databasePath);
		List<TableInfo> tableInfos = new ArrayList<TableInfo>();
		if(xlsHelper!=null) {
			Iterator<Sheet> sheets = xlsHelper.getSheets();
			while(sheets.hasNext()) {
				Sheet sheet = sheets.next();
				String name = sheet.getSheetName().trim().toLowerCase();
				TableInfo  tableInfo = new TableInfo();
				tableInfo.setTableName(name);//设置名称
				tableInfo.setPackageName("com.ml.common.mdc.entity");
				List<String[]> dataList = xlsHelper.getSheetData(sheet,2);
				tableInfo.setDataList(dataList);//设置数据
				tableInfos.add(tableInfo);//添加table
			}
		}
		
		for(TableInfo tableInfo : tableInfos) {
			//创建数据库
			GeneratorDatabseTable databaseTable = new GeneratorDatabseTable(tableInfo);
			databaseTable.write();
			//创建java bean
			CodingJaveBeanUtils beanUtil = new CodingJaveBeanUtils("E:\\02Porject\\generator\\mdc\\entity",tableInfo,"com.ml.common.mdc.entity");
			beanUtil.write();
			
			
			CodingMybatisXml xmlUtil = new CodingMybatisXml("E:\\02Porject\\generator\\mdc\\dao",tableInfo,"com.ml.mdc");
			xmlUtil.write();
		}
	}
	
	
	
}

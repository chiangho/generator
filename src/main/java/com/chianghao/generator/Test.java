package com.chianghao.generator;

import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pro.x", "1");
		map.put("pro.y", "2");
		map.put("pro.z", "3");
		
		for(String key:map.keySet()) {
			System.out.println(map.get(key));
		}
		
		
//		CloseableHttpClient client = HttpClients.createDefault();
//		HttpGet get = new HttpGet("http://127.0.0.1:8080/get_object");
//		try {
//			HttpResponse response = client.execute(get);
//			HttpEntity entity = response.getEntity(); 
//			InputStream is = entity.getContent(); 
//			GeneratorClass  generatorClass = new GeneratorClass();
//			generatorClass.addProperty("name",PropertyDataType.STRING);
//			generatorClass.addProperty("count",PropertyDataType.DECIMAL);
//			Class<?> clazz = generatorClass.create("com.wimi.helloworld.controll","UserInfo");
//			if(clazz!=null) {
//				ObjectInputStream objInt=new ObjectInputStream(is);
//				Object obj=clazz.newInstance();
//				obj=objInt.readObject();
//				System.out.println(ClassUtil.getFieldValue(obj, "name"));
//				System.out.println(ClassUtil.getFieldValue(obj, "count"));
//				
//			}
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}  
	}
	
}

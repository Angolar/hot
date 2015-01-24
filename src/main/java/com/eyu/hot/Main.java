package com.eyu.hot;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
	//注意：使用接口  
	static Target obj = new TargetImpl();

	public static void main(String[] args) throws Exception {
		while (true) {
			String path = "/Users/bin/Documents/workspace/hot/target/classes/com/eyu/hot/TargetImpl.class";
			byte[] b = getBytes(path);
			Class c = new DynamicClassLoader().findClass(b);
			obj = (Target) c.newInstance();
			System.err.println(obj.name());
			TimeUnit.SECONDS.sleep(2);
		}
	}

	// 从本地读取文件
	private static byte[] getBytes(String filename) throws IOException {
		File file = new File(filename);
		long len = file.length();
		byte raw[] = new byte[(int) len];
		FileInputStream fin = new FileInputStream(file);
		fin.read(raw);
		fin.close();
		return raw;
	}

}

package com.github.ysl3000;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HashmapLoader {
	public static <T extends Object> void save(T obj, String path)
			throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				SmartServerTool.getMainDirectory()+ "/Hashmaps/" + path));
		oos.writeObject(obj);
		oos.flush();
		oos.close();
	}

	@SuppressWarnings("unchecked")
	public static <T extends Object> T load(String path) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				SmartServerTool.getMainDirectory()+ "/Hashmaps/" + path));
		T result = (T) ois.readObject();
		ois.close();
		return result;
	}

}

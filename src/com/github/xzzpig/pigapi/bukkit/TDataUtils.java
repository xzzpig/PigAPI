package com.github.xzzpig.pigapi.bukkit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import com.github.xzzpig.pigapi.plugin.Main;

public class TDataUtils {

	private static final HashMap<Serializable, Serializable> data = new HashMap<>();
	private static final HashMap<Object, Object> tempdata = new HashMap<>();

	public static void loadData() throws IOException, ClassNotFoundException {
		File dir = new File(Main.self.getDataFolder(), "datas");
		if (!dir.exists())
			return;
		File file = new File(dir, "data.obj");
		if (!file.exists())
			return;
		FileInputStream fin = new FileInputStream(file);
		ObjectInputStream oin = new ObjectInputStream(fin);
		@SuppressWarnings("unchecked")
		HashMap<Serializable, Serializable> d = (HashMap<Serializable, Serializable>) oin.readObject();
		data.putAll(d);
		oin.close();
		fin.close();
	}

	public static void saveData() throws IOException {
		File dir = new File(Main.self.getDataFolder(), "datas");
		if (!dir.exists())
			dir.mkdirs();
		File file = new File(dir, "data.obj");
		if (!file.exists())
			file.createNewFile();
		FileOutputStream fout = new FileOutputStream(file);
		ObjectOutputStream oout = new ObjectOutputStream(fout);
		oout.writeObject(data);
		oout.close();
		fout.close();
		// System.err.println("[PigAPI]DataUtils:" + entry.getKey() +
		// "的数据保存失败");
	}

	@SuppressWarnings("unchecked")
	public static <T> T getData(Serializable key, Class<T> clazz) {
		return (T) data.get(key);
	}

	public static void setData(Serializable key, Serializable value) {
		data.put(key, value);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getTempData(Object key, Class<T> clazz) {
		return (T) tempdata.get(key);
	}

	public static void setTempData(Object key, Object value) {
		tempdata.put(key, value);
	}
}

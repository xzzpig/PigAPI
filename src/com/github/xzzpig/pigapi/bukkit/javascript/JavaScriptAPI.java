package com.github.xzzpig.pigapi.bukkit.javascript;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.xzzpig.pigapi.PigData;
import com.github.xzzpig.pigapi.plugin.Main;

public class JavaScriptAPI {
	private PigData data = new PigData();
	
	public List<Object> createList(){
		return new ArrayList<Object>();
	}
	
	public Map<Object,Object> createMap(){
		return new HashMap<>();
	}
	
	public PigData createPigData(){
		return new PigData();
	}
	
	public void setData(String key,Object value){
		data.set(key, value);
	}
	
	public Object getData(String key){
		return data.get(key);
	}
	
	public File getPluginFolder(){
		return Main.self.getDataFolder();
	}
	
	public File getFile(String path){
		return new File(path);
	}
	
	public File getSubFile(File file,String path){
		return new File(file,path);
	}
}

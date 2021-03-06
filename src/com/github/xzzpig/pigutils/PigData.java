package com.github.xzzpig.pigutils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class PigData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6660306307743729543L;

	private HashMap<String, Object> data = new HashMap<String, Object>();

	private PigData parent;

	public PigData() {
	}

	public PigData(File file) throws FileNotFoundException {
		this(new FileReader(file));
	}

	public PigData(InputStream source) {
		this(new InputStreamReader(source));
	}

	private PigData(PigData parent) {
		this.parent = parent;
	}

	public PigData(Reader source) {
		this(source, null);
	}

	private PigData(Reader source, PigData parent) {
		this.parent = parent;
		if (source == null)
			return;
		int i = 0;
		StringBuffer sb = new StringBuffer();
		String key = "", value;
		while (true)
			try {
				i = source.read();
				if (i == -1)
					return;
				char c = (char) i;
				if (c == ':') {
					key = sb.toString();
					sb = new StringBuffer();
					continue;
				} else if (c == ';') {
					value = sb.toString();
					sb = new StringBuffer();
					value = value.replace('：', ':').replace('；', ';').replace('｛', '{').replace('｝', '}');
					this.data.put(key, value);
					continue;
				} else if (c == '{') {
					key = sb.toString();
					this.data.put(key, new PigData(source, this));
					sb = new StringBuffer();
					continue;
				} else if (c == '}')
					return;
				else
					sb.append(c);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public PigData(String soruce) {
		this(new StringReader(soruce));
	}

	public boolean contianKey(String key) {
		String[] keys = key.replace('.', '。').split("。");
		if (keys.length == 1)
			return (this.data.containsKey(key));
		String thiskey = keys[0];
		Object thisvalue;
		if ((!this.data.containsKey(thiskey)) || (!(this.data.get(thiskey) instanceof PigData)))
			return false;
		else
			thisvalue = this.data.get(thiskey);
		PigData pData = (PigData) thisvalue;
		return pData.contianKey(key.replaceFirst(thiskey + ".", ""));
	}

	public Object get(String key) {
		String[] keys = key.replace('.', '。').split("。");
		if (keys.length == 1)
			if (this.data.containsKey(key))
				return this.data.get(key);
			else
				return "";
		String thiskey = keys[0];
		Object thisvalue;
		if ((!this.data.containsKey(thiskey)) || (!(this.data.get(thiskey) instanceof PigData)))
			thisvalue = new PigData(this);
		else
			thisvalue = this.data.get(thiskey);
		PigData pData = (PigData) thisvalue;
		return pData.get(key.replaceFirst(thiskey + ".", ""));
	}

	public boolean getBoolean(String key) {
		String str = this.get(key).toString();
		if (str.equalsIgnoreCase("true"))
			return true;
		return false;
	}

	public HashMap<String, Object> getData() {
		return this.data;
	}

	public double getDouble(String key) {
		String str = this.get(key).toString();
		try {
			return Double.valueOf(str);
		} catch (Exception e) {
		}
		return 0;
	}

	public List<Double> getDoubleList(String key) {
		List<Double> list = new ArrayList<Double>();
		for (String string : this.getList(key))
			try {
				list.add(Double.valueOf(string));
			} catch (Exception e) {
			}
		return list;
	}

	public PigData getFinalParent() {
		if (this.parent == null)
			return this;
		return this.parent.getFinalParent();
	}

	public float getFloat(String key) {
		String str = this.get(key).toString();
		try {
			return Float.valueOf(str);
		} catch (Exception e) {
		}
		return 0;
	}

	public List<Float> getFloatList(String key) {
		List<Float> list = new ArrayList<Float>();
		for (String string : this.getList(key))
			try {
				list.add(Float.valueOf(string));
			} catch (Exception e) {
			}
		return list;
	}

	public int getInt(String key) {
		String str = this.get(key).toString();
		try {
			return Integer.valueOf(str);
		} catch (Exception e) {
		}
		return 0;
	}

	public List<Integer> getIntList(String key) {
		List<Integer> list = new ArrayList<Integer>();
		for (String string : this.getList(key))
			try {
				list.add(Integer.valueOf(string));
			} catch (Exception e) {
			}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<String> getList(String key) {
		Object object = this.get(key);
		List<String> list;
		try {
			list = (List<String>) object;
		} catch (Exception e) {
			String str = this.get(key).toString();
			list = new ArrayList<String>();
			if (!(str.startsWith("[") && str.endsWith("]")))
				return list;
			String[] strs = str.substring(1, str.length() - 1).replace(',', '，').split("，");
			for (String string : strs)
				list.add(string);
		}
		set(key, list);
		return list;
	}

	public long getLong(String key) {
		String str = this.get(key).toString();
		try {
			return Long.valueOf(str);
		} catch (Exception e) {
		}
		return 0;
	}

	public List<Long> getLongList(String key) {
		List<Long> list = new ArrayList<Long>();
		for (String string : this.getList(key))
			try {
				list.add(Long.valueOf(string));
			} catch (Exception e) {
			}
		return list;
	}

	public PigData getParent() {
		return this.parent;
	}

	public String getPrintString() {
		return this.getPrintString(0);
	}

	private String getPrintString(int before) {
		StringBuffer beforeBuffer = new StringBuffer();
		for (int i = 0; i < before; i++)
			beforeBuffer.append(' ');
		StringBuffer sb = new StringBuffer();
		for (Entry<String, Object> entry : this.data.entrySet()) {
			sb.append(beforeBuffer + entry.getKey());
			Object value = entry.getValue();
			if (value instanceof PigData)
				sb.append('{').append("\n").append(((PigData) value).getPrintString(before + 2) + beforeBuffer)
						.append('}').append("\n");
			else
				sb.append(':').append(
						TString.toString(value).replace(':', '_').replace(';', '_').replace('{', '_').replace('}', '_'))
						.append(';').append("\n");
		}
		return sb.toString();
	}

	public short getShort(String key) {
		String str = this.get(key).toString();
		try {
			return Short.valueOf(str);
		} catch (Exception e) {
		}
		return 0;
	}

	public List<Short> getShortList(String key) {
		List<Short> list = new ArrayList<Short>();
		for (String string : this.getList(key))
			try {
				list.add(Short.valueOf(string));
			} catch (Exception e) {
			}
		return list;
	}

	public String getString(String key) {
		String str = TString.toString(this.get(key));
		if (str.equalsIgnoreCase(""))
			return null;
		return str;
	}

	public PigData getSub(String key) {
		String[] keys = key.replace('.', '。').split("。");
		if (keys.length == 1)
			if (this.data.containsKey(key) && (this.data.get(key) instanceof PigData))
				return (PigData) this.data.get(key);
			else {
				this.data.put(key, new PigData(this));
				return this.getSub(key);
			}
		String thiskey = keys[0];
		Object thisvalue;
		if ((!this.data.containsKey(thiskey)) || (!(this.data.get(thiskey) instanceof PigData)))
			thisvalue = new PigData(this);
		else
			thisvalue = this.data.get(thiskey);
		PigData pData = (PigData) thisvalue;
		return pData.getSub(key.replaceFirst(thiskey + ".", ""));
	}

	public List<PigData> getSubList(String key) {
		PigData data = this;
		List<PigData> subs = new ArrayList<PigData>();
		if (key != null) {
			data = this.getSub(key);
			if (data == null)
				return subs;
		}
		Collection<Object> values = data.data.values();
		for (Object object : values)
			if (object instanceof PigData)
				subs.add((PigData) object);
		return subs;
	}

	public PigData remove(String key) {
		String[] keys = key.replace('.', '。').split("。");
		if (keys.length == 1) {
			this.data.remove(key);
			return this;
		}
		String thiskey = keys[0];
		Object thisvalue;
		if ((!this.data.containsKey(thiskey)) || (!(this.data.get(thiskey) instanceof PigData)))
			return this;
		else
			thisvalue = this.data.get(thiskey);
		PigData pData = (PigData) thisvalue;
		pData.remove(key.replaceFirst(thiskey + ".", ""));
		return this;
	}

	@SuppressWarnings("resource")
	public PigData saveToFile(File file) {
		if (file == null)
			throw (new NullPointerException("File为null"));
		try {
			new FileWriter(file, false).append(this.toString()).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public PigData set(String key, Object value) {
		String[] keys = key.replace('.', '。').split("。");
		if (keys.length == 1) {
			this.data.put(key, value);
			return this;
		}
		String thiskey = keys[0];
		Object thisvalue;
		if ((!this.data.containsKey(thiskey)) || (!(this.data.get(thiskey) instanceof PigData))) {
			thisvalue = new PigData(this);
			this.data.put(thiskey, thisvalue);
		} else
			thisvalue = this.data.get(thiskey);
		PigData pData = (PigData) thisvalue;
		pData.set(key.replaceFirst(thiskey + ".", ""), value);
		return this;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Entry<String, Object> entry : this.data.entrySet()) {
			sb.append(entry.getKey());
			Object value = entry.getValue();
			if (value instanceof PigData)
				sb.append('{').append(TString.toString(value)).append('}');
			else
				sb.append(':').append(
						TString.toString(value).replace(':', '：').replace(';', '；').replace('{', '｛').replace('}', '｝'))
						.append(';');
		}
		return sb.toString();
	}
}
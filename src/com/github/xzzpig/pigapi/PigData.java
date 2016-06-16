package com.github.xzzpig.pigapi;

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
		while (true) {
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
					value = value.replace('：',':')
							.replace( '；',';').replace( '｛','{')
							.replace( '｝','}');
					data.put(key, value);
					continue;
				} else if (c == '{') {
					key = sb.toString();
					data.put(key, new PigData(source, this));
					sb = new StringBuffer();
					continue;
				} else if (c == '}') {
					return;
				} else {
					sb.append(c);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public PigData(String soruce) {
		this(new StringReader(soruce));
	}

	public Object get(String key) {
		String[] keys = key.replace('.', '。').split("。");
		if (keys.length == 1) {
			if (data.containsKey(key))
				return data.get(key);
			else
				return "";
		}
		String thiskey = keys[0];
		Object thisvalue;
		if ((!data.containsKey(thiskey))
				|| (!(data.get(thiskey) instanceof PigData))) {
			thisvalue = new PigData(this);
		} else
			thisvalue = data.get(thiskey);
		PigData pData = (PigData) thisvalue;
		return pData.get(key.replaceFirst(thiskey + ".", ""));
	}

	public boolean getBoolean(String key) {
		String str = get(key).toString();
		if (str.equalsIgnoreCase("true"))
			return true;
		return false;
	}

	public HashMap<String, Object> getData() {
		return data;
	}

	public double getDouble(String key) {
		String str = get(key).toString();
		try {
			return Double.valueOf(str);
		} catch (Exception e) {
		}
		return 0;
	}

	public List<Double> getDoubleList(String key) {
		List<Double> list = new ArrayList<Double>();
		for (String string : getList(key)) {
			try {
				list.add(Double.valueOf(string));
			} catch (Exception e) {
			}
		}
		return list;
	}

	public PigData getFinalParent() {
		if (parent == null) {
			return this;
		}
		return parent.getFinalParent();
	}

	public float getFloat(String key) {
		String str = get(key).toString();
		try {
			return Float.valueOf(str);
		} catch (Exception e) {
		}
		return 0;
	}

	public List<Float> getFloatList(String key) {
		List<Float> list = new ArrayList<Float>();
		for (String string : getList(key)) {
			try {
				list.add(Float.valueOf(string));
			} catch (Exception e) {
			}
		}
		return list;
	}

	public int getInt(String key) {
		String str = get(key).toString();
		try {
			return Integer.valueOf(str);
		} catch (Exception e) {
		}
		return 0;
	}

	public List<Integer> getIntList(String key) {
		List<Integer> list = new ArrayList<Integer>();
		for (String string : getList(key)) {
			try {
				list.add(Integer.valueOf(string));
			} catch (Exception e) {
			}
		}
		return list;
	}

	public List<String> getList(String key) {
		String str = get(key).toString();
		List<String> list = new ArrayList<String>();
		if (!(str.startsWith("[") && str.endsWith("]")))
			return list;
		String[] strs = str.substring(1, str.length() - 1).replace(',', '，')
				.split("，");
		for (String string : strs) {
			list.add(string);
		}
		return list;
	}

	public long getLong(String key) {
		String str = get(key).toString();
		try {
			return Long.valueOf(str);
		} catch (Exception e) {
		}
		return 0;
	}

	public List<Long> getLongList(String key) {
		List<Long> list = new ArrayList<Long>();
		for (String string : getList(key)) {
			try {
				list.add(Long.valueOf(string));
			} catch (Exception e) {
			}
		}
		return list;
	}

	public PigData getParent() {
		return parent;
	}

	public String getPrintString() {
		return getPrintString(0);
	}

	private String getPrintString(int before) {
		StringBuffer beforeBuffer = new StringBuffer();
		for (int i = 0; i < before; i++)
			beforeBuffer.append(' ');
		StringBuffer sb = new StringBuffer();
		for (Entry<String, Object> entry : data.entrySet()) {
			sb.append(beforeBuffer + entry.getKey());
			Object value = entry.getValue();
			if (value instanceof PigData) {
				sb.append('{')
						.append("\n")
						.append(((PigData) value).getPrintString(before + 2)
								+ beforeBuffer).append('}').append("\n");
			} else {
				sb.append(':')
						.append(TString.toString(value).replace(':', '_')
								.replace(';', '_').replace('{', '_')
								.replace('}', '_')).append(';').append("\n");
			}
		}
		return sb.toString();
	}

	public short getShort(String key) {
		String str = get(key).toString();
		try {
			return Short.valueOf(str);
		} catch (Exception e) {
		}
		return 0;
	}

	public List<Short> getShortList(String key) {
		List<Short> list = new ArrayList<Short>();
		for (String string : getList(key)) {
			try {
				list.add(Short.valueOf(string));
			} catch (Exception e) {
			}
		}
		return list;
	}

	public String getString(String key) {
		String str = get(key).toString();
		if (str.equalsIgnoreCase(""))
			return null;
		return str;
	}

	public PigData getSub(String key) {
		String[] keys = key.replace('.', '。').split("。");
		if (keys.length == 1) {
			if (data.containsKey(key) && data.get(key) instanceof PigData)
				return (PigData) data.get(key);
			else {
				data.put(key, new PigData(this));
				return getSub(key);
			}
		}
		String thiskey = keys[0];
		Object thisvalue;
		if ((!data.containsKey(thiskey))
				|| (!(data.get(thiskey) instanceof PigData))) {
			thisvalue = new PigData(this);
		} else
			thisvalue = data.get(thiskey);
		PigData pData = (PigData) thisvalue;
		return pData.getSub(key.replaceFirst(thiskey + ".", ""));
	}

	public List<PigData> getSubList(String key) {
		PigData data = this;
		List<PigData> subs = new ArrayList<PigData>();
		if (key != null) {
			data = getSub(key);
			if (data == null)
				return subs;
		}
		Collection<Object> values = data.data.values();
		for (Object object : values) {
			if (object instanceof PigData)
				subs.add((PigData) object);
		}
		return subs;
	}

	public PigData remove(String key) {
		String[] keys = key.replace('.', '。').split("。");
		if (keys.length == 1) {
			data.remove(key);
			return this;
		}
		String thiskey = keys[0];
		Object thisvalue;
		if ((!data.containsKey(thiskey))
				|| (!(data.get(thiskey) instanceof PigData))) {
			return this;
		} else
			thisvalue = data.get(thiskey);
		PigData pData = (PigData) thisvalue;
		pData.remove(key.replaceFirst(thiskey + ".", ""));
		return this;
	}

	public PigData saveToFile(File file) {
		if (file == null)
			throw (new NullPointerException("File为null"));
		try {
			new FileWriter(file, false).append(toString()).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public PigData set(String key, Object value) {
		String[] keys = key.replace('.', '。').split("。");
		if (keys.length == 1) {
			data.put(key, value);
			return this;
		}
		String thiskey = keys[0];
		Object thisvalue;
		if ((!data.containsKey(thiskey))
				|| (!(data.get(thiskey) instanceof PigData))) {
			thisvalue = new PigData(this);
			data.put(thiskey, thisvalue);
		} else
			thisvalue = data.get(thiskey);
		PigData pData = (PigData) thisvalue;
		pData.set(key.replaceFirst(thiskey + ".", ""), value);
		return this;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Entry<String, Object> entry : data.entrySet()) {
			sb.append(entry.getKey());
			Object value = entry.getValue();
			if (value instanceof PigData) {
				sb.append('{').append(TString.toString(value)).append('}');
			} else {
				sb.append(':')
						.append(TString.toString(value).replace(':', '：')
								.replace(';', '；').replace('{', '｛')
								.replace('}', '｝')).append(';');
			}
		}
		return sb.toString();
	}
}
package com.github.xzzpig.pigapi.pigcommandservice;

import com.github.xzzpig.pigapi.json.JSONObject;

public interface CommandRunner {
	/**
	 * 执行命令
	 * 
	 * @param cmd
	 * @param args
	 * @return 异常(null未无异常)
	 */
	public JSONObject run(String cmd, JSONObject args);
}

package com.github.xzzpig.pigapi.pigcommandservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.github.xzzpig.pigapi.json.JSONObject;

public abstract class Command {
	private static List<Command> cmds = new ArrayList<>();

	static {
		regCommand(new com.github.xzzpig.pigapi.pigcommandservice.command.cs.Command_Print());
		regCommand(new com.github.xzzpig.pigapi.pigcommandservice.command.cc.Command_Print());
		regCommand(new com.github.xzzpig.pigapi.pigcommandservice.command.cc.Command_Help());
	}

	public static Stream<Command> getCommands() {
		return cmds.stream();
	}

	public static void regCommand(Command c) {
		cmds.add(c);
	}

	public Map<String, String> args = new HashMap<>();

	/**
	 * 必填: -key:[value] 非必填: -key:<value>
	 * 
	 * @param key
	 * @param value
	 * @param needed
	 *            是否必填
	 */
	public void addArgs(String key, String value, boolean needed) {
		// args.put(key, value)
		if (needed)
			args.put(key, "[" + value + "]");
		else
			args.put(key, "<" + value + ">");
	}

	protected Map<String, String> getArgs() {
		return args;
	}

	public abstract String getCmd();

	public abstract CommandRunner getCommandRunner();

	public abstract String getDescribe();

	public abstract String getType();

	public JSONObject runCommand(JSONObject args) {
		if (getArgs().entrySet().stream().anyMatch(e -> {
			if (e.getValue().startsWith("[")) {
				if (!args.has(e.getKey())) {
					return true;
				}
			}
			return false;
		}))
			return new JSONObject().put("msg", "命令用法错误:" + this.toString());
		return getCommandRunner().run(getCmd(), args);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(getCmd());
		getArgs().entrySet().stream().forEach(e -> {
			sb.append(' ').append('-').append(e.getKey()).append(':').append(e.getValue());
		});
		sb.append('|').append(getDescribe());
		return sb.toString();
	}
}

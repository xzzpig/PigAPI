package com.github.xzzpig.pigapi.pigcommandservice.command.cc;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.xzzpig.pigapi.json.JSONObject;
import com.github.xzzpig.pigapi.pigcommandservice.CommandRunner;
import com.github.xzzpig.pigapi.pigcommandservice.client.ClientCommand;

public class Command_Print extends ClientCommand {

	public Command_Print() {
		this.addArgs("m", "msg", true);
		this.addArgs("l", "level", false);
	}

	@Override
	public String getCmd() {
		return "print";
	}

	@Override
	public CommandRunner getCommandRunner() {
		return this::run;
	}

	@Override
	public String getDescribe() {
		return "打印信息";
	}

	@Override
	public String getType() {
		return "Client";
	}

	public JSONObject run(String cmd, JSONObject args) {
		String msg = args.optString("m");
		String level = args.optString("level", "INFO");
		Logger.getAnonymousLogger().log(Level.parse(level), msg);
		return null;
	}

}

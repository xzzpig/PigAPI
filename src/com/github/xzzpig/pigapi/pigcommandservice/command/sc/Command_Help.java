package com.github.xzzpig.pigapi.pigcommandservice.command.sc;

import java.util.logging.Logger;

import com.github.xzzpig.pigapi.json.JSONObject;
import com.github.xzzpig.pigapi.pigcommandservice.Command;
import com.github.xzzpig.pigapi.pigcommandservice.CommandRunner;
import com.github.xzzpig.pigapi.pigcommandservice.server.ServerCommand;

public class Command_Help extends ServerCommand {

	public Command_Help() {
	}

	@Override
	public String getCmd() {
		return "help";
	}

	@Override
	public CommandRunner getCommandRunner() {
		return this::run;
	}

	@Override
	public String getDescribe() {
		return "列出所有命令";
	}

	@Override
	public CommandTarget getType() {
		return CommandTarget.Client;
	}

	public JSONObject run(String cmd, JSONObject args) {
		StringBuffer sb = new StringBuffer("命令列表:\n");
		Command.getCommands().filter(c -> c instanceof ServerCommand)
				.filter(c -> c.getType().toString().equalsIgnoreCase("Client")).forEach(c -> {
					sb.append('\t').append(c.toString()).append('\n');
				});
		Logger.getAnonymousLogger().info(sb.toString());
		return null;
	}

}

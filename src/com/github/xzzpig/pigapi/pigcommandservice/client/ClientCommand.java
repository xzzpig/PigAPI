package com.github.xzzpig.pigapi.pigcommandservice.client;

import com.github.xzzpig.pigapi.json.JSONObject;
import com.github.xzzpig.pigapi.pigcommandservice.Command;

public abstract class ClientCommand extends Command {
	public CommandClient getClient(JSONObject args){
		return (CommandClient) args.opt("client");
	}
}

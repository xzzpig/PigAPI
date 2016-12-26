package com.github.xzzpig.pigapi.pigsimpleweb.event;

import com.github.xzzpig.pigapi.event.Event;
import com.github.xzzpig.pigapi.pigsimpleweb.PigSimpleWebServer;

public abstract class PigSWSEvent extends Event{
	private PigSimpleWebServer psws;

	
	protected PigSWSEvent(PigSimpleWebServer psws){
		this.psws = psws;

	}

	public PigSimpleWebServer getPigSimpleWebServer() {
		return psws;
	}
}

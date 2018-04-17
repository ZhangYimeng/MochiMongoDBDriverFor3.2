package com.wonder.mongodb.api.foundation;

import com.wonder.mongodb.api.MongoDBPlayer;
import com.wonder.mongodb.api.foundation.interfaces.MongoManualFunction;
import com.wonder.mongodb.api.foundation.interfaces.SimpleScheduledTask;

public class ManualControlExcutor extends SimpleScheduledTask {
	
	private MongoManualFunction function;
	private MongoDBPlayer player;

	public ManualControlExcutor(MongoManualFunction function, MongoDBPlayer player) {
		this.function = function;
		this.player = player;
	}
	
	@Override
	public void run() {
		function.run(player);
	}

}

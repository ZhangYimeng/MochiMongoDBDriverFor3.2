package com.wonder.mongodb.api.foundation.interfaces;

import java.util.Timer;
import java.util.TimerTask;

public abstract class SimpleScheduledTask extends TimerTask {

	private Timer timer;
	
	public SimpleScheduledTask() {
		timer = new Timer();
	}
	
	public void schedule(long delay, long period) {
		timer.schedule(this, delay, period);
	}
	
	public void stop() {
		timer.cancel();
	}
	
}

package org.yottabase.tagmining.utils;

public class Timer {

	protected long elapsedTime = 0;
	
	protected long latestStart = 0;
	
	
	public void startOrRestart() {
		this.latestStart = System.currentTimeMillis();
	}

	public void pause() {
		this.elapsedTime += System.currentTimeMillis() - this.latestStart;
	}
	
	public long getElapsedTime(){
		return this.elapsedTime;
	}

}

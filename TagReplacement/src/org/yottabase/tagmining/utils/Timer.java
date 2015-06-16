package org.yottabase.tagmining.utils;

public class Timer {

	protected String timerName;

	protected long elapsedTime = 0;
	
	protected long latestStart = 0;
	
	public Timer() {
	}
	
	public Timer(String timerName) {
		super();
		this.timerName = timerName;
	}
	
	public String getTimerName() {
		return timerName;
	}
	
	public void setTimerName(String timerName) {
		this.timerName = timerName;
	}	
	
	public void startOrRestart() {
		this.latestStart = System.currentTimeMillis();
	}

	public void pause() {
		this.elapsedTime += System.currentTimeMillis() - this.latestStart;
	}
	
	public long getElapsedTime(){
		return this.elapsedTime;
	}
	
	public String toString(){
		return String.format("%s: %dms (%dm %ds)",
			this.timerName,
			this.elapsedTime,
			Math.round(this.elapsedTime/1000/60),
			Math.round(this.elapsedTime/1000) - (Math.round(this.elapsedTime/1000/60) * 60)
		);
	}

}

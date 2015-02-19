package com.powerbot.winedrinker;

import java.util.ArrayList;

import org.powerbot.script.PollingScript;
import org.powerbot.script.rt6.ClientContext;

import com.powerbot.task.Task;

public class WineDrinker extends PollingScript<ClientContext> {

	private ArrayList<Task> taskList = new ArrayList<Task>();

	@Override
	public void start(){
		System.out.println("starting script");
	}
	
	@Override
	public void poll() {
		
	}

	
}

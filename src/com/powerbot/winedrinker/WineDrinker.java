package com.powerbot.winedrinker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script.Manifest;
import org.powerbot.script.rt6.ClientContext;

import com.powerbot.task.DepositAndTakeJugs;
import com.powerbot.task.DrinkWineTask;
import com.powerbot.task.Task;

@Manifest(name= "Drunk Wine Drinker", description = " Drinks wine at GE")
public class WineDrinker extends PollingScript<ClientContext> {
	
	private final static Logger log = Logger.getLogger(WineDrinker.class.getName());
	
	private ArrayList<Task> taskList = new ArrayList<Task>();

	@Override
	public void start(){
		log.info("Initializng bot");
		
		taskList.addAll(Arrays.asList(
//				new DrinkWineTask(ctx),
				new DepositAndTakeJugs(ctx)));
	}
	
	@Override
	public void poll() {
		
		for (Task task : taskList) {
			if(task.activate())
				task.execute();
		}
	}

	
}

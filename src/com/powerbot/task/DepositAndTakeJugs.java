package com.powerbot.task;

import java.util.Random;
import java.util.logging.Logger;

import org.powerbot.bot.Con;
import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.rt4.Bank;
import org.powerbot.script.rt6.Bank.Amount;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;

import com.powerbot.winedrinker.Jugs;

public class DepositAndTakeJugs extends Task<ClientContext>{

	public static final Logger log = Logger.getLogger(DepositAndTakeJugs.class.getName());
	
	public DepositAndTakeJugs(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		
		
		log.info("Checking if needed to deposit jugs");
		if(ctx.bank.inViewport() && ctx.backpack.select(JugWineFilter).isEmpty()){
			log.info("Opening bank, depositing empty jugs and taking wine jugs");
			return true;
		}
		
		return false;
	}

	@Override
	public void execute() {
		
		Random rand = new Random();
		
		log.info("Opening bank");
		ctx.bank.open();
		
		Condition.sleep(rand.nextInt(5000));
		
		if(ctx.bank.opened()){
			log.info("Depositing all empty jugs");
			ctx.bank.depositInventory();
		}
		
		Condition.sleep(rand.nextInt(5000));
		if(ctx.backpack.select().isEmpty() && ctx.bank.open()){
			
			log.info("Retrieving wine jugs");
			ctx.bank.withdraw(Jugs.WINE.getCode(), Amount.ALL);
			ctx.bank.close();
		}
		
		Condition.sleep(rand.nextInt(5000));
		
	}
	
	
	public final Filter<Item> JugEmptyFilter = new Filter<Item>() {

		@Override
		public boolean accept(Item item) {

			if (item.id() == Jugs.EMPTY.getCode()) {
				return true;
			}

			return false;
		}

	};
	
	public final Filter<Item> JugWineFilter = new Filter<Item>() {

		@Override
		public boolean accept(Item item) {

			if (item.id() == Jugs.WINE.getCode()) {
				return true;
			}

			return false;
		}

	};
	
}

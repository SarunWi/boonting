package controller;

import interfaces.State;

public class RecycledCheckState implements State {

	@Override
	public void doAction(Context context) {
		System.out.println("RecycledCheckState");
	    context.setState(this);
	}
	
}

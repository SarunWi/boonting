package controller;

import interfaces.State;

public class TravellingState implements State {

	@Override
	public void doAction(Context context) {
		System.out.println("TravellingState");
	    context.setState(this);
	}
	
}

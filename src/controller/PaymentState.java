package controller;

import interfaces.State;

public class PaymentState implements State {

	@Override
	public void doAction(Context context) {
		System.out.println("PaymentState");
	    context.setState(this);
	}

}

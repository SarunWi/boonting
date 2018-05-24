package controller;

import interfaces.State;

public class SellerConfirmState implements State {

	@Override
	public void doAction(Context context) {
		System.out.println("WatingConfirmFromSeller");
	    context.setState(this);
	}

}
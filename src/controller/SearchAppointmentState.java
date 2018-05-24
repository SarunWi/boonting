package controller;

import interfaces.State;

public class SearchAppointmentState implements State {

	@Override
	public void doAction(Context context) {
		System.out.println("ConfirmSalesOrder");
	    context.setState(this);
	}

}

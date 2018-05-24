package controller;

import java.sql.Date;
import java.util.List;

import dto.Response;
import dto.SalesOrder;
import dto.SalesOrderResp;
import model.SalesOrderModel;
import util.Constants;

public class SalesOrderSrvc {
	private int page;
	private int rowsperpage;
	private String orderfield;
		
	public SalesOrderSrvc() {}
	
	public SalesOrderResp getSalesOrder(int page, int rowsperpage, String orderfield) {
		SalesOrderModel salesOrderModel = new SalesOrderModel();
		
		SalesOrderResp salesOrderResp = new SalesOrderResp();
		
		salesOrderResp.setRecords(getSalesOrderList(page, rowsperpage, orderfield, salesOrderModel));
		salesOrderResp.setPage(page);
		salesOrderResp.setTotalRecords(getTotalRecord(0, salesOrderModel));
		
		return salesOrderResp;
	}
	
	public SalesOrderResp getSalesOrderById(int id) {
		SalesOrderModel salesOrderModel = new SalesOrderModel();
		SalesOrderResp salesOrderResp = new SalesOrderResp();
		
		salesOrderResp.setRecords(getSalesOrderListById(id, salesOrderModel));
		salesOrderResp.setPage(1);
		salesOrderResp.setTotalRecords(getTotalRecord(id, salesOrderModel));
		
		return salesOrderResp;
	}
	
	public Response updateSalesOrder(SalesOrder salesOrder) {
		SalesOrderModel salesOrderModel = new SalesOrderModel();
		Response resp = new Response();
		
		if(salesOrder.getSalesOrderStatus() != "" || salesOrder.getSalesOrderStatus() != null) {
			if(salesOrderModel.checkSalesOrderStatus(salesOrder.getSalesOrderStatus()) == 0) { // dont't have this state
				resp.setIsSuccess(false);
				resp.setErrorMessage("Dont't have this state");
				return resp;
			} else {
				Context context = new Context();

				if(salesOrder.getSalesOrderStatus().equals(Constants.WAITING_CNFRM_FROM_SELLER_STATE)) {
					SellerConfirmState sellerConfirmState = new SellerConfirmState();
					sellerConfirmState.doAction(context);
				} else if(salesOrder.getSalesOrderStatus().equals(Constants.CNFRM_SALES_ORDER_STATE)) {
					SearchAppointmentState searchAppointmentState = new SearchAppointmentState();
					searchAppointmentState.doAction(context);
				} else if(salesOrder.getSalesOrderStatus().equals(Constants.RECYCLED_CHECK_STATE)) {
					RecycledCheckState recycleCheckSate = new RecycledCheckState();
					recycleCheckSate.doAction(context);
				} else if(salesOrder.getSalesOrderStatus().equals(Constants.TRAVELLING_STATE)) {
					TravellingState travellingState = new TravellingState();
					travellingState.doAction(context);
				} else if(salesOrder.getSalesOrderStatus().equals(Constants.PAYMENT_STATE)) {
					PaymentState paymentState = new PaymentState();
					paymentState.doAction(context);
				}
			}
		}
		
		int updateSuccess = salesOrderModel.updateSalesOrder(salesOrder);
		if(updateSuccess == 1) {
			resp.setIsSuccess(true);
			resp.setErrorMessage("");
		} else {
			resp.setIsSuccess(false);
			resp.setErrorMessage("Cannot update sales order status");
		}
		return resp;
	}
	
	public Response insertSalesOrder(SalesOrder salesOrder) {
		SalesOrderModel salesOrderModel = new SalesOrderModel();
		Response resp = new Response();
		int updateSuccess = salesOrderModel.insertSalesOrder(salesOrder);
		
		if(updateSuccess == 1) {
			resp.setIsSuccess(true);
			resp.setErrorMessage("");
		} else {
			resp.setIsSuccess(false);
			resp.setErrorMessage("Cannot update sales order status");
		}
		return resp;
	}
	
	public SalesOrderResp getSalesOrderGroupByLocation(Date fromDate, Date toDate) {
		SalesOrderModel salesOrderModel = new SalesOrderModel();
		SalesOrderResp salesOrderResp = new SalesOrderResp();
		
		salesOrderResp.setRecords(salesOrderModel.getSalesOrderGroupByLocation(fromDate, toDate));
		salesOrderResp.setTotalRecords(getTotalRecord(0, salesOrderModel));
		
		return salesOrderResp;
	}
	
	public SalesOrderResp getSalesOrderGroupByCustomer(Date fromDate, Date toDate) {
		SalesOrderModel salesOrderModel = new SalesOrderModel();
		SalesOrderResp salesOrderResp = new SalesOrderResp();
		
		salesOrderResp.setRecords(salesOrderModel.getSalesOrderGroupByCustomer(fromDate, toDate));
		salesOrderResp.setTotalRecords(getTotalRecord(0, salesOrderModel));
		
		return salesOrderResp;
	}
	
	private List<SalesOrder> getSalesOrderList(int page, int rowsperpage, String orderfield, SalesOrderModel salesOrderModel) {
		List<SalesOrder> salesOrderList = salesOrderModel.getSalesOrderList(page, rowsperpage, orderfield);
		return salesOrderList;
	}
	
	private List<SalesOrder> getSalesOrderListById(int id, SalesOrderModel salesOrderModel) {
		return salesOrderModel.getSalesOrderListById(id);
	}
	
	private int getTotalRecord(int id, SalesOrderModel salesOrderModel) {
		System.out.println("getTotalRecord");
		return salesOrderModel.getTotalRecord(id);
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRowsperpage() {
		return rowsperpage;
	}
	public void setRowsperpage(int rowsperpage) {
		this.rowsperpage = rowsperpage;
	}
	public String getOrderfield() {
		return orderfield;
	}
	public void setOrderfield(String orderfield) {
		this.orderfield = orderfield;
	}
}

package controller;

import java.util.List;

import dto.RecycleMaterialList;
import dto.Response;
import dto.SalesOrder;
import dto.SalesOrderRequest;
import dto.SalesOrderResp;
import model.SalesOrderModel;
import util.Constants;

public class SalesOrderSrvc {
	private int page;
	private int rowsperpage;
	private String orderfield;
	
	private int sale_order_id;
	private String sale_order_remark;
	private String sale_order_status;
	private String sale_order_state_name;
	private int shipping_id;
		
	public SalesOrderSrvc() {}
	
	public SalesOrderSrvc(int SaleOrderid,String remark,String status,String statename,int ShipingId) 
	{
		this.sale_order_id = SaleOrderid;
		this.sale_order_remark = remark;
		this.sale_order_status = status;
		this.sale_order_state_name =statename;
		this.shipping_id = ShipingId;
	}
	
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
	
	public Response insertUpdateSalesOrder(SalesOrderRequest salesOrder) {
		SalesOrderModel salesOrderModel = new SalesOrderModel();
		Response resp = new Response();
		Context context = new Context();

		if(salesOrder.getStatus().equals(Constants.WAITING_CNFRM_FROM_SELLER_STATE)) {
			SellerConfirmState sellerConfirmState = new SellerConfirmState();
			sellerConfirmState.doAction(context);
		} else if(salesOrder.getStatus().equals(Constants.CNFRM_SALES_ORDER_STATE)) {
			SearchAppointmentState searchAppointmentState = new SearchAppointmentState();
			searchAppointmentState.doAction(context);
		} else if(salesOrder.getStatus().equals(Constants.RECYCLED_CHECK_STATE)) {
			RecycledCheckState recycleCheckSate = new RecycledCheckState();
			recycleCheckSate.doAction(context);
		} else if(salesOrder.getStatus().equals(Constants.TRAVELLING_STATE)) {
			TravellingState travellingState = new TravellingState();
			travellingState.doAction(context);
		} else if(salesOrder.getStatus().equals(Constants.PAYMENT_STATE)) {
			PaymentState paymentState = new PaymentState();
			paymentState.doAction(context);
		}
		
		int updateSuccess = salesOrderModel.insertUpdateSalesOrder(salesOrder);
		if(updateSuccess == 1) {
			resp.setIsSuccess(true);
			resp.setErrorMessage("Success");
		} else {
			resp.setIsSuccess(false);
			resp.setErrorMessage("Cannot update sales order status");
		}
		return resp;
	}
	
	public Response insertSalesOrder(SalesOrderRequest salesOrderReq) {
		SalesOrderModel salesOrderModel = new SalesOrderModel();
		Response resp = new Response();
		int updateSuccess = salesOrderModel.insertUpdateSalesOrder(salesOrderReq);
		System.out.println("updateSuccess: " + updateSuccess);
		
		if(updateSuccess == 1) {
			resp.setIsSuccess(true);
			resp.setErrorMessage("Insert sales order success");
		} else {
			resp.setIsSuccess(false);
			resp.setErrorMessage("Cannot update sales order status");
		}
		return resp;
	}
	
	public SalesOrderResp getSalesOrderGroupByLocation(String fromDate, String toDate) {
		SalesOrderModel salesOrderModel = new SalesOrderModel();
		SalesOrderResp salesOrderResp = new SalesOrderResp();
		List<SalesOrder> salesOrderList = salesOrderModel.getSalesOrderGroupByLocation(fromDate, toDate);
		salesOrderResp.setRecords(salesOrderList);
		salesOrderResp.setTotalRecords(salesOrderList.size());
		salesOrderResp.setPage(1);
		return salesOrderResp;
	}
	
	public SalesOrderResp getSalesOrderGroupByCustomer(String fromDate, String toDate) {
		SalesOrderModel salesOrderModel = new SalesOrderModel();
		SalesOrderResp salesOrderResp = new SalesOrderResp();
		List<SalesOrder> salesOrderList = salesOrderModel.getSalesOrderGroupByCustomer(fromDate, toDate);
		salesOrderResp.setRecords(salesOrderList);
		salesOrderResp.setTotalRecords(salesOrderList.size());
		salesOrderResp.setPage(1);
		return salesOrderResp;
	}
	
	public Response deleteSalesOrder(int salesOrderId) 
    {
		SalesOrderModel salesOrderModel = new SalesOrderModel();
		int salesOrderExist = salesOrderModel.deleteSaleOrder(salesOrderId);
		Response resp = new Response();
		if(salesOrderExist == 1) {
			resp.setIsSuccess(true);
			resp.setErrorMessage("delete success");
		} else if(salesOrderExist == 0) {
			resp.setIsSuccess(false);
			resp.setErrorMessage("sales order does not exist");
		} else {
			resp.setIsSuccess(false);
			resp.setErrorMessage("cannot delete sales order");
		}
		return resp;					
    }
	
	public List<RecycleMaterialList>getMeterialList(int saleOrderId)
	{
		SalesOrderModel salesOrderModel = new SalesOrderModel();
		List<RecycleMaterialList> RecycleMaterialList = salesOrderModel.getMeterialList(saleOrderId);
		return RecycleMaterialList;
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

package dto;

public class Response {
	private boolean isSuccess;
	private String errorMessage;
	
	public boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public Response(boolean isSuccess, String errorMessage) {
		super();
		this.isSuccess = isSuccess;
		this.errorMessage = errorMessage;
	}
	
	public Response() {
		super();
	}
}

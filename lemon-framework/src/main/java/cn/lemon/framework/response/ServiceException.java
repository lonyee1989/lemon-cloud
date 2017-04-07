package cn.lemon.framework.response;


public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 6654830544228411564L;
	
	private ResultMessage errorMessage;
	
	public ResultMessage getResultMessage() {
		return errorMessage;
	}
	
	public int getErrorCode() {
		return errorMessage.getCode();
	}

	public void setErrorCode(ResultMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public ServiceException(){
		//Hessian序列化保留方式
		super(ResultMessage.FAILURE.getMessage());
		this.errorMessage = ResultMessage.FAILURE;
	}
	
	public ServiceException(ResultMessage errorMessage, Object... args){
		super(String.format(errorMessage.getMessage().contains("%s")? errorMessage.getMessage() : errorMessage.getMessage()+"，%s", args));
		this.errorMessage.setMessage(this.getMessage());
		this.errorMessage = errorMessage;
	}
	
	public ServiceException(ResultMessage errorMessage){
		super(errorMessage.getMessage());
		this.errorMessage = errorMessage;
	}
}

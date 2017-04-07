package cn.lemon.framework.response;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 返回响应结果类
 * @author lonyee
 *
 */
@JsonInclude(Include.NON_NULL)
public class ResultResponse {
    private Meta meta;
    private Object data;

	/**
     * 操作成功
     * @return ResultResponse
     */
    public ResultResponse success() {
    	ResultResponse resultResponse = new ResultResponse();
    	resultResponse.setMeta(new Meta(ResultMessage.SUCCESS));
    	resultResponse.setData(null);
        return resultResponse;
    }
    
    /**
     * 操作成功（返回对象）
     * @param data 数据对象
     * @return ResultResponse
     */
    public ResultResponse success(Object data) {
    	ResultResponse resultResponse = new ResultResponse();
    	resultResponse.setMeta(new Meta(ResultMessage.SUCCESS));
    	resultResponse.setData(data);
        if (data instanceof String || data instanceof Number) {
        	Map<String, Object> map= new HashMap<String, Object>();
        	map.put("value", data);
        	resultResponse.setData(map);
        }
        return resultResponse;
    }
    
    /**
     * 操作成功(返回单个对象)
     * @param key 对象的键
     * @param value 对象的值
     * @return ResultResponse
     */
    public ResultResponse success(String key, Object value) {
    	Map<String, Object> map= new HashMap<String, Object>();
    	map.put(key, value);
    	ResultResponse resultResponse = new ResultResponse();
    	resultResponse.setMeta(new Meta(ResultMessage.SUCCESS));
    	resultResponse.setData(map);
        return resultResponse;
    }
    
    /**
     * 操作成功(自定义消息)
     * @param resultMessage 消息对象
     * @param args 格式化对象数组
     * @return ResultResponse
     */
    public ResultResponse success(ResultMessage resultMessage, Object... args) {
    	ResultResponse resultResponse = new ResultResponse();
    	resultResponse.setMeta(new Meta(resultMessage.getCode(), String.format(resultMessage.getMessage().contains("%s")? resultMessage.getMessage() : resultMessage.getMessage()+"，%s", args)));
    	resultResponse.setData(null);
        return resultResponse;
    }
	
    /**
     * 操作失败
     * @return ResultResponse
     */
    public ResultResponse failure() {
    	ResultResponse resultResponse = new ResultResponse();
    	resultResponse.setMeta(new Meta(ResultMessage.FAILURE));
    	resultResponse.setData(null);
        return resultResponse;
    }
    
    /**
     * 操作失败(格式化消息)
     * @return ResultResponse
     */
    public ResultResponse failure(ServiceException ex) {
    	ResultResponse resultResponse = new ResultResponse();
    	resultResponse.setMeta(new Meta(ex.getResultMessage().getCode(), ex.getMessage()));
    	resultResponse.setData(null);
        return resultResponse;
    }
    
    /**
     * 操作失败(失败原因/格式化消息)
     * @param resultMessage 消息对象
     * @param args 格式化对象数组
     * @return ResultResponse
     */
    public ResultResponse failure(ResultMessage resultMessage, Object... args) {
    	ResultResponse resultResponse = new ResultResponse();
    	resultMessage = resultMessage!=null? resultMessage: ResultMessage.FAILURE;
    	resultResponse.setMeta(new Meta(resultMessage.getCode(), String.format(resultMessage.getMessage().contains("%s")? resultMessage.getMessage() : resultMessage.getMessage()+"，%s", args)));
    	resultResponse.setData(null);
        return resultResponse;
    }
    
    
	public Meta getMeta() {
		return meta;
	}

	public Object getData() {
		return data;
	}
    
    public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public void setData(Object data) {
		this.data = data;
	}

	class Meta {

        private String message;
        private int code;
        
		public Meta(){}

		public Meta(int code) {
			this.code = code;
        }
		
		public Meta(ResultMessage resultMessage) {
            this.code = resultMessage.getCode();
            this.message = resultMessage.getMessage();
        }
		
        public Meta(int code, String message) {
            this.code = code;
            this.message = message;
        }

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
        
    }
}

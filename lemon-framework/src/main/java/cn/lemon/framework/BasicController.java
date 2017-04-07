package cn.lemon.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.lemon.framework.response.ResultResponse;

public class BasicController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	/** 返回结果集 */
	protected ResultResponse resultResponse = new ResultResponse();

	/** Token名称 */
	protected final static String TOKEN = "x-auth-token";
}

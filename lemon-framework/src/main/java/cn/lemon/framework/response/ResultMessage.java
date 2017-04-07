package cn.lemon.framework.response;


/**
 * 返回消息伪枚举 - 使用时可以直接继承扩展
 * @author lonyee
 *
 */
public class ResultMessage {
	
	/** 操作成功 */
	public static final ResultMessage SUCCESS = new ResultMessage(2000, "操作成功");
	/** 操作失败 */
	public static final ResultMessage FAILURE = new ResultMessage(4020, "操作失败");
	/** 服务器无法理解此请求 */
	public static final ResultMessage F4000 = new ResultMessage(4000, "服务器无法理解此请求");
	/** [服务器]运行时异常 */
	public static final ResultMessage F4001 = new ResultMessage(4001, "[服务器]运行时异常");
	/** [服务器]空值异常 */
	public static final ResultMessage F4002 = new ResultMessage(4002, "[服务器]空值异常");
	/** [服务器]数据类型转换异常 */
	public static final ResultMessage F4003 = new ResultMessage(4003, "[服务器]数据类型转换异常");
	/** [服务器]IO异常 */
	public static final ResultMessage F4004 = new ResultMessage(4004, "[服务器]IO异常");
	/** [服务器]未知方法异常 */
	public static final ResultMessage F4005 = new ResultMessage(4005, "[服务器]未知方法异常");
	/** [服务器]数组越界异常 */
	public static final ResultMessage F4006 = new ResultMessage(4006, "[服务器]数组越界异常");
	/** [服务器]网络异常 */
	public static final ResultMessage F4007 = new ResultMessage(4007, "[服务器]网络异常");
	/** 访问超时，请重新登陆 */
	public static final ResultMessage F4010 = new ResultMessage(401, "访问超时，请重新登陆");
	/** 不支持的媒体类型 */
	public static final ResultMessage F4150 = new ResultMessage(4150, "不支持的媒体类型");
	/** 没有页面访问权限 */
	public static final ResultMessage F4030 = new ResultMessage(403, "没有页面访问权限");
	/** 没有找到请求的接口资源 */
	public static final ResultMessage F4040 = new ResultMessage(4040, "没有找到请求的接口资源");
	/** 不支持的请求方法 GET,POST */
	public static final ResultMessage F4050 = new ResultMessage(4050, "不支持的请求方法");
	/** 客户端不接受所请求的 MIME类型 */
	public static final ResultMessage F4060 = new ResultMessage(4060, "客户端不接受所请求的MIME类型");
	/** 服务器内部异常 */
	public static final ResultMessage F5000 = new ResultMessage(5000, "服务器内部异常");
	
	//****** 2000操作正常的提示 ***********//
	/** 密码重置成功，新密码为 %s */
	public static final ResultMessage F2021 = new ResultMessage(2000, "密码重置成功，新密码为 %s ");
	
	//****** 操作异常的提示 ***************//
	/** 用户名或密码错误 */
	public static final ResultMessage F5001 = new ResultMessage(5001, "用户名或密码错误");
	/** 当前登录密码错误 */
	public static final ResultMessage F5002 = new ResultMessage(5002, "当前登录密码错误");
	/** 手机号错误 */
	public static final ResultMessage F5003 = new ResultMessage(5003, "手机号错误");
	/** 验证码错误 */
	public static final ResultMessage F5004 = new ResultMessage(5004, "验证码错误");
	/** 用户名不能为空 */
	public static final ResultMessage F5005 = new ResultMessage(5005, "用户名不能为空");
	/** 用户名已存在 */
	public static final ResultMessage F5006 = new ResultMessage(5006, "用户名已存在");
	/** 授权信息获取失败 */
	public static final ResultMessage F5007 = new ResultMessage(5007, "授权信息获取失败");
	/** 授权用户信息获取失败 */
	public static final ResultMessage F5008 = new ResultMessage(5008, "授权用户信息获取失败");
	/** 网页授权票据获取失败 */
	public static final ResultMessage F5009 = new ResultMessage(5009, "网页授权票据获取失败");
	/** 模板消息发送失败 */
	public static final ResultMessage F5010 = new ResultMessage(5010, "模板消息发送失败");
	/** 认证URL获取失败 */
	public static final ResultMessage F5011 = new ResultMessage(5011, "认证URL获取失败");
	/** 1小时内最多只能发送%s次验证码 */
	public static final ResultMessage F5020 = new ResultMessage(5020, "1小时内最多能发送%s次验证码");
	/** 手机验证码发送失败 */
	public static final ResultMessage F5021 = new ResultMessage(5021, "手机验证码发送失败");
	/** 存在子节点数据 */
	public static final ResultMessage F6002 = new ResultMessage(6002, "删除失败，存在子节点数据");
	/** 数据签名验证失败 */
	public static final ResultMessage F8001 = new ResultMessage(8001, "数据签名验证失败");
	/** 调起支付失败，错误代码：%s，错误信息：%s*/
	public static final ResultMessage F8002 = new ResultMessage(8002, "调起支付失败，错误代码：%s，错误信息：%s");
	/** 调起支付失败 %s*/
	public static final ResultMessage F8003 = new ResultMessage(8003, "调起支付失败 %s");
	/** 支付失败，错误代码：%s，错误信息：%s*/
	public static final ResultMessage F8004 = new ResultMessage(8004, "支付失败，错误代码：%s，错误信息：%s");
	/** 支付失败 %s*/
	public static final ResultMessage F8005 = new ResultMessage(8005, "支付失败 %s");
	
	/** 提现转账失败，错误代码：%s，错误信息：%s*/
	public static final ResultMessage F8012 = new ResultMessage(8012, "提现转账失败，错误代码：%s，错误信息：%s");
	/** 提现转账失败 %s*/
	public static final ResultMessage F8013 = new ResultMessage(8013, "提现转账失败 %s");
	/** 发放红包失败，错误代码：%s，错误信息：%s*/
	public static final ResultMessage F8022 = new ResultMessage(8022, "发放红包失败，错误代码：%s，错误信息：%s");
	/** 发放红包失败 %s*/
	public static final ResultMessage F8023 = new ResultMessage(8023, "发放红包失败 %s");
	/** 退款失败，错误代码：%s，错误信息：%s*/
	public static final ResultMessage F8032 = new ResultMessage(8032, "退款失败，错误代码：%s，错误信息：%s");
	/** 退款失败 %s*/
	public static final ResultMessage F8033 = new ResultMessage(8033, "退款失败 %s");
	
	private int code;
	private String message;
	protected ResultMessage(int code, String message){
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

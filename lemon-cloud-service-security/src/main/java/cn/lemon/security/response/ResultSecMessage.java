package cn.lemon.security.response;

import cn.lemon.framework.response.ResultMessage;

/**
 * 返回消息码
 * Created by lonyee on 2017/4/17.
 */
public class ResultSecMessage extends ResultMessage {

    /** 用户名或密码错误 */
    public static final ResultMessage F5001 = new ResultSecMessage(5001, "用户名或密码错误");
    /** 当前登录密码错误 */
    public static final ResultMessage F5002 = new ResultSecMessage(5002, "当前登录密码错误");
    /** 手机号错误 */
    public static final ResultMessage F5003 = new ResultSecMessage(5003, "手机号错误");
    /** 验证码错误 */
    public static final ResultMessage F5004 = new ResultSecMessage(5004, "验证码错误");
    /** 用户名不能为空 */
    public static final ResultMessage F5005 = new ResultSecMessage(5005, "用户名不能为空");
    /** 用户名已存在 */
    public static final ResultMessage F5006 = new ResultSecMessage(5006, "用户名已存在");
    /** 用户名已存在 */
    public static final ResultMessage F5007 = new ResultSecMessage(5007, "账号已被禁用");
    /** 授权信息获取失败 */
    public static final ResultMessage F5008 = new ResultSecMessage(5008, "授权信息获取失败");
    /** 授权用户信息获取失败 */
    public static final ResultMessage F5009 = new ResultSecMessage(5009, "授权用户信息获取失败");
    /** 网页授权票据获取失败 */
    public static final ResultMessage F5010 = new ResultSecMessage(5010, "网页授权票据获取失败");
    /** 认证URL获取失败 */
    public static final ResultMessage F5011 = new ResultSecMessage(5011, "认证URL获取失败");
    /** 1小时内最多只能发送%s次验证码 */
    public static final ResultMessage F5020 = new ResultSecMessage(5020, "1小时内最多能发送%s次验证码");
    /** 手机验证码发送失败 */
    public static final ResultMessage F5021 = new ResultSecMessage(5021, "手机验证码发送失败");

    protected ResultSecMessage(int code, String message) {
        super(code, message);
    }
}

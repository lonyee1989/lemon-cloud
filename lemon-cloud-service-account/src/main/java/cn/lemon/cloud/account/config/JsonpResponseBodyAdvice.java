package cn.lemon.cloud.account.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * 加入对jsonp的支持
 * @author lonyee
 */
@ControllerAdvice(basePackages = "cn.lemon.fileservice.controller")
public class JsonpResponseBodyAdvice  extends AbstractJsonpResponseBodyAdvice {
	private final Log logger = LogFactory.getLog(getClass());
    private final String[] jsonpQueryParamNames;

    public JsonpResponseBodyAdvice() {
        super("callback"); //"jsonp"
        this.jsonpQueryParamNames = new String[]{"callback"};
    }

    /** 
     * 重写callback方法，使即支持jsonp格式也支持json格式
     */
    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
                                           MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {

        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();

        //如果不存在callback这个请求参数，直接返回，不需要处理为jsonp
        if (ObjectUtils.isEmpty(servletRequest.getParameter("callback"))) {
            return;
        }
        //按设定的请求参数(JsonAdvice构造方法中的this.jsonpQueryParamNames = new String[]{"callback"};)，处理返回结果为jsonp格式
        for (String name : this.jsonpQueryParamNames) {
            String value = servletRequest.getParameter(name);
            if (value != null) {
            	if (!isValidJsonpQueryParam(value)) {
					if (logger.isDebugEnabled()) {
						logger.debug("Ignoring invalid jsonp parameter value: " + value);
					}
					continue;
				}
                MediaType contentTypeToUse = getContentType(contentType, request, response);
                response.getHeaders().setContentType(contentTypeToUse);
                bodyContainer.setJsonpFunction(value);
                return;
            }
        }
    }
}
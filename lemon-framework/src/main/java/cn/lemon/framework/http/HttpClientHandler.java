package cn.lemon.framework.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.lemon.framework.utils.StringUtil;

@SuppressWarnings("deprecation")
public class HttpClientHandler
{
	private static Logger logger = LoggerFactory.getLogger(HttpClientHandler.class);

    //连接超时时间，默认10秒
    private static int socketTimeout = 10000;
    //传输超时时间，默认30秒
    private static int connectTimeout = 30000;
    
    /**
	 * GET接口网络请求
	 * @param url 调用的地址
	 * @return String
	 */
	public static String get(String url) throws IOException {
		return get(url, null);
	}
	
	/**
	 * GET接口网络请求
	 * @param url 调用的地址
	 * @param params 参数
	 * @return String
	 * @throws IOException
	 */
	public static String get(String url, Map<String, String> params) throws IOException {
		String result = "";
		if (null != params) {
	        StringBuffer buffer = new StringBuffer();
			for(Entry<String, String> entry: params.entrySet()) {
	        	buffer.append("&");
				buffer.append(entry.getKey());
	        	buffer.append("=");
	        	buffer.append(entry.getValue());
	        }
			url += String.format("%s%s", url.contains("?")? "&": "?", buffer.length()>=1? buffer.deleteCharAt(0).toString(): "");
		}
		HttpGet httpGet = new HttpGet(url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();//设置请求和传输超时时间
		httpGet.setConfig(requestConfig);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity(); 
			/* 读返回数据 */
			result = EntityUtils.toString(entity, "UTF-8");
			logger.debug("get url: {}, result: {}", url, result);

		} catch (ConnectionPoolTimeoutException e) {
			logger.error("http get throw ConnectionPoolTimeoutException(wait time out)", e);
		} catch (ConnectTimeoutException e) {
			logger.error("http get throw ConnectTimeoutException", e);
		} catch (SocketTimeoutException e) {
			logger.error("http get throw SocketTimeoutException", e);
		} catch (Exception e) {
			logger.error("http get throw Exception", e);
		} finally {
			httpGet.abort();
			httpClient.close();
		}
		return result;
	}
	
	/**
	 * POST接口网络请求（JSON传输格式）
	 * @param url 调用的地址
	 * @param json JSON参数
	 * @return String
	 * @throws IOException
	 */
	public static String post(String url, String json) throws IOException {
		String result = "";
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();//设置请求和传输超时时间
		StringEntity strEntity = new StringEntity(json);
		strEntity.setContentEncoding("UTF-8");
		strEntity.setContentType("application/json");
		httpPost.setEntity(strEntity);
		httpPost.setConfig(requestConfig);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity(); 
			/* 读返回数据 */
			result = EntityUtils.toString(entity, "UTF-8");
			logger.debug("post url: {}, result: {}", url, result);

		} catch (ConnectionPoolTimeoutException e) {
			logger.error("http post throw ConnectionPoolTimeoutException(wait time out)", e);
		} catch (ConnectTimeoutException e) {
			logger.error("http post throw ConnectTimeoutException", e);
		} catch (SocketTimeoutException e) {
			logger.error("http post throw SocketTimeoutException", e);
		} catch (Exception e) {
			logger.error("http post throw Exception", e);
		} finally {
			httpPost.abort();
			httpClient.close();
		}
		return result;
	}	
	
	/**
	 * POST接口网络请求
	 * @param url 调用的地址
	 * @param params 参数
	 * @return String
	 * @throws IOException
	 */
	public static String post(String url, Map<String, String> params) throws IOException {
		String result = "";
		HttpPost httpPost = new HttpPost(url);
		if (null != params) {
			List <NameValuePair> pairs = new ArrayList<NameValuePair>();
			for(Entry<String, String> entry: params.entrySet()) {
				pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
		}
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();//设置请求和传输超时时间
		httpPost.setConfig(requestConfig);
		httpPost.addHeader("Content-type", "application/x-www-form-urlencoded");
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity(); 
			/* 读返回数据 */
			result = EntityUtils.toString(entity, "UTF-8");
			logger.debug("post url: {}, result: {}", url, result);

		} catch (ConnectionPoolTimeoutException e) {
			logger.error("http post throw ConnectionPoolTimeoutException(wait time out)", e);
		} catch (ConnectTimeoutException e) {
			logger.error("http post throw ConnectTimeoutException", e);
		} catch (SocketTimeoutException e) {
			logger.error("http post throw SocketTimeoutException", e);
		} catch (Exception e) {
			logger.error("http post throw Exception", e);
		} finally {
			httpPost.abort();
			httpClient.close();
		}
		return result;
	}
	
	public static String postXml(String url, Map<String, String> params) throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		return post(httpClient, url, params);
	}
	
	public static String postWithSSL(String url, Map<String, String> params, String certPath, String certPassword) throws IOException, KeyManagementException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
		CloseableHttpClient httpClient = getHttpClientSSL(certPath, certPassword);
		return post(httpClient, url, params);
	}
	
	private static String post(CloseableHttpClient httpClient, String url, Map<String, String> params) throws IOException {
		
        String result = null;

        HttpPost httpPost = new HttpPost(url);
        //将要提交给API的数据对象转换成XML格式数据Post给API
        String postDataXML = StringUtil.getXmlString(params);
        System.out.println("POST过去的数据是：");
        System.out.println(postDataXML);

        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);

		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();//设置请求和传输超时时间
        httpPost.setConfig(requestConfig);
        try {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
			logger.debug("post url: {}, result: {}", url, result);
        } catch (ConnectionPoolTimeoutException e) {
        	logger.error("http post throw ConnectionPoolTimeoutException(wait time out)", e);
        } catch (ConnectTimeoutException e) {
        	logger.error("http post throw ConnectTimeoutException", e);
        } catch (SocketTimeoutException e) {
        	logger.error("http post throw SocketTimeoutException", e);
        } catch (Exception e) {
        	logger.error("http post throw Exception", e);
        } finally {
            httpPost.abort();
    		httpClient.close();
        }
        return result;
	}
	
	private static CloseableHttpClient getHttpClientSSL(String certPath, String certPassword) throws KeyStoreException, IOException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
        //FileInputStream instream = new FileInputStream(new File(Constants.WX_CERT_PATH));//加载本地的证书进行https加密传输
    	InputStream instream = HttpClientHandler.class.getClassLoader().getResourceAsStream(certPath);
        char[] passwordArray = certPassword.toCharArray();
        try {
            keyStore.load(instream, passwordArray);//设置证书密码
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            instream.close();
        }
        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, passwordArray).build();
        // Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        return httpClient;
	}
	
	public static String buildForm(String baseUrl, Map<String, String> parameters) {
        java.lang.StringBuffer sb = new StringBuffer();
        sb.append("<form name=\"punchout_form\" method=\"post\" action=\"");
        sb.append(baseUrl);
        sb.append("\">\n");
        for(Entry<String, String> entrySet: parameters.entrySet()) {
        	if (entrySet.getValue()!=null && !entrySet.getValue().isEmpty()) {
        		sb.append(buildHiddenField(entrySet.getKey(), entrySet.getValue()));
        	}
        }
        sb.append("<input type=\"submit\" value=\"立即支付\" style=\"display:none\" >\n");
        sb.append("</form>\n");
        sb.append("<script>document.forms[0].submit();</script>");
        java.lang.String form = sb.toString();
        return form;
    }

	private static String buildHiddenField(String key, String value) {
        java.lang.StringBuffer sb = new StringBuffer();
        sb.append("<input type=\"hidden\" name=\"");
        sb.append(key);

        sb.append("\" value=\"");
        //转义双引号
        String a = value.replace("\"", "&quot;");
        sb.append(a).append("\">\n");
        return sb.toString();
    }
}

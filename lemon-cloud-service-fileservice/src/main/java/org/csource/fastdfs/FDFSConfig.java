package org.csource.fastdfs;

import com.google.common.base.Strings;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by lonyee on 2017/4/13.
 * FastDFS配置
 */
@ConfigurationProperties(prefix = "fdfs")
public class FDFSConfig {
    private Integer connectTimeout = 0;
    private Integer networkTimeout = 0;
    private String charset;
    private Integer httpTrackerPort = 80;
    private Boolean httpAntiStealToken;
    private String httpSecretKey;
    private String[] trackerServers;

    public Integer getConnectTimeout() {
        return (connectTimeout < 0) ? 5: connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getNetworkTimeout() {
        return (networkTimeout < 0) ? 30: networkTimeout;
    }

    public void setNetworkTimeout(Integer networkTimeout) {
        this.networkTimeout = networkTimeout;
    }

    public String getCharset() {
        return Strings.isNullOrEmpty(charset)? "ISO8859-1": charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public Integer getHttpTrackerPort() {
        return (httpTrackerPort <= 0) ? 80: httpTrackerPort;
    }

    public void setHttpTrackerPort(Integer httpTrackerPort) {
        this.httpTrackerPort = httpTrackerPort;
    }

    public Boolean getHttpAntiStealToken() {
        return (httpAntiStealToken == null) ? false: httpAntiStealToken;
    }

    public void setHttpAntiStealToken(Boolean httpAntiStealToken) {
        this.httpAntiStealToken = httpAntiStealToken;
    }

    public String getHttpSecretKey() {
        return httpSecretKey;
    }

    public void setHttpSecretKey(String httpSecretKey) {
        this.httpSecretKey = httpSecretKey;
    }

    public String[] getTrackerServers() {
        return trackerServers;
    }

    public void setTrackerServers(String[] trackerServers) {
        this.trackerServers = trackerServers;
    }
}

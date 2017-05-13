package cn.lemon.security.service;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 客户授权服务接口
 * Created by lonyee on 2017/4/14.
 */
@Service
public class ClientService extends JdbcClientDetailsService {

    public ClientService(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        System.out.println("loadClientbyClientId is  oking.......");
        return super.loadClientByClientId(clientId);

        /*BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId(clientId);
        clientDetails.setClientSecret("whc_client_secret");

        LinkedList<String> scope = new LinkedList<String>();
        scope.add("whc");
        clientDetails.setScope(scope);

        Set<String> registeredRedirectUris = new HashSet<String>();
        registeredRedirectUris.add("http://localhost:8080/test");
        clientDetails.setRegisteredRedirectUri(registeredRedirectUris);

        LinkedList<String> grant_types = new LinkedList<String>();
        grant_types.add("client_credentials");
        clientDetails.setAuthorizedGrantTypes(grant_types);

        clientDetails.setAccessTokenValiditySeconds(24 * 60 * 60);
        clientDetails.setRefreshTokenValiditySeconds(48 * 60 * 60);

        LinkedList<String> autoApproveScopes = new LinkedList<String>();
        autoApproveScopes.add("whc");
        clientDetails.setAutoApproveScopes(autoApproveScopes);

        return clientDetails;*/
    }
}

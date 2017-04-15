package cn.lemon.security.service;

import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 客户授权服务接口
 * Created by lonyee on 2017/4/14.
 */
public class ClientService implements ClientDetailsService {

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        BaseClientDetails clientDetails = new BaseClientDetails();
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

        return clientDetails;
    }
}

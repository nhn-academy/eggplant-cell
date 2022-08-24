package com.nhnacademy.eggplantcell.config;

import com.nhnacademy.eggplantcell.dto.response.SecureKeyResponseDto;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Objects;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 설명작성란
 *
 * @author : 김보민
 * @since 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "secure-key-manager")
public class DataProtectionConfig {
    private String url;
    private String appKey;
    private String localKey;
    private String userInfoProtectionKey;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getLocalKey() {
        return localKey;
    }

    public void setLocalKey(String localKey) {
        this.localKey = localKey;
    }

    public String getUserInfoProtectionKey() {
        return userInfoProtectionKey;
    }

    public void setUserInfoProtectionKey(String userInfoProtectionKey) {
        this.userInfoProtectionKey = userInfoProtectionKey;
    }

    String findSecretDataFromSecureKeyManager(final String keyId) {
        try {
            KeyStore clientStore = KeyStore.getInstance("PKCS12");
            Resource resource = new ClassPathResource("/github-action.p12");
            clientStore.load(resource.getInputStream(), localKey.toCharArray());

            SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
            sslContextBuilder.setProtocol("TLS");
            sslContextBuilder.loadKeyMaterial(clientStore, localKey.toCharArray());
            sslContextBuilder.loadTrustMaterial(new TrustSelfSignedStrategy());

            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                    sslContextBuilder.build());
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLSocketFactory(sslConnectionSocketFactory)
                    .build();
            HttpComponentsClientHttpRequestFactory requestFactory =
                    new HttpComponentsClientHttpRequestFactory(httpClient);

            return Objects.requireNonNull(new RestTemplate(requestFactory)
                            .getForEntity(url + "/keymanager/v1.0/appkey/{appkey}/secrets/{keyid}",
                                    SecureKeyResponseDto.class,
                                    this.appKey,
                                    keyId)
                            .getBody())
                    .getBody()
                    .getSecret();
        } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException
                | UnrecoverableKeyException | IOException | KeyManagementException e) {
            throw new RuntimeException();
        }
    }

    @Bean
    public String userInformationProtectionValue() {
        return findSecretDataFromSecureKeyManager(userInfoProtectionKey);
    }
}

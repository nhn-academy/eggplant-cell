package com.nhnacademy.eggplantcell.config;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 설명작성란
 *
 * @author : 김보민
 * @since 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "datasource")
public class DataSourceConfig {
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    @Bean
    public DataSource getDataSource(DataProtectionConfig dataProtectionConfig) {
        String secretUrl = dataProtectionConfig.findSecretDataFromSecureKeyManager(url);
        String secretPassword = dataProtectionConfig.findSecretDataFromSecureKeyManager(password);

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.url(secretUrl);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(secretPassword);

        return dataSourceBuilder.build();
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

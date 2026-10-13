package com.fabio.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Bean
    public DataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setJdbcUrl(url);
        config.setPassword(password);
        config.setDriverClassName(driver);

        config.setPoolName("library-db-pool");
        config.setMaximumPoolSize(10);             // máximo de conexões liberadas
        config.setMinimumIdle(1);                  // tamanho inicial de pool
        config.setMaxLifetime(600000);             // tempo maximo de vida (10 minutos)
        config.setConnectionTimeout(100000);       // timeout para conseguir uma conexao
        config.setConnectionTestQuery("SELECT 1"); // query para testar conexao com o banco

        return new HikariDataSource(config);
    }
}

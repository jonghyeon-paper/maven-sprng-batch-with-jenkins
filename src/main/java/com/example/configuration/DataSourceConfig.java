package com.example.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment environment;
    
    @Bean
    @Primary
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL)
//                .addScript("classpath:hsqlDB/create-db.sql")
//                .addScript("classpath:hsqlDB/insert-db.sql")
//                .addScript("classpath:schema-all.sql")
                .build();
        return db;
    }
    
    @Bean(value = "bsinessDataSource")
    public DataSource dataSource2() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("business.datasource.driver"));
        dataSource.setUrl(environment.getProperty("business.datasource.url"));
        dataSource.setUsername(environment.getProperty("business.datasource.username"));
        dataSource.setPassword(environment.getProperty("business.datasource.password"));
        return dataSource;
    }
    
    /*
    @Bean
    public Log4jdbcProxyDataSource dataSourceLog(DataSource dataSource) {
        Log4JdbcCustomFormatter logFormatter = new Log4JdbcCustomFormatter();
        logFormatter.setLoggingType(LoggingType.MULTI_LINE);
        logFormatter.setSqlPrefix("SQL  :: ");
        
        Log4jdbcProxyDataSource querylog = new Log4jdbcProxyDataSource(dataSource);
        querylog.setLogFormatter(logFormatter);
        
        return querylog;
    }
    */
}

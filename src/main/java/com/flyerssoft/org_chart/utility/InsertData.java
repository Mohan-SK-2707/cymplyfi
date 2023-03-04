package com.flyerssoft.org_chart.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class InsertData {

    @Autowired
    private DataSource dataSource;

    @Bean
    public DataSourceInitializer dataSourceInitializer(){
      DataSourceInitializer initializer = new DataSourceInitializer();
      initializer.setDataSource(dataSource);
        ResourceDatabasePopulator populate = new ResourceDatabasePopulator();
        populate.addScript(new ClassPathResource("data.sql"));
         initializer.setDatabasePopulator(populate);
         return initializer;
    }
}

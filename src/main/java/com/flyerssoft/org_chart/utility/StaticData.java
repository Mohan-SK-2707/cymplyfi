package com.flyerssoft.org_chart.utility;

import com.flyerssoft.org_chart.repo.EmployeeBankDetailsRepo;
import com.flyerssoft.org_chart.repo.EmployeeDetailRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class StaticData {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EmployeeBankDetailsRepo employeeBankDetailsRepo;

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        if (CollectionUtils.isEmpty(employeeBankDetailsRepo.findAll())) {
            DataSourceInitializer initializer = new DataSourceInitializer();
            initializer.setDataSource(dataSource);
            ResourceDatabasePopulator populate = new ResourceDatabasePopulator();
            populate.addScript(new ClassPathResource("data.sql"));
            initializer.setDatabasePopulator(populate);
            return initializer;
        } else {
            DataSourceInitializer initializer = new DataSourceInitializer();
            initializer.setDataSource(dataSource);
            return initializer;
        }
    }
}

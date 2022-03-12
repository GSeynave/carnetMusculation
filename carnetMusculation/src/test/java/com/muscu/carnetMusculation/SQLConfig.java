package com.muscu.carnetMusculation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@EnableJpaRepositories(basePackages = "com.carnetMusculation.services")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class SQLConfig {

}

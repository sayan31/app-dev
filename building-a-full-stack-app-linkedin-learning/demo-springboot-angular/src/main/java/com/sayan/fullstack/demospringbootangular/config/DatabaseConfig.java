package com.sayan.fullstack.demospringbootangular.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("com.sayan.fullstack.demospringbootangular.repository")
@EnableTransactionManagement
public class DatabaseConfig {

}

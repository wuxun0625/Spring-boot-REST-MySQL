package com.oracle.microsvctk.demo.read.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import javax.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.oracle.microsvctk.demo.read.repository", 
    entityManagerFactoryRef = "heroesReadEntityManager", 
    transactionManagerRef = "heroesReadTransactionManager"
)
public class HeroesReadConfig {
     
    @Bean(name = "heroesReadEntityManager")
    public LocalContainerEntityManagerFactoryBean heroesReadEntityManager(
        EntityManagerFactoryBuilder builder, @Qualifier("dataSource") DataSource dataSource) {
      return builder.dataSource(dataSource).packages("com.oracle.microsvctk.demo.read.model").persistenceUnit("HeroesRead")
          .build();
  }
 
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.read")
    public DataSource dataSource() {
      return DataSourceBuilder.create().build();
    }
 
    @Bean(name = "heroesReadTransactionManager")
    public PlatformTransactionManager heroesReadTransactionManager(
        @Qualifier("heroesReadEntityManager") EntityManagerFactory heroesReadEntityManager) {
      return new JpaTransactionManager(heroesReadEntityManager);
  }
  
}
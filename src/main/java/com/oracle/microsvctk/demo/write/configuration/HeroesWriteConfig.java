package com.oracle.microsvctk.demo.write.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
    basePackages = "com.oracle.microsvctk.demo.write.repository", 
    entityManagerFactoryRef = "heroesWriteEntityManager", 
    transactionManagerRef = "heroesWriteTransactionManager"
)
public class HeroesWriteConfig {
     
    @Primary
    @Bean(name = "heroesWriteEntityManager")
    public LocalContainerEntityManagerFactoryBean heroesWriteEntityManager(
        EntityManagerFactoryBuilder builder, @Qualifier("dataSourceWrite") DataSource dataSourceWrite) {
      return builder.dataSource(dataSourceWrite).packages("com.oracle.microsvctk.demo.write.model").persistenceUnit("HeroesWrite")
          .build();
  }
 
    @Primary
    @Bean(name = "dataSourceWrite")
    @ConfigurationProperties(prefix = "spring.datasource.write")
    public DataSource dataSource() {
      return DataSourceBuilder.create().build();
    }
 
    @Primary
    @Bean(name = "heroesWriteTransactionManager")
    public PlatformTransactionManager heroesWriteTransactionManager(
        @Qualifier("heroesWriteEntityManager") EntityManagerFactory heroesWriteEntityManager) {
      return new JpaTransactionManager(heroesWriteEntityManager);
  }
  
}
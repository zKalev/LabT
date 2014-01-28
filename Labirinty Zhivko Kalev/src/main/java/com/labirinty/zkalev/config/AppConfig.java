package com.labirinty.zkalev.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.jolbox.bonecp.BoneCPDataSource;
import com.labirinty.zkalev.services.GoodsService;
import com.labirinty.zkalev.services.impl.GoodsServiceImpl;

@Configuration
@ComponentScan(basePackages = { "com.labirinty.zkalev" })
@EnableJpaRepositories(basePackages = "com.labirinty.zkalev.dao")
public class AppConfig {

	// @Value("${db.host}")
	private String dbHost = "localhost:3306";

	// @Value("${db.name}")
	private String dbName = "goods";

	// @Value("${db.username}")
	private String dbUsername = "root";

	// @Value("${db.password}")
	private String dbPassword = "zkalev";

	@Bean
	public GoodsService getGootsService() {
		return new GoodsServiceImpl();
	}

	@Bean
	public DataSource dataSource() {
		BoneCPDataSource dataSource = new BoneCPDataSource();
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://" + dbHost + "/" + dbName);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		dataSource.setIdleConnectionTestPeriodInMinutes(60L);
		dataSource.setIdleMaxAgeInMinutes(240L);
		dataSource.setPartitionCount(1);
		dataSource.setMaxConnectionsPerPartition(1);
		dataSource.setAcquireIncrement(5);
		dataSource.setStatementsCacheSize(10);
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(dataSource);
		lef.setJpaVendorAdapter(jpaVendorAdapter);
		lef.setPackagesToScan("com.labirinty.zkalev.dao");
		return lef;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(false);
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		return hibernateJpaVendorAdapter;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}
}

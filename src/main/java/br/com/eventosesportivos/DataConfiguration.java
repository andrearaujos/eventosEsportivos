package br.com.eventosesportivos;

import javax.activation.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/*
@Configuration
public class DataConfiguration {

	
	spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

spring.datasource.username=postgres
spring.datasource.password=admin
spring.datasource.url=jdbc:postgresql://localhost:5432/EveEsp
spring.jpa.hibernate.ddl-auto=update
spring.jpa.hibernate.show-sql=true 
	
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.postgresql.jdbc.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/EveEsp");
		dataSource.setUsername("postgres");
		dataSource.setPassword("admin");
		return (DataSource) dataSource;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.POSTGRESQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
		adapter.setPrepareConnection(true);
		return adapter;
	}
	
	
	
}
 */

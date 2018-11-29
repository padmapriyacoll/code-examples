package com.priya.bank.bankdemo;

	import javax.sql.DataSource;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.ComponentScan;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.jdbc.core.JdbcTemplate;
	import org.springframework.jdbc.datasource.DriverManagerDataSource;
	


	@Configuration
	@ComponentScan(basePackages="com.priya.bank.bankdemo")
    public class AppConfig{
		
		@Bean
		public DataSource datasource() {
			
			DriverManagerDataSource ds =new DriverManagerDataSource(); 
			ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
			ds.setUrl("jdbc:mysql://localhost:3306/bankdemo?serverTimezone=UTC");
			ds.setUsername("root");
			ds.setPassword("AdminPassword100");	
			return ds;
		}
		
		@Bean
		public JdbcTemplate jdbctemplate(DataSource ds) {
			
			return new JdbcTemplate(ds);
		} 

}

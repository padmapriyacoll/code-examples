package com.priya.jpa.jpademo;

import java.util.Properties;

import javax.annotation.Resources;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@Configuration
@ComponentScan
@PropertySource(value= {"classpath:/application.properties"})
public class PersistenceConfig{
	
	@Autowired
	Environment env;	
	
	@Bean
	public DataSource datasource() {
		
		DriverManagerDataSource ds =new DriverManagerDataSource(); 
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/jpademo?serverTimezone=UTC");
		ds.setUsername("root");
		ds.setPassword("AdminPassword100");	
		return ds;
    }
	
	private Properties getHibernateProperties() {
        Properties jpaProperties = new Properties();
        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
         jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));

        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
         jpaProperties.put("hibernate.hbm2ddl.auto", 
         env.getRequiredProperty("hibernate.hbm2ddl.auto"));

      //Configures the naming strategy that is used when Hibernate creates
      //new database objects and schema elements
       jpaProperties.put("hibernate.ejb.naming_strategy", 
       env.getRequiredProperty("hibernate.ejb.naming_strategy")
       );

       //If the value of this property is true, Hibernate writes all SQL
       //statements to the console.
       jpaProperties.put("hibernate.show_sql", 
       env.getRequiredProperty("hibernate.show_sql")
       );

        //If the value of this property is true, Hibernate will format the SQL
       //that is written to the console.
        jpaProperties.put("hibernate.format_sql",  env.getRequiredProperty("hibernate.format_sql"));
        return jpaProperties;

    }
	
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {	
	
	
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(datasource());
    entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    entityManagerFactoryBean.setPackagesToScan("com.priya.jpa.jpademo.entity");
    entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

    return  entityManagerFactoryBean;
   }
	
  @Bean
  public PlatformTransactionManager getTransactionManager()
  {
	  JpaTransactionManager jtm= new JpaTransactionManager( );
      jtm.setEntityManagerFactory(entityManagerFactory().getObject());
	  return jtm;
  }
  
 /* @Autowired
  @Bean(name = "hibernate5AnnotatedSessionFactory")
  LocalSessionFactoryBuilder getLocalSessionFactoryBean() {
      LocalSessionFactoryBuilder localSessionFactoryBean = new LocalSessionFactoryBuilder(datasource());
      localSessionFactoryBean.scanPackages( "com.priya.jpa.jpademo.entity"
      );
      localSessionFactoryBean.addProperties(getHibernateProperties());
      localSessionFactoryBean.buildSessionFactory();

      return localSessionFactoryBean;
  }
*/
  

	
}

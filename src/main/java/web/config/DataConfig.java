package web.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan("web")
public class DataConfig {

        @Autowired
        private Environment environment;

        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
            LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
            factoryBean.setDataSource(dataSource());
            factoryBean.setPackagesToScan("web.model");
            factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            factoryBean.setJpaProperties(hibernateProperties());
            return factoryBean;
        }

        @Bean
        public DataSource dataSource() {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(environment.getProperty("db.driver"));
            dataSource.setUsername(environment.getProperty("db.user"));
            dataSource.setPassword(environment.getProperty("db.pass"));
            dataSource.setUrl(environment.getProperty("db.url"));
            return dataSource;
        }

        @Bean
        public Properties hibernateProperties() {
            Properties properties = new Properties();
            properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
            properties.put("hibernate.show_sql", "true");
            properties.put("hibernate.hbm2ddl.auto", "create-drop");
            return properties;
        }

        @Bean
        public PlatformTransactionManager transactionManager() {
            JpaTransactionManager managerTx = new JpaTransactionManager();
            managerTx.setEntityManagerFactory(entityManagerFactory().getObject());
            return managerTx;
        }
}

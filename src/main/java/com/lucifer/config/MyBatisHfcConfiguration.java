package com.lucifer.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 *
 * 获取第二个数据库的连接信息，在application.yml中配置，并指定特定的前缀
 *
 */
@Configuration
@AutoConfigureAfter({ DataBaseHfcConfiguration.class })

public class MyBatisHfcConfiguration implements EnvironmentAware{

    private  Log logger = LogFactory.getLog(this.getClass());

    private RelaxedPropertyResolver propertyResolver;

    @Autowired
    @Qualifier("hfcDataSource")
    protected DataSource hfcDataSource;



    @Override
    public void setEnvironment(Environment environment) {
        logger.info("MyBatisHfcConfiguration  setEnvironment has been called");
        this.propertyResolver = new RelaxedPropertyResolver(environment,"mybatis.");
    }

    @Bean(name="hfcSqlSessionFactory")
    public SqlSessionFactory hfcSqlSessionFactory() {
        try {
            //logger.info("userSqlSessionFactory: "+userDataSource.getConnection().getSchema());
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(hfcDataSource);
            factoryBean.setTypeAliasesPackage(propertyResolver
                    .getProperty("typeAliasesPackage"));
            factoryBean
                    .setMapperLocations(new PathMatchingResourcePatternResolver()
                            .getResources(propertyResolver
                                    .getProperty("mapperLocations")));
            factoryBean
                    .setConfigLocation(new DefaultResourceLoader()
                            .getResource(propertyResolver
                                    .getProperty("configLocation")));

            SqlSessionFactory sqlSessionFactory = null;
            try {
                sqlSessionFactory = factoryBean.getObject();
            }catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }

            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory
                    .getConfiguration();
            configuration.setMapUnderscoreToCamelCase(true);

            return sqlSessionFactory;

        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("Could not confiure mybatis session factory");
            return null;
        }
    }



    @Bean(name = "hfcSqlSessionTemplate")
    public SqlSessionTemplate getHfcSqlSessionTemplate(){
        logger.info("getHfcSqlSessionTemplate  : ");
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(hfcSqlSessionFactory());
        return sessionTemplate;
    }




}
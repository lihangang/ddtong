//package com.ddtong.core.dao.datasource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//@MapperScan(basePackages = "com.ddtong.core.dao.mapper2", sqlSessionTemplateRef  = "threeSqlSessionTemplate")
//public class DataSource3Config {
//
//    @Bean(name = "threeDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.three")
//    public DataSource threeDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "threeSqlSessionFactory")
//    public SqlSessionFactory threeSqlSessionFactory(@Qualifier("threeDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper2/*.xml"));
//        
//        return bean.getObject();
//    }
//
//    @Bean(name = "threeTransactionManager")
//    public DataSourceTransactionManager threeTransactionManager(@Qualifier("threeDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean(name = "threeSqlSessionTemplate")
//    public SqlSessionTemplate threeSqlSessionTemplate(@Qualifier("threeSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//}

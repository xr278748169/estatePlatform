package com.kerry.wechat.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.kerry.core.SnowflakeIdWorker;
import com.kerry.wechat.interceptor.SqlInterceptor;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.IDAutoGen;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangshen on 2017/7/3.
 */
@Configuration
@EnableCaching
public class BeetlSqlConfig {

    private static int workerId = 2;//工作组ID

    private static int datacenterId = 2;//数据中心ID（这里按数据库顺序填写）


    @Bean(initMethod = "init", name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader();
        beetlGroupUtilConfiguration.setResourceLoader(classpathResourceLoader);
        try {
            return beetlGroupUtilConfiguration;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "beetlSqlScannerConfigurer")
    public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer() {
        BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
        conf.setBasePackage("com.kerry");//设置扫描的包
        conf.setDaoSuffix("Dao");
        conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean");
        return conf;
    }

    @Bean(name = "sqlManagerFactoryBean")
    @Primary
    public SqlManagerFactoryBean getSqlManagerFactoryBean(@Qualifier("datasource") DruidDataSource datasource) {
        SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
        BeetlSqlDataSource source = new BeetlSqlDataSource();
        source.setMasterSource(datasource);
        factory.setCs(source);
        factory.setDbStyle(new MySqlStyle());
        factory.setInterceptors(new Interceptor[]{new DebugInterceptor(),new SqlInterceptor()});
        factory.setNc(new UnderlinedNameConversion());
        factory.setSqlLoader(new ClasspathLoader("/sql"));
        //在这里注册一个新的主键生成器
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(workerId,datacenterId);
        Map<String,IDAutoGen> idAutoGenMap = new HashMap<>();
        idAutoGenMap.put("pkId", new IDAutoGen() {
            @Override
            public String nextID(String s) {
                return String.valueOf(snowflakeIdWorker.nextId());
            }
        });
        factory.setIdAutoGens(idAutoGenMap);
        return factory;
    }

    @Bean(name="txManager")
    @Primary
    public DataSourceTransactionManager getDataSourceTransactionManager(@Qualifier("datasource") DruidDataSource datasource) {
        DataSourceTransactionManager dsm = new DataSourceTransactionManager();
        dsm.setDataSource(datasource);
        return dsm;
    }

}

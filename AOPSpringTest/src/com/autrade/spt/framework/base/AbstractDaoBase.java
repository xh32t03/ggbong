package com.autrade.spt.framework.base;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.autrade.spt.framework.annotation.DataSourceType;
import com.autrade.spt.framework.annotation.Mysql;
import com.autrade.spt.framework.annotation.Oracle;
import com.autrade.spt.framework.qualifier.DataBase;

public abstract class AbstractDaoBase extends JdbcDaoSupport
{

    protected DataSource mysqlDataSource;
//    protected DataSource oracleDataSource;

    
//    @Autowired
//    public void initDataSource(@Mysql DataSource mysqlDataSource)
//    {
//        this.mysqlDataSource = mysqlDataSource;
//    }
    
    @Autowired  
    public void initDataSource(   
            @DataSourceType(ip="localhost", database=DataBase.MYSQL)   
            DataSource mysqlDataSource) {   
        this.mysqlDataSource = mysqlDataSource;
    }
    
//    @Autowired
//    public void initDataSource(@Mysql DataSource mysqlDataSource, @Oracle DataSource oracleDataSource)
//    {
//        this.mysqlDataSource = mysqlDataSource;
//        this.oracleDataSource = oracleDataSource;
//    }

//    @Autowired  
//    public void initDataSource(   
//            @DataSourceType(ip="localhost", database=DataBase.MYSQL)   
//            DataSource mysqlDataSource,   
//            @DataSourceType(ip="localhost", database=DataBase.ORACLE)   
//            DataSource oracleDataSource) {   
//        this.mysqlDataSource = mysqlDataSource;   
//        this.oracleDataSource = oracleDataSource;   
//    }
    
    public DataSource getMysqlDataSource()
    {
        return mysqlDataSource;
    }
    
}

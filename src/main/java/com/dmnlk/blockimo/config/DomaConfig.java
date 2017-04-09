package com.dmnlk.blockimo.config;

import com.dmnlk.blockimo.util.doma.MyJdbcLogger;
import org.seasar.doma.jdbc.*;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by m00217 on 2016/03/17.
 */
@Component
public class DomaConfig implements Config {
    private DataSource dataSource;
    private Dialect dialect;
    private SqlFileRepository sqlFileRepository;
    private static MyJdbcLogger logger = new MyJdbcLogger();

    public DomaConfig() {
    }

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = new TransactionAwareDataSourceProxy(dataSource);
    }

    @Autowired
    public void setDialect(@Value("${doma.dialect}") String domaDialect) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        this.dialect = (Dialect) Class.forName(domaDialect).newInstance();
    }

    @Autowired
    public void setSqlFileRepository(@Value("${spring.profiles.active}") String springProfilesActive) {
        // develop モードの時は SQL ファイルがキャッシュされないようにする
        if (springProfilesActive.equals("develop")) {
            this.sqlFileRepository = new NoCacheSqlFileRepository();
        } else {
            this.sqlFileRepository = new GreedyCacheSqlFileRepository();
        }
    }

    @Override
    public DataSource getDataSource() {
        return this.dataSource;
    }

    @Override
    public Dialect getDialect() {
        return this.dialect;
    }

    @Override
    public SqlFileRepository getSqlFileRepository() {
        return this.sqlFileRepository;
    }

    @Override
    public JdbcLogger getJdbcLogger() {
        return logger;
    }
}

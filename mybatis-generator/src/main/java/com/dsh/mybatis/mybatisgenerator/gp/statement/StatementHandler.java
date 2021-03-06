package com.dsh.mybatis.mybatisgenerator.gp.statement;

import com.dsh.mybatis.mybatisgenerator.gp.config.GpConfiguration;
import com.dsh.mybatis.mybatisgenerator.gp.config.MapperRegistry;
import com.dsh.mybatis.mybatisgenerator.gp.result.ResultSetHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2019-12-22_15:54
 */
public class StatementHandler {
    private final GpConfiguration configuration;

    private final ResultSetHandler resultSetHandler;

    public StatementHandler(GpConfiguration configuration) {
        this.configuration = configuration;
        resultSetHandler = new ResultSetHandler(configuration);
    }

    public <E> E query(MapperRegistry.MapperData mapperData, Object parameter) throws Exception {
        try {
            //JDBC
            Connection conn = getConnection();
            //TODO ParameterHandler
            PreparedStatement pstmt = conn.prepareStatement(String.format(mapperData.getSql(),
                    Integer.parseInt(String.valueOf(parameter))));
            pstmt.execute();
            //ResultSetHandler
            return (E)resultSetHandler.handle(pstmt.getResultSet(),mapperData.getType());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public Connection getConnection() throws SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "123";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}

package voc.service;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import voc.dao.Dao;
import voc.dao.DataAbstractFactory;
import voc.model.Word;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLFactory extends DataAbstractFactory{

    private Connection connection;
    public MySQLFactory(DriverManagerDataSource dataSource){
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Dao<Word> getDao() {
        return new WordDao(connection);
    }
}

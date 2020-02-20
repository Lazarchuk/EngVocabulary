package voc.dao;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import voc.model.Word;
import voc.service.MySQLFactory;

public abstract class DataAbstractFactory {
    public abstract Dao<Word> getDao();

    public static DataAbstractFactory getFactory(DriverManagerDataSource dataSource){
        return new MySQLFactory(dataSource);
    }
}

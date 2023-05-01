package kr.mjc.kys.basics.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.mariadb.jdbc.MariaDbDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
@Slf4j
public class DataSourceFactory {
    public static DataSource getDataSource() {
        Properties props = new Properties();
        try {
            props.load(DataSourceFactory.class.getResourceAsStream("/db.properties"));
            return new MariaDbDataSource(props.getProperty("url"));
        } catch (IOException | SQLException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
    }
}

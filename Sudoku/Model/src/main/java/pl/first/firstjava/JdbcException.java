package pl.first.firstjava;

import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class JdbcException extends SQLException {
    private static final Logger logger = LoggerFactory.getLogger(JdbcException.class);

    public JdbcException(String msg) {
        super(msg);
        try {
            logger.error(msg);
        } catch (Exception e) {
            throw new RuntimeWithLogsException(e);
        }
    }
}

package pl.first.firstjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloneException extends CloneNotSupportedException {
    private static final Logger logger = LoggerFactory.getLogger(CloneException.class);

    public CloneException(String msg) {
        super(msg);
        try {
            logger.error("Clone not supported " + msg);
        } catch (Exception e) {
            throw new RuntimeWithLogsException(e);
        }
    }
}

package pl.first.firstjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuntimeWithLogsException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(RuntimeWithLogsException.class);

    public RuntimeWithLogsException(Throwable msg) {
        super(msg);
        try {
            logger.error("There was Runtime Problem " + msg);
        } catch (Exception e) {
            throw new RuntimeWithLogsException(e);
        }
    }
}

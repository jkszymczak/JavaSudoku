package pl.first.firstjava;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileException extends IOException {

    private static final Logger logger = LoggerFactory.getLogger(FileException.class);

    public FileException(Throwable cause,String msg) {

        super(cause);
        try {
            logger.error("IO problem here " + msg);
        } catch (Exception e) {
            throw new RuntimeWithLogsException(e);
        }


    }
}

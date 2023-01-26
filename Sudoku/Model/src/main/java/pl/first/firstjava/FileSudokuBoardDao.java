package pl.first.firstjava;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private String filename;
    //private static final Logger logger = LoggerFactory.getLogger(FileSudokuBoardDao.class);

    public FileSudokuBoardDao(String filename) {
        this.filename = filename + ".txt";
    }

    @Override
    public SudokuBoard read() throws DaoFileOperationException {
        SudokuBoard obj = null;
        try (FileInputStream fileInputStream = new FileInputStream(filename);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            obj = (SudokuBoard) objectInputStream.readObject();
        } catch (ClassNotFoundException exception) {
            String msg = FileSudokuBoardDao.class.getSimpleName();
            throw new DaoFileOperationException(exception,msg);
        } catch (IOException exception) {
            String msg = FileSudokuBoardDao.class.getSimpleName();
            throw new DaoFileOperationException(exception,msg);
        }
        return obj;
    }

    @Override
    public void write(SudokuBoard obj) throws DaoFileOperationException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(obj);
        } catch (IOException exception) {
            String msg = FileSudokuBoardDao.class.getSimpleName();
            throw new DaoFileOperationException(exception,msg);
        }
    }
}

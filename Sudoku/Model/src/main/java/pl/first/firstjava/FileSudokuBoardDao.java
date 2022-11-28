package pl.first.firstjava;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    
    private String filename;

    public FileSudokuBoardDao(String filename) {
        this.filename = filename + ".txt";
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard obj = null;
        try (FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                obj = (SudokuBoard) objectInputStream.readObject();
            } catch (ClassNotFoundException exception) {
                System.out.println("Class Not Found");
                throw new RuntimeException(exception);
            } catch (IOException exception) {
            System.out.println("IO problem here");
                throw new RuntimeException(exception);
            }
            return obj;
    }

    @Override
    public void write(SudokuBoard obj) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(obj);
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
    }

 
}

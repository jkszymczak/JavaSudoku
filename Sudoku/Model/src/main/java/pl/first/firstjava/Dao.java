package pl.first.firstjava;

public interface Dao<T> {
    T read() throws FileException;

    void write(T obj) throws FileException;
}

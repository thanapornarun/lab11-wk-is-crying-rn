package ku.cs.lab11_dictionary.services;

public interface DataSource<T> {
    T readData();

    void writeData(T t);
}

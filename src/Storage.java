public class Storage<T> {
    private final T value;

    public Storage(T value) {
        this.value = value;
    }

    // Метод для получения значения с альтернативой
    public T getOrElse(T alternative) {
        return value != null ? value : alternative;
    }

    // Метод для извлечения значения из хранилища и вывода на экран
    public static <T> void printStorage(Storage<T> storage, T alternative) {
        T value = storage.getOrElse(alternative);
        System.out.println("Значение в хранилище: " + value);
    }
}
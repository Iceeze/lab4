public class Box<T> {
    private T item;

    public Box(T item) {
        this.item = item;
    }

    public Box() {
        this(null);
    }

    // Метод для помещения объекта в коробку
    public void put(T item) throws IllegalStateException {
        if (!this.isEmpty()) {
            throw new IllegalStateException("Коробка уже заполнена.");
        }
        this.item = item;
    }

    // Метод для извлечения объекта из коробки
    public T take() {
        T temp = this.item;
        this.item = null;
        return temp;
    }

    // Метод для проверки заполненности
    public boolean isEmpty() {
        return item == null;
    }

    // Метод для получения текущего объекта (если нужно просто посмотреть)
    public T get() {
        return item;
    }

    // Метод для извлечения значения из коробки и вывода его на экран
    public static void printBoxContent(Box<Integer> box) {
        if (!box.isEmpty()) {
            Integer value = box.take();
            System.out.println("Значение в коробке: " + value);
        } else {
            System.out.println("Коробка пуста");
        }
    }
}
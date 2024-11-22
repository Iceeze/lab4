import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BiFunction;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Вариант 10\n");
        System.out.println("Список заданий:");
        System.out.println("1. Обобщенная коробка (1.1)");
        System.out.println("2. Хранилище без null (1.2)");
        System.out.println("3. Поиск максимума (2.2)");
        System.out.println("4. Функция (3.1)");
        System.out.println("5. Фильтр (3.2)");
        System.out.println("6. Сокращение (3.3)");
        System.out.print("\nВыберите номер задания: ");
        String choice = scanner.next();

        switch (choice) {
            // Задание 1.1
            case "1": {
                Box<Integer> integerBox = new Box<>();
                try {
                    integerBox.put(3);
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
                Box.printBoxContent(integerBox);
            } break;

            // Задание 1.2
            case "2": {
                // Хранилище чисел со значением null, альтернатива 0
                Storage<Integer> nullNumberStorage = new Storage<>(null);
                Storage.printStorage(nullNumberStorage, 0);

                // Хранилище чисел со значением 99, альтернатива -1
                Storage<Integer> numberStorage = new Storage<>(99);
                Storage.printStorage(numberStorage, -1);

                // Хранилище строк со значением null, альтернатива "default"
                Storage<String> nullStringStorage = new Storage<>(null);
                Storage.printStorage(nullStringStorage, "default");

                // Хранилище строк со значением "hello", альтернатива "hello world"
                Storage<String> stringStorage = new Storage<>("hello");
                Storage.printStorage(stringStorage, "hello world");
            } break;

            // Задание 2.2
            case "3": {
                int numBoxes = getValidInt("Введите количество коробок: ", scanner);
                while (numBoxes <= 0) {
                    System.out.println("Количество коробок должно быть > 0");
                    numBoxes = getValidInt("Введите количество коробок: ", scanner);
                }

                List<Box<? extends Number>> boxes = new ArrayList<>();
                for (int i = 0; i < numBoxes; i++) {
                    System.out.println("Коробка " + (i + 1));
                    int typeChoice = getValidInt("Выберите тип данных для коробки: 1 - Integer, 2 - Double, 3 - Float: ", scanner);

                    // Обрабатываем ввод данных и создаем коробку с соответствующим типом
                    switch (typeChoice) {
                        case 1:  // Integer
                            Integer intValue = getValidInt("Введите значение для коробки: ", scanner);
                            boxes.add(new Box<>(intValue));
                            break;
                        case 2:  // Double
                            Double doubleValue = getValidDouble("Введите значение для коробки: ", scanner);
                            boxes.add(new Box<>(doubleValue));
                            break;
                        case 3:  // Float
                            Float floatValue = getValidFloat("Введите значение для коробки: ", scanner);
                            boxes.add(new Box<>(floatValue));
                            break;
                        default:
                            System.out.println("Неверный выбор типа данных.");
                            i--;  // Повторяем итерацию для текущей коробки
                    }
                }
                double max = findMax(boxes);
                System.out.println("Максимальное значение: " + max);
            } break;

            // Задание 3.1
            case "4": {
                // Длина строк
                List<String> stringList = List.of("qwerty", "asdfg", "zx");
                List<Integer> lengthList = applyFunctionToList(stringList, String::length);
                System.out.println("Длины строк: " + lengthList);

                // Преобразование отрицательных чисел в положительные
                List<Integer> numberList = List.of(1, -3, 7);
                List<Integer> absList = applyFunctionToList(numberList, Math::abs);
                System.out.println("Положительные значения: " + absList);

                // Получение максимального значения из массивов целых чисел
                List<int[]> arrayOfInts = createIntArrays(scanner);
                List<Integer> maxList = applyFunctionToList(arrayOfInts, Main::findMaxElement);
                System.out.println("Максимальные значения массивов: " + maxList);
            } break;

            // Задание 3.2
            case "5": {
                // Фильтрация строк длиной менее 3 символов
                List<String> stringList = List.of("qwerty", "asdfg", "zx");
                List<String> filteredStrings = filterList(stringList, str -> str.length() >= 3);
                System.out.println("Строки длиной 3 и более символов: " + filteredStrings);

                // Фильтрация положительных чисел
                List<Integer> numberList = List.of(1, -3, 7);
                List<Integer> filteredNumbers = filterList(numberList, num -> num < 0);
                System.out.println("Отрицательные числа: " + filteredNumbers);

                // Фильтрация массивов, где нет положительных чисел
                List<int[]> arrayOfInts = createIntArrays(scanner);
                List<int[]> filteredArrays = filterList(arrayOfInts, Main::positiveArrCheck);
                System.out.println("Массивы без положительных чисел: ");
                for (int[] array : filteredArrays) {
                    for (int num : array) {
                        System.out.print(num + " ");
                    }
                    System.out.println();
                }
            } break;

            // Задание 3.3
            case "6": {
                // Формирование одной большой строки
                List<String> stringList = List.of("qwerty", "asdfg", "zx");
                String concatenatedString = reduceList(stringList, (a, b) -> a + b, "");
                System.out.println("Сформированная строка: " + concatenatedString);

                // Сумма всех значений
                List<Integer> numberList = List.of(1, -3, 7);
                int sum = reduceList(numberList, Integer::sum, 0);
                System.out.println("Сумма всех значений: " + sum);

                // Ввод данных вручную — список списков целых чисел
                List<List<Integer>> listOfLists = createIntList(scanner);
                int totalElements = reduceListOfLists(listOfLists);
                System.out.println("Общее количество элементов во всех списках: " + totalElements);
            } break;

            default: System.out.println("Данного задания нет в списке.");
        }
    }

    // Метод для проверки корректного ввода целого числа
    public static int getValidInt(String message, Scanner scanner) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) return scanner.nextInt();
            else {
                System.out.println("Ошибка: это не целое число. Попробуйте снова.");
                scanner.next();
            }
        }
    }

    // Метод для проверки корректного ввода дробного числа
    public static double getValidDouble(String message, Scanner scanner) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextDouble()) return scanner.nextDouble();
            else {
                System.out.println("Ошибка: это число не типа double. Попробуйте снова.");
                scanner.next();
            }
        }
    }

    // Метод для проверки корректного ввода целого числа
    public static float getValidFloat(String message, Scanner scanner) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextFloat()) return scanner.nextFloat();
            else {
                System.out.println("Ошибка: это число не типа Float. Попробуйте снова.");
                scanner.next();
            }
        }
    }

    // Метод для поиска максимального значения из набора коробок
    public static double findMax(List<Box<? extends Number>> boxes) {
        double max = -999999999;
        for (Box<? extends Number> box : boxes) {
            if (!box.isEmpty()) {  // Проверяем, что в коробке не null
                double value = box.get().doubleValue();  // Преобразуем значение в double
                if (value > max) max = value;
            }
        }
        return max;
    }

    // Обобщенный метод для применения функции ко всем элементам списка
    public static <T, P> List<P> applyFunctionToList(List<T> list, Function<T, P> function) {
        List<P> result = new ArrayList<>();
        for (T item : list) {
            result.add(function.apply(item));
        }
        return result;
    }

    // Метод поиска максимального элемента из массива
    public static int findMaxElement(int[] arrInt) {
        int max = -999999999;
        for (int num : arrInt) {
            if (num > max) max = num;
        }
        return max;
    }

    // Обобщенный метод для фильтрации списка по предикату
    public static <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
        List<T> filteredList = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) filteredList.add(item);
        }
        return filteredList;
    }

    // Метод для создания списка из массивов целых чисел
    public static List<int[]> createIntArrays(Scanner scanner) {
        int arrCount = getValidInt("Введите число массивов целых чисел: ", scanner);
        while (arrCount <= 0) {
            System.out.println("Число должно быть > 0.");
            arrCount = getValidInt("Введите число массивов целых чисел: ", scanner);
        }

        List<int[]> arrayOfInts = new ArrayList<>();
        for (int i = 0; i < arrCount; i++) {
            int arrLen = getValidInt("Введите количество чисел в массиве: ", scanner);
            while (arrLen <= 0) {
                System.out.println("Число должно быть > 0.");
                arrLen = getValidInt("Введите количество чисел в массиве: ", scanner);
            }
            int[] arrInt = new int[arrLen];

            for (int j = 0; j < arrLen; j++) {
                int num = getValidInt("Введите " + (j+1) + "-е число: ", scanner);
                arrInt[j] = num;
            }
            arrayOfInts.add(arrInt);
        }
        return arrayOfInts;
    }

    // Метод для создания списка из списков целых чисел
    public static List<List<Integer>> createIntList(Scanner scanner) {
        int listsCount = getValidInt("Введите число списков целых чисел: ", scanner);
        while (listsCount <= 0) {
            System.out.println("Число должно быть > 0.");
            listsCount = getValidInt("Введите число списков целых чисел: ", scanner);
        }

        List<List<Integer>> listOflists = new ArrayList<>();
        for (int i = 0; i < listsCount; i++) {
            int listLen = getValidInt("Введите количество чисел в списке: ", scanner);
            while (listLen <= 0) {
                System.out.println("Число должно быть > 0.");
                listLen = getValidInt("Введите количество чисел в списке: ", scanner);
            }
            List<Integer> listOfInt = new ArrayList<>();

            for (int j = 0; j < listLen; j++) {
                int num = getValidInt("Введите " + (j+1) + "-е число: ", scanner);
                listOfInt.add(num);
            }
            listOflists.add(listOfInt);
        }
        return listOflists;
    }

    // Метод для проверки массива на содержание в нём положительных чисел
    public static boolean positiveArrCheck(int[] array) {
        for (int num : array) {
            if (num > 0) return false;
        }
        return true;
    }

    // Метод для редуцирования списка в одно значение
    public static <T> T reduceList(List<T> list, BiFunction<T, T, T> accumulator, T nullReplace) {
        T result = nullReplace;  // Инициализация результата значением identity
        for (T item : list) {  // Проход по каждому элементу списка
            result = accumulator.apply(result, item);  // Применение операции сворачивания
        }
        return result;  // Возвращаем итоговое значение
    }

    // Метод для работы с списком списков и подсчёта их элементов
    public static int reduceListOfLists(List<List<Integer>> listOfLists) {
        int totalElements = 0;  // Инициализация счетчика элементов
        for (List<Integer> list : listOfLists) {  // Проход по спискам
            for (Integer item : list) {  // Проход по элементам каждого списка
                totalElements++;  // Увеличиваем счетчик для каждого элемента
            }
        }
        return totalElements;  // Возвращаем общее количество элементов
    }
}

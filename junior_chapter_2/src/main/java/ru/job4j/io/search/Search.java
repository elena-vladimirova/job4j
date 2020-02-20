package ru.job4j.io.search;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.function.Predicate;

/**
 * 1. Создать программу для поиска файла.
 * 2. Программа должна искать данные в заданном каталоге и подкаталогах.
 * 3. Имя файла может задаваться, целиком, по маске, по регулярному выражение(не обязательно).
 * 4. Программа должна собираться в jar и запускаться через java -jar find.jar -d c:/ -n *.txt -m -o log.txt
 * Ключи
 * -d - директория в которая начинать поиск.
 * -n - имя файл, маска, либо регулярное выражение.
 * -m - искать по макс, либо -f - полное совпадение имени. -r регулярное выражение.
 * -o - результат записать в файл.
 * 5. Программа должна записывать результат в файл.
 * 6. В программе должна быть валидация ключей и подсказка.
 */
public class Search {

    private Args args;

    public Search(Args args) {
        this.args = args;
    }

    public void search() throws IOException {
        Path path = Paths.get(args.getDirectory());
        Predicate<String> predicate;
        if (args.getMode() == Args.SearchMode.REGEXP) {
            predicate = n -> n.matches(args.getName());
        } else if (args.getMode() == Args.SearchMode.MASK) {
            predicate = n -> FileSystems.getDefault()
                                        .getPathMatcher("glob:" + args.getName())
                                        .matches(Paths.get(n));
        } else {
            predicate = n -> n.equals(args.getName());
        }


        try (PrintWriter out = new PrintWriter(args.getLog())) {
            Files.walk(path)
                 .filter(p -> Files.isRegularFile(p))
                 .filter(f -> predicate.test(f.getFileName().toString()))
                 .forEach(f -> out.println(f.toAbsolutePath().toString()));
        }

    }

    public static void main(String[] args) throws IOException {
        // -d C:\projects\job4j\junior_chapter_2 -n Search.java -o C:\projects\job4j\junior_chapter_2\src\main\java\ru\job4j\io\search\log.txt
        // -d C:\projects\job4j\junior_chapter_2 -n .*ip\.java -r -o C:\projects\job4j\junior_chapter_2\src\main\java\ru\job4j\io\search\log.txt
        // -d C:\projects\job4j\junior_chapter_2 -n *.java -m -o C:\projects\job4j\junior_chapter_2\src\main\java\ru\job4j\io\search\log.txt
        Args arguments = new Args(args);
        Search search = new Search(arguments);
        search.search();
    }
}
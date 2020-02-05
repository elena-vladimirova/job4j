package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SearchTest {
    @Test
    public void whenFiles() {
        List<File> expected = List.of(
                new File("C:\\Users\\6145~1\\AppData\\Local\\Temp\\Job4JTest\\File3.txt"),
                new File("C:\\Users\\6145~1\\AppData\\Local\\Temp\\Job4JTest\\Folder1\\Folder1File1.txt"),
                new File("C:\\Users\\6145~1\\AppData\\Local\\Temp\\Job4JTest\\Folder1\\Folder1File2.java"),
                new File("C:\\Users\\6145~1\\AppData\\Local\\Temp\\Job4JTest\\Folder2\\Folder2File1.java")
        );
        Predicate<File> predicate = new Predicate() {
            @Override
            public boolean test(Object o) {
                File file = (File)o;
                Set<String> ext = Set.of("txt", "java");
                return ext.contains(Search.getExtension(file.getName()));
            }
        };
        assertThat(expected,
                   is(Search.files(System.getProperty("java.io.tmpdir") + "Job4JTest", predicate))
        );
    }
}

package ru.job4j.io;

import java.io.File;
import java.util.*;

/**
 * В этом задании нужно написать метод, который возвращает список всех файлов с конкретным расширением.
 * Метод должен заходить во всех каталоги.
 * Для этого нужно использовать алгоритм обхода дерева в ширину.
 */
public class Search {

    private static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static List<File> files(String parent, Set<String> exts) {
        List<File> result = new LinkedList<>();
        Queue<File> filesQueue = new LinkedList<>();
        filesQueue.offer(new File(System.getProperty("java.io.tmpdir"), parent));
        while (!filesQueue.isEmpty()) {
            File file = filesQueue.poll();
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    if (f.isFile() && exts.contains(getExtension(f.getName()))) {
                        result.add(f);
                    } else {
                        filesQueue.offer(f);
                    }
                }
            } else if (exts.contains(getExtension(file.getName()))) {
                result.add(file);
            }
        }
        return result;
     }

    public static void main(String[] args) {
        //System.out.println(System.getProperty("java.io.tmpdir"));
        Search.files("Job4JTest", Set.of("txt", "java")).stream().forEach(f -> System.out.println(f));
    }
}

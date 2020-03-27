package ru.job4j.gc.size.ru.job4j.cache;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {

    Map<String, SoftReference<List<String>>> cache = new HashMap();

    public List<String> getFile(String name) throws IOException {
        List<String> result;
        if (cache.containsKey(name) && cache.get(name).get() != null) {
            result = cache.get(name).get();
        } else {
            result = Files.readAllLines(Paths.get(name));
            cache.put(name, new SoftReference(result));
        }
        return result;
    }

    public static void main(String[] args) {
        Cache c = new Cache();
        try {
            c.getFile("junior_chapter_4\\src\\main\\resources\\Names.txt").forEach(l -> System.out.println(l));
            c.getFile("junior_chapter_4\\src\\main\\resources\\Names.txt").forEach(l -> System.out.println(l));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

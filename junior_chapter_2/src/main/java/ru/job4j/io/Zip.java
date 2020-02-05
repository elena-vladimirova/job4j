package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static List<File> seekBy(String root, String ext) {
        Predicate<File> check = f -> !f.getName().matches(ext);
        return Search.files(root, check);
    }

    public static void pack(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //-d C:\projects\job4j\junior_chapter_2\ -e .*\.java -o project.zip
        Args packArgs = new Args(args);
        Zip.pack(Zip.seekBy(packArgs.getDirectory(), packArgs.getExclude()), packArgs.getOutput());
    }
}

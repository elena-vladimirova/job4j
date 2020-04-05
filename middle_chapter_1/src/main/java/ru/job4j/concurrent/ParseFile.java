package ru.job4j.concurrent;

import java.io.*;
import java.util.stream.Collectors;

public class ParseFile {

    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public String getContent() throws IOException {
        try (BufferedReader i = new BufferedReader(new FileReader(this.getFile()))) {
            return i.lines().collect(Collectors.joining());
        }
    }

    public String getContentWithoutUnicode() throws IOException {
        try (BufferedReader i = new BufferedReader(new FileReader(this.getFile()))) {
            StringBuffer output = new StringBuffer();
            int data;
            while ((data = i.read()) > 0) {
                if (data < 0x80) {
                    output.append((char) data);
                }
            }
            return output.toString();
        }
    }

    public void saveContent(String content) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(this.getFile()))) {
            out.print(content);
        }
    }

    public static void main(String[] args) throws Exception {
        ParseFile pf = new ParseFile();
        pf.setFile(new File("middle_chapter_1\\src\\main\\resources\\ParseFile.txt"));
        System.out.println(pf.getContent());
        System.out.println(pf.getContentWithoutUnicode());
        pf.saveContent("Hi!");
    }
}

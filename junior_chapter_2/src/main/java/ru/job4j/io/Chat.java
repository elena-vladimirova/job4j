package ru.job4j.io;

import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Chat {

    private static final String stopWord = "стоп";
    private static final String continueWord = "продолжить";
    private static final String finishWord = "закончить";
    private static final Path answers = Paths.get("C:\\projects\\job4j\\junior_chapter_2","src", "main", "resources", "odnostishiya.txt");
    private static final File log = new File("C:\\projects\\job4j\\junior_chapter_2\\src\\main\\resources\\chatLog.txt");

    private String getAnswer(List<String> answer) {
        Random random = new Random();
        return answer.get(random.nextInt(answer.size() - 1));
    }

    private void write(Console console, PrintWriter out, String text) {
        console.writer().println(text);
        out.println(text);
    }

    private String read(Console console, PrintWriter out) {
        console.flush();
        String line = console.readLine();
        out.println(line);
        return line;
    }

    public void start() {
        Console console = System.console();
        if(console == null) {
            throw new RuntimeException("Console not available");
        } else {
            try (PrintWriter out = new PrintWriter(new FileOutputStream(log))) {
                List<String> answer = Files.readAllLines(answers);
                write(console, out, "Bot: " + "Привет, давай поболтаем?");
                String input;
                boolean putAnswer = true;
                do {
                    input = read(console, out);
                    if (input.equals(stopWord)) {
                        putAnswer = false;
                    } else if (input.equals(continueWord)) {
                        putAnswer = true;
                    }
                    if (putAnswer == true) {
                        write(console, out, "Bot: " + getAnswer(answer));
                    }
                } while (!input.equals(finishWord));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Chat chat = new Chat();
        chat.start();
    }
}
package ru.job4j.io;

import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Chat {

    private static String stopWord = "стоп";
    private static String continueWord = "продолжить";
    private static String finishWord = "закончить";
    private static List<String> chatText = new LinkedList<>();
    private Path answers;
    private File log;

    public Chat(String answers, String log) {
        this.answers = Paths.get(answers);
        this.log = new File(log);
    }

    private String getAnswer(List<String> answer) {
        Random random = new Random();
        return answer.get(random.nextInt(answer.size() - 1));
    }

    private void write(Console console, String text) {
        console.writer().println(text);
        chatText.add(text);
    }

    private String read(Console console) {
        console.flush();
        String line = console.readLine();
        chatText.add(line);
        return line;
    }

    private void writeLog() {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(log))) {
            for (String line : this.chatText) {
                out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        Console console = System.console();
        if (console == null) {
            throw new RuntimeException("Console not available");
        } else {
            try {
                List<String> answer = Files.readAllLines(answers);
                write(console, "Bot: " + "Привет, давай поболтаем?");
                String input;
                boolean putAnswer = true;
                do {
                    input = read(console);
                    if (input.equals(stopWord)) {
                        putAnswer = false;
                    } else if (input.equals(continueWord)) {
                        putAnswer = true;
                    }
                    if (putAnswer) {
                        write(console, "Bot: " + getAnswer(answer));
                    }
                } while (!input.equals(finishWord));
            } catch (Exception e) {
                e.printStackTrace();
            }
            writeLog();
        }
    }

    public static void main(String[] args) {
        //Chat chat = new Chat("C:\\projects\\job4j\\junior_chapter_2\\src\\main\\resources\\odnostishiya.txt",
        //                     "C:\\projects\\job4j\\junior_chapter_2\\src\\main\\resources\\chatLog.txt");
        Chat chat = new Chat(args[0], args[1]);
        chat.start();
    }
}
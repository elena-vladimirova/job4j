package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Set;

/**
 * Представим, что у нас есть файл регистрации событий сервера.
 * Он имеет следующий формат
 * TYPE date
 * Type - может иметь 4 значения 200, 300, 400, 500
 * Date - это время проверки 10:56:01 (формат часы:минуты:секунды)
 *
 * Например server.log
 *
 * 200 10:56:01
 * 200 10:57:01
 * 400 10:58:01
 * 200 10:59:01
 * 500 11:01:02
 * 200 11:02:02
 *
 * Метод unavailable должен находить диапазоны, когда сервер не работал.
 * Сервер не работал. если status = 400 или 500
 */
public class Analizy {
    public static void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter write = new PrintWriter(new FileOutputStream(target))) {

            String line;
            Set<String> unavailableState = Set.of("400", "500");
            Set<String> availableState = Set.of("200", "300");
            Set<String> currentState = unavailableState;

            while ((line = read.readLine()) != null) {
                if (currentState == unavailableState && unavailableState.contains(line.substring(0, 3))) {
                    write.print(line.substring(4) + ";");
                    currentState = availableState;
                } else if (currentState == availableState && availableState.contains(line.substring(0, 3))) {
                    write.println(line.substring(4));
                    currentState = unavailableState;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

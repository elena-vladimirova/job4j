package ru.job4j.io.oracle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        String userInput;
        while (!(userInput = console.nextLine()).equals("exit")) {
            out.println(userInput);
            String str;
            while (!(str = in.readLine()).isEmpty()) {
                System.out.println(str);
            }
        }
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 5000)) {
            new Client(socket).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
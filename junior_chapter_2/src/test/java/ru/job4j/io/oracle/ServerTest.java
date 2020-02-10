package ru.job4j.io.oracle;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {

    private static final String LN = System.getProperty("line.separator");

    private void testServer(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.start();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenExit() throws IOException {
        this.testServer("exit", "");
    }

    @Test
    public void whenHello() throws IOException {
        this.testServer(String.format("Hello oracle%sexit", LN),
                        String.format("Hello, dear friend, I'm a oracle.%s%s", LN, LN));
    }

    @Test
    public void whenQuestion() throws IOException {
        this.testServer(String.format("What time is it?%sexit", LN),
                        String.format("I dont know.%s%s", LN, LN));
    }

}

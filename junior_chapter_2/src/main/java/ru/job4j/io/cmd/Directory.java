package ru.job4j.io.cmd;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Please solve the followig puzzle which simulates generic directory structures.
 * The solution should be directory agnostic.
 * Be succinct yet readable. You should not exceed more than 200 lines.
 * Consider adding comments and asserts to help the understading.
 * Code can be compiled with javac Directory.java
 * Code should be executed as: java -ea Directory (-ea option it's to enabled the assert)
 */

public class Directory {

    public static void main(String[] args) {

        final Shell shell = new Shell();
        assert shell.path().equals("\\");

        shell.cd("/");
        assert shell.path().equals("\\");

        shell.cd("usr/..");
        assert shell.path().equals("\\");

       shell.cd("usr").cd("local");
       assert shell.path().equals("\\usr\\local");

        shell.cd("../local").cd("./");
        assert shell.path().equals("\\usr\\local");

        shell.cd("..");
        assert shell.path().equals("\\usr");

        //Exception in thread "main" java.nio.file.InvalidPathException: UNC path is missing sharename: //lib///
        shell.cd("//lib///");
        assert shell.path().equals("\\lib");
    }
}
/**
 * change the code here.
 */
class Shell {

    Path path = Paths.get("/");

    Shell cd(final String path) {
        if (path.startsWith("/")) {
            this.path = Paths.get(path);
        } else {
            this.path = this.path.resolve(path);
        }
        this.path = this.path.normalize();
        return this;
    }

    public String path() {
        return path.toString();
    }
}
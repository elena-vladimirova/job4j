package ru.job4j.io;

import java.io.File;

public class Args {

    private String directory;
    private String exclude;
    private String output;

    public String getDirectory() {
        return directory;
    }

    public String getExclude() {
        return exclude;
    }

    public File getOutput() {
        return new File(output);
    }

    public Args(String[] args) {
        int i = 0;
        while (i < args.length) {
            switch (args[i]) {
                case ("-d"):
                    directory = args[++i];
                    break;
                case ("-e"):
                    exclude = args[++i];
                    break;
                case ("-o"):
                    output = args[++i];
                    break;
                default:
            }
            i++;
        }
    }
}

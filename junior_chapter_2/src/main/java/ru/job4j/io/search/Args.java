package ru.job4j.io.search;

public class Args {

    enum SearchMode {REGEXP, FULLNAME, MASK};

    private String directory;
    private String name;
    private SearchMode mode = SearchMode.FULLNAME;
    private String log;

    public String getDirectory() {
        return directory;
    }

    public String getName() {
        return name;
    }

    public SearchMode getMode() {
        return mode;
    }

    public String getLog() {
        return log;
    }

    public Args(String[] args) {
        String msg = "Correct arguments:\n" +
                "-d search directory\n" +
                "-n file name or regular expression\n" +
                "-f search mode - complete match (default)\n" +
                "-r search mode - regular expression\n" +
                "-o result file";
        int i = 0;
        while (i < args.length) {
            switch (args[i]) {
                case ("-d"):
                    directory = args[++i];
                    break;
                case ("-n"):
                    name = args[++i];
                    break;
                case ("-f"):
                    mode = SearchMode.FULLNAME;
                    break;
                case ("-r"):
                    mode = SearchMode.REGEXP;
                    break;
                case ("-m"):
                    mode = SearchMode.MASK;
                    break;
                case ("-o"):
                    log = args[++i];
                    break;
                default:
                    throw new IllegalArgumentException(msg);
            }
            i++;
        }
        if (directory == null || name == null || log == null) {
            throw new IllegalArgumentException(msg);
        }
    }
}

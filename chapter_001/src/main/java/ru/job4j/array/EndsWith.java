package ru.job4j.array;

public class EndsWith {
    boolean result = true;
    public static boolean endsWith(char[] word, char[] post) {
        boolean result = true;
        int startPosition = word.length - post.length;
        for (int i = startPosition; i < word.length; i++) {
            if (word[i] != post[i-startPosition]){
                result = false;
                break;
            }
        }
        return result;
    }

}

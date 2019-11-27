package ru.job4j.pojo;

public class Library {

    public static Book[] delete(Book[] books, int index){

        for (int i = index; i < books.length-1; i++){
            books[i] = books[i+1];
        }
        books[books.length-1] = null;
        return books;

    }

    public static void main(String[] args) {

        Book[] books = {new Book ("Clean Code", 10),
                        new Book ("The Catcher In Yhe Ray", 100),
                        new Book ("The Lord Of The Rings", 1000),
                        new Book ("Hobbit", 200)
        };

        for (Book b : books) {
            System.out.println(b.getName() + " " + b.getPageCount());
        }

        Book temp;
        temp = books[0];
        books[0] = books[3];
        books[3] = temp;

        for (Book b : books) {
            System.out.println(b.getName() + " " + b.getPageCount());
        }

        for (Book b : books) {
            if (b.getName().equals("Clean Code")) {
                System.out.println(b.getName() + " " + b.getPageCount());
            }
        }


        System.out.println("--------------");
        books = delete(books, 0);
        for (Book b : books) {
            if (b != null) {
                System.out.println(b.getName() + " " + b.getPageCount());
            } else {
                System.out.println("null");
            }
        }

    }
}

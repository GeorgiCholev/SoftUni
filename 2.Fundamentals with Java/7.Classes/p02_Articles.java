package Classes_7.Exercises;

import java.util.Scanner;

public class p02_Articles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        String line = scanner.nextLine();
        String[] components = line.split(", ");
        Article article = new Article(components[0], components[1], components[2]);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            line = scanner.nextLine();
            String[] edit = line.split(": ");
            switch (edit[0]) {
                case "Edit":
                    article.Edit(edit[1]);
                    break;
                case "ChangeAuthor":
                    article.ChangeAuthor(edit[1]);
                    break;
                case "Rename":
                    article.Rename(edit[1]);
                    break;
            }
        }
        System.out.println(article);
    }
    static class Article {
        String title;
        String content;
        String author;

        public Article(String title, String content, String author) {
            this.title = title;
            this.content = content;
            this.author = author;
        }
        void Edit(String content) {
            this.content = content;
        }
        void ChangeAuthor(String author) {
            this.author = author;
        }
        void Rename(String title) {
            this.title = title;
        }
        @Override
        public String toString() {
            return String.format("%s - %s: %s", this.title, this.content, this.author);
        }
    }
}

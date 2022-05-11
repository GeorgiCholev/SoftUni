package Classes_7.Exercises;

import java.util.*;

public class p04_Article2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Article> articles = new ArrayList<>();
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] components = line.split(", ");
            String title = components[0];
            String content = components[1];
            String author = components[2];
            articles.add(new Article(title, content, author));
        }
        String type = scanner.nextLine();
        switch (type) {
            case "title":
                articles.stream()
                        .sorted(Comparator.comparing(Article::getTitle))
                        .forEach(System.out::println);
                break;
            case "content":
                articles.stream()
                        .sorted(Comparator.comparing(Article::getContent))
                        .forEach(System.out::println);
                break;
            case "author":
                articles.stream()
                        .sorted(Comparator.comparing(Article::getAuthor))
                        .forEach(System.out::println);
                break;
        }
    }

    static class Article {
        String title;
        String content;
        String author;

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }


        public String getAuthor() {
            return author;
        }

        public Article(String title, String content, String author) {
            this.title = title;
            this.content = content;
            this.author = author;
        }

        @Override
        public String toString() {
            return String.format("%s - %s: %s", this.title, this.content, this.author);

        }
    }
}

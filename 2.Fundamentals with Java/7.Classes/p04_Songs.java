package Classes_7.Lab;

import java.util.Scanner;

public class p04_Songs {
    static class Song {
        String typeList;
        String name;
        String time;

        Song(String typeList, String name, String time) {
            this.typeList = typeList;
            this.name = name;
            this.time = time;
        }

        String getTypeList() {
            return this.typeList;
        }

        String getName() {
            return this.name;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Song[] songs = new Song[n];
        String line;
        for (int i = 0; i < n; i++) {
            line = scanner.nextLine();
            String[] components = line.split("_");
            String typeList = components[0];
            String name = components[1];
            String time = components[2];
            songs[i] = new Song(typeList, name, time);
        }

        line = scanner.nextLine();

        printClassArray(songs, line);
    }

    private static void printClassArray(Song[] songs, String line) {
        if (line.equals("all")) {
            for (Song song : songs) {
                System.out.printf("%s%n", song.getName());
            }
        } else {
            for (Song song : songs) {
                if (line.equals(song.getTypeList())) {
                    System.out.printf("%s%n", song.getName());
                }
            }
        }
    }
}

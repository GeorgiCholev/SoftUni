package DefiningClasses_06.Exercise.p09_CatLady;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CatLady sweetCatLady = new CatLady();

        Scanner scanner = new Scanner(System.in);
        String input;
        while (!"End".equals(input = scanner.nextLine())) {
            String[] catComponents = input.split("\\s");
            String breed = catComponents[0];
            int componentTwo = Integer.parseInt(catComponents[2]);
            String componentOne = catComponents[1];
            Cat cat = null;
            switch (breed) {
                case "Siamese":
                    cat = new Siamese(componentOne, componentTwo);
                    break;
                case "Cymric":
                    cat = new Cymric(componentOne, componentTwo);
                    break;
                case "StreetExtraordinaire":
                    cat = new StreetExtraordinaire(componentOne, componentTwo);
                    break;
            }
            sweetCatLady.attainCat(cat);
        }

        String searchedCat = scanner.nextLine();
        String searchedCatInfo = sweetCatLady.getCat(searchedCat).getInfo();
        System.out.println(searchedCatInfo);
    }

}

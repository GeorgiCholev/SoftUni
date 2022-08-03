package DefiningClasses_06.Exercise.p09_CatLady;

import java.util.ArrayList;
import java.util.List;

public class CatLady {

    private List<Cat> catsOwned;

    public CatLady() {
        this.catsOwned = new ArrayList<>();
    }

    public Cat getCat(String catName) {
        for (Cat cat : catsOwned) {
            if (cat.getName().equals(catName)) {
                return cat;
            }
        }
        throw new IllegalArgumentException("Cat not owned");
    }

    public void attainCat(Cat cat) {
        catsOwned.add(cat);
    }
}

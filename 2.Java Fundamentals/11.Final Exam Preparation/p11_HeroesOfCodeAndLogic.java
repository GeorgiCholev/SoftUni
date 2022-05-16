package Fundamentals_ExamPrep_11.April4th2020;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class p03_HeroesOfCodeAndLogic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Hero> heroes = fillHeroes(scanner);
        String line = scanner.nextLine();
        while (!line.equals("End")) {
            String[] components = line.split("\\s-\\s");
            String action = components[0];
            switch (action) {
                case "CastSpell":
                    handleCastSpell(components, heroes);
                    break;
                case "TakeDamage":
                    handleTakeDamage(components, heroes);
                    break;
                case "Recharge":
                    handleRecharge(components, heroes);
                    break;
                case "Heal":
                    handleHeal(components, heroes);
                    break;
            }
            line = scanner.nextLine();
        }
        for (Hero value : heroes.values()) {
            System.out.println(value.getInfo());
        }
    }

    private static void handleCastSpell(String[] components, Map<String, Hero> heroes) {
        String heroName = components[1];
        int mpNeeded = Integer.parseInt(components[2]);
        String spellName = components[3];
        Hero hero = heroes.get(heroName);
        boolean success = hero.castSpell(mpNeeded);
        if (success) {
            System.out.println(heroName + " has successfully cast " + spellName + " and now has "
                    + heroes.get(heroName).getManaPoints() + " MP!");
        } else {
            System.out.println(heroName + " does not have enough MP to cast " + spellName + "!");
        }
    }

    private static void handleTakeDamage(String[] components, Map<String, Hero> heroes) {
        String heroName = components[1];
        int damage = Integer.parseInt(components[2]);
        String attacker = components[3];
        Hero hero = heroes.get(heroName);
        boolean alive = hero.takeDamage(damage);
        if (alive) {
            System.out.println(heroName + " was hit for " + damage + " HP by " + attacker + " and now has "
                    + hero.getHitPoints() + " HP left!");
        } else {
            heroes.remove(heroName);
            System.out.println(heroName + " has been killed by " + attacker + "!");
        }
    }

    private static void handleRecharge(String[] components, Map<String, Hero> heroes) {
        String heroName = components[1];
        int amount = Integer.parseInt(components[2]);
        Hero hero = heroes.get(heroName);
        int rechargedAmount = hero.recharge(amount);
        System.out.println(heroName + " recharged for " + rechargedAmount + " MP!");
    }

    private static void handleHeal(String[] components, Map<String, Hero> heroes) {
        String heroName = components[1];
        int amount = Integer.parseInt(components[2]);
        Hero hero = heroes.get(heroName);
        int rechargedAmount = hero.heal(amount);
        System.out.println(heroName + " healed for " + rechargedAmount + " HP!");
    }

    private static Map<String, Hero> fillHeroes(Scanner scanner) {
        Map<String, Hero> heroes = new LinkedHashMap<>();
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            String[] profile = input.split("\\s");
            heroes.put(profile[0], new Hero(profile[0], Integer.parseInt(profile[1]), Integer.parseInt(profile[2])));
        }
        return heroes;
    }


    static class Hero {
        String name;
        int hitPoints;
        int manaPoints;

        public int getManaPoints() {
            return manaPoints;
        }

        public int getHitPoints() {
            return hitPoints;
        }

        public Hero(String name, int hitPoints, int manaPoints) {
            this.name = name;
            this.hitPoints = hitPoints;
            this.manaPoints = manaPoints;
        }

        String getInfo() {
            return this.name + System.lineSeparator()
                    + "  HP: " + this.hitPoints + System.lineSeparator()
                    + "  MP: " + this.manaPoints;
        }

        boolean castSpell(int manaNeeded) {
            if (this.manaPoints >= manaNeeded) {
                this.manaPoints -= manaNeeded;
                return true;
            } else {
                return false;
            }
        }

        boolean takeDamage(int damage) {
            if (this.hitPoints > damage) {
                this.hitPoints -= damage;
                return true;
            } else {
                return false;
            }
        }


        int recharge(int amount) {
            int notRecharged = this.manaPoints;
            this.manaPoints = Math.min(200, this.manaPoints + amount);
            return this.manaPoints - notRecharged;
        }

        int heal(int amount) {
            int unhealed = this.hitPoints;
            this.hitPoints = Math.min(100, this.hitPoints + amount);
            return this.hitPoints - unhealed;
        }
    }
}


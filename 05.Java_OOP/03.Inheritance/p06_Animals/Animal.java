package _3_Inheritance.p06_Animals;

public class Animal {

    private String name;
    private int age;
    private String gender;

    public Animal(String name, int age, String gender) {
       setName(name);
       setAge(age);
       setGender(gender);
    }

    public String produceSound() {
        return "";
    }

    @Override
    public String toString() {
        return  this.getClass().getSimpleName() + System.lineSeparator() +
                this.name + " " + this.age + " " + this.gender + System.lineSeparator() +
                this.produceSound();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    private void setName(String name) {
        validation(name);
        this.name = name;
    }

    private void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.age = age;
    }

    private void setGender(String gender) {
        validation(gender);
        this.gender = gender;
    }
    private void validation(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid input!");
        }
    }

}

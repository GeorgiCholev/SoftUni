package _2_Encapsulation.Lab;

public class Person {

    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Person(String firstName, String lastName, int age, double salary) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.setSalary(salary);
    }

    public void setFirstName(String firstName) {

        validate(firstName.length(), 3, "First Name must be at least 3 characters long.");

        this.firstName = firstName;
    }


    public void setLastName(String lastName) {

        validate(lastName.length(), 3, "Last Name must be at least 3 characters long.");

        this.lastName = lastName;
    }

    public void setAge(int age) {

        validate(age, 1, "Age cannot be zero or negative " + age);

        this.age = age;
    }

    public void setSalary(double salary) {
        validate(salary, 460, "Salary cannot be under 460 leva.");
        this.salary = salary;
    }

    private void validate(double valueToValidate, int smallestAcceptableValue, String exceptionMessage) {
        if (valueToValidate < smallestAcceptableValue) {
            throw new IllegalArgumentException(exceptionMessage);

        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public void increaseSalary(double bonus) {
        if (this.age < 30) {
            this.salary += this.salary * (bonus / 200);
        } else {
            this.salary += this.salary * (bonus / 100);
        }
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + " gets " + String.format("%.1f", this.salary) + " leva.";
    }

}

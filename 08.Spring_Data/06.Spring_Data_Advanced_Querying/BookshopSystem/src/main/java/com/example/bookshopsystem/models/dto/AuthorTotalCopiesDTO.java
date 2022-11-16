package com.example.bookshopsystem.models.dto;

public class AuthorTotalCopiesDTO {

    private String firstName;

    private String lastName;

    private long totalSum;

    public AuthorTotalCopiesDTO(String firstName, String lastName, long totalSum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalSum = totalSum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(long totalSum) {
        this.totalSum = totalSum;
    }

    public String getInfo() {
        return getFirstName() + " " + getLastName() + " - " + getTotalSum();
    }
}

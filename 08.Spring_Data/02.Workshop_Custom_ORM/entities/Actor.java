package entities;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.time.LocalDate;

@Entity(name = "actors")
public class Actor {

    @Id
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "number_of_movies")
    private int numberOfMovies;

    @Column(name = "first_movie_date")
    private LocalDate firstMovieDate;

    @Column(name = "best_movie_name")
    private String bestMovieName;

    @Column(name = "age")
    private int age;

    public Actor() {
    }

    public Actor(String fullName, int numberOfMovies, LocalDate firstMovieDate) {
        this.fullName = fullName;
        this.numberOfMovies = numberOfMovies;
        this.firstMovieDate = firstMovieDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getNumberOfMovies() {
        return numberOfMovies;
    }

    public void setNumberOfMovies(int numberOfMovies) {
        this.numberOfMovies = numberOfMovies;
    }

    public LocalDate getFirstMovieDate() {
        return firstMovieDate;
    }

    public void setFirstMovieDate(LocalDate firstMovieDate) {
        this.firstMovieDate = firstMovieDate;
    }

    public String getBestMovieName() {
        return bestMovieName;
    }

    public void setBestMovieName(String bestMovieName) {
        this.bestMovieName = bestMovieName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

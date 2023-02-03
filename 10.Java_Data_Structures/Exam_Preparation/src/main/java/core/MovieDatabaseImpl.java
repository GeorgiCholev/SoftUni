package core;

import models.Movie;

import java.util.*;
import java.util.stream.Collectors;

public class MovieDatabaseImpl implements MovieDatabase {

    private final Map<String, Movie> moviesById = new HashMap<>();

    //    ordered by rating in descending order and by release year in descending order.
    private final Map<String, TreeSet<Movie>> moviesByActor = new HashMap<>();

    //    ordered by rating in descending order
    private final Map<Integer, TreeSet<Movie>> moviesByYear = new HashMap<>();

    //    The results should be ordered by rating in descending order
    private final TreeMap<Double, LinkedHashSet<Movie>> moviesByRating = new TreeMap<>(Comparator.reverseOrder());


    @Override

    public void addMovie(Movie movie) {
        moviesById.put(movie.getId(), movie);

        for (String actor : movie.getActors()) {
            moviesByActor.computeIfAbsent(actor, k -> new TreeSet<>(ratingDesc_YearDesc())).add(movie);
        }

        moviesByYear.computeIfAbsent(movie.getReleaseYear(), k -> new TreeSet<>(ratingDesc_YearDesc())).add(movie);

        moviesByRating.computeIfAbsent(movie.getRating(), k -> new LinkedHashSet<>()).add(movie);
    }

    @Override
    public void removeMovie(String movieId) {
        Movie removedMovie = moviesById.remove(movieId);
        if (removedMovie == null) {
            throw new IllegalArgumentException();
        }

        for (String actor : removedMovie.getActors()) {
            moviesByActor.computeIfAbsent(actor, k -> new TreeSet<>(ratingDesc_YearDesc())).remove(removedMovie);
        }

        moviesByYear.computeIfAbsent(removedMovie.getReleaseYear(), k -> new TreeSet<>(ratingDesc_YearDesc())).remove(removedMovie);

        moviesByRating.computeIfAbsent(removedMovie.getRating(), k -> new LinkedHashSet<>()).remove(removedMovie);
    }

    @Override
    public int size() {
        return moviesById.size();
    }

    @Override
    public boolean contains(Movie movie) {
        return contains(movie.getId());
    }

    private boolean contains(String movieId) {
        return moviesById.get(movieId) != null;
    }

    @Override
    public Iterable<Movie> getMoviesByActor(String actorName) {
        TreeSet<Movie> get = moviesByActor.get(actorName);
        if (get == null || get.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return get;
    }

    @Override
    public Iterable<Movie> getMoviesByActors(List<String> actors) {
        Set<Movie> mutualMovies = new HashSet<>();

        Iterator<String> iterator = actors.iterator();
        if (iterator.hasNext()) {
            mutualMovies = (Set<Movie>) getMoviesByActor(iterator.next());
        }

        while (iterator.hasNext() && mutualMovies.size() != 0) {
            Set<Movie> moviesFor = (Set<Movie>) getMoviesByActor(iterator.next());
            mutualMovies.retainAll(moviesFor);
        }

        if (mutualMovies.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return mutualMovies;
    }

    @Override
    public Iterable<Movie> getMoviesByYear(Integer releaseYear) {
        TreeSet<Movie> movies = moviesByYear.get(releaseYear);
        return movies != null ? movies : Collections.emptyList();
    }

    @Override
    public Iterable<Movie> getMoviesInRatingRange(double lowerBound, double upperBound) {
        NavigableMap<Double, LinkedHashSet<Movie>> inRange
                = moviesByRating.subMap(upperBound, true, lowerBound, true);

        List<Movie> movies = new ArrayList<>();
        for (Set<Movie> set : inRange.values()) {
            movies.addAll(set);
        }

        return movies;
    }

    @Override
    public Iterable<Movie> getAllMoviesOrderedByActorPopularityThenByRatingThenByYear() {

        List<Movie> movies = new ArrayList<>();
        moviesById.forEach((key, value) -> movies.add(value));

        return movies.stream().sorted(actorRoleCountDesc_RatingAsc_YearDesc()).collect(Collectors.toList());
    }

    //        their actors played in descending order,
    //        then by rating in descending order
    //        and then by release year in descending order
    private Comparator<Movie> actorRoleCountDesc_RatingAsc_YearDesc() {
        return (m1, m2) -> {
            int compare = Integer.compare(sumForActorsRoles(m2), sumForActorsRoles(m1));
            if (compare != 0) {
                return compare;
            }
            compare = Double.compare(m2.getRating(), m1.getRating());
            if (compare != 0) {
                return compare;
            }

            compare = Integer.compare(m2.getReleaseYear(), m1.getReleaseYear());
            return compare != 0 ? compare : m1.getId().compareTo(m2.getId());
        };
    }

    private int sumForActorsRoles(Movie m) {
        int sum = 0;
        for (String actor : m.getActors()) {
            TreeSet<Movie> movies = moviesByActor.get(actor);
            if (movies != null) {
                sum += movies.size();
            }
        }
        return sum;
    }

    //            by rating in descending order and by release year in descending order.
    private Comparator<Movie> ratingDesc_YearDesc() {
        return (m1, m2) -> {
            int compare = Double.compare(m2.getRating(), m1.getRating());
            if (compare != 0) {
                return compare;
            }
            compare = Integer.compare(m2.getReleaseYear(), m1.getReleaseYear());
            return compare != 0 ? compare : m1.getId().compareTo(m2.getId());
        };
    }
}

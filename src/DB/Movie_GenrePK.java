package DB;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class Movie_GenrePK implements Serializable {
    private int movieId;
    private int genreId;

    @Column(name = "MovieID")
    @Id
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Column(name = "GenreID")
    @Id
    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie_GenrePK that = (Movie_GenrePK) o;
        return movieId == that.movieId &&
                genreId == that.genreId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, genreId);
    }
}

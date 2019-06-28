package DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Movies_Genres", schema = "dbo", catalog = "OnlineMovies")
@IdClass(Movie_GenrePK.class)
public class Movie_Genre {
    private int movieId;
    private int genreId;

    @Id
    @Column(name = "MovieID")
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Id
    @Column(name = "GenreID")
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
        Movie_Genre that = (Movie_Genre) o;
        return movieId == that.movieId &&
                genreId == that.genreId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, genreId);
    }
}

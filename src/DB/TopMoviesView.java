package DB;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Table(name = "showTopMovies1", schema = "dbo", catalog = "OnlineMovies")
public class TopMoviesView {

    private int id;
    private String genre;
    private String movie;
    private int views;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GenreID")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Genre")
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Basic
    @Column(name = "Movie")
    public String getMovie() {
        return movie;
    }
    public void setMovie(String movie) {
        this.movie = movie;
    }

    @Basic
    @Column(name = "Views")
    public int getViews() {
        return views;
    }
    public void setViews(int views) {
        this.views = views;
    }


}

package DB;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Genres", schema = "dbo", catalog = "OnlineMovies")
@Access(AccessType.PROPERTY)

public class Genre {
    private int id;
    private String name;
    private List<Movie> movieList = new ArrayList<>();

    public Genre(){}
    public Genre(String name){
        this.name = name;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "genreList", cascade = CascadeType.ALL)
//    @Fetch(value = FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Movie> getMovieList() {
        return movieList;
    }
    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return id == genre.id &&
                Objects.equals(name, genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }


    //TODO: ако stackoverflow-не ---> от тук и toString на Movies е
    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", movieList=" + movieList +
                '}';
    }
}

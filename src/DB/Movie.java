package DB;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Movies", schema = "dbo", catalog = "OnlineMovies")
@Access(AccessType.PROPERTY)
public class Movie {
    private int id;
    private String name;
    private Integer views;
    private double price;
    private List<Genre> genreList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();

    public Movie(){}
    public Movie(String name, double price, Genre ...genres){
        this.name = name;
        this.price = price;
        for(int i = 0; i<genres.length; i++) {
            this.getGenreList().add(genres[i]);
            genres[i].getMovieList().add(this);
        }

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


    @Basic
    @Column(name = "views")
    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToMany(cascade = CascadeType.ALL)
//    @Fetch(value = FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "Movies_Genres",
            joinColumns = {@JoinColumn(name = "MovieID")},
            inverseJoinColumns = {@JoinColumn(name = "GenreID")}
    )
    public List<Genre> getGenreList() {
        return genreList;
    }
    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    @ManyToMany(mappedBy = "movieList", cascade = CascadeType.ALL)
//    @Fetch(value = FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<User> getUserList() {
        return userList;
    }
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id &&
                Objects.equals(name, movie.name) &&
                Objects.equals(views, movie.views);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, views);
    }


    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", views=" + views +
                ", price=" + price +
                ", genreList=" + genreList +
                ", userList=" + userList +
                '}';
    }
}

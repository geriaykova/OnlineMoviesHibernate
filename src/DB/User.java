package DB;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Users", schema = "dbo", catalog = "OnlineMovies")
@Access(AccessType.PROPERTY)
public class User {
    private int id;
    private String name;
    private String email;
    private double balance;
    private List<Movie> movieList = new ArrayList<>();

    public User(){}
    public User(String name, String email, double balance){
        this.name = name;
        this.email = email;
        this.balance = balance;
        //UserDetail ud = new UserDetail(name, EGN);
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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "balance")
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Users_Movies",
            joinColumns = {@JoinColumn(name = "UserID")},
            inverseJoinColumns = {@JoinColumn(name = "MovieID")}
    )
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
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", balance=" + getBalance() +
                ", movieList=" + getMovieList() +
                '}';
    }

}

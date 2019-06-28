//package DB;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.IdClass;
//import java.util.Objects;
//
//@Entity
//@IdClass(Users_MoviesPK.class)
//public class Users_Movies {
//    private int userId;
//    private int movieId;
//
//    @Id
//    @Column(name = "UserID")
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    @Id
//    @Column(name = "MovieID")
//    public int getMovieId() {
//        return movieId;
//    }
//
//    public void setMovieId(int movieId) {
//        this.movieId = movieId;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Users_Movies that = (Users_Movies) o;
//        return userId == that.userId &&
//                movieId == that.movieId;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, movieId);
//    }
//}

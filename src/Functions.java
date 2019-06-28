import DB.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Functions {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    //добавяне на жанр, филм и потребител
    public static Genre insertGenre(String name){
        final Session session = getSession();
        session.beginTransaction();

        Genre g = new Genre(name);

        session.save(g);
        session.getTransaction().commit();
        session.close();
        return g;
    }

    public static Movie insertMovie(String name, double price, Genre ...genres){
        final Session session = getSession();
        session.beginTransaction();

        Movie m = new Movie(name, price, genres);

        session.save(m);
        session.getTransaction().commit();
        session.close();
        return m;
    }

    public static User insertUser(String name, String email, double balance){
        final Session session = getSession();
        session.beginTransaction();

        User u = new User(name, email, balance);

        session.save(u);
        session.getTransaction().commit();
        session.close();
        return u;
    }


    //добавяне на пари в сметката
    public static void depositMoney(User u, double money){
        u.setBalance(u.getBalance() + money);
    }

    //купуване на филм
    public static void buyMovie(int userID, int movieID){
        final Session session = getSession();
        session.beginTransaction();

        User user = getUserById(userID);
        Movie movie = getMovieById(movieID);
        System.out.println("User " + user.getName() + "'s old balance: " + user.getBalance());
        //таксуване на потребителя
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);

        user.setBalance(user.getBalance() - movie.getPrice());

        //добавяне на филма към списъка му с филми
        user.getMovieList().add(movie);
        System.out.println("Movie " + movie.getName() + "'s price: " + movie.getPrice());

        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public static void watchMovie(int userId, int movieID){

        User user = getUserById(userId);
        Movie movie = getMovieById(movieID);
        //проверяваме дали филма е вече в списъка с филми на потребителя
        if(user.getMovieList().contains(movie)){
            System.out.println("Enjoy watching: " + movie.getName());
        }
        else{
            System.out.println("Buying movie....");
            buyMovie(userId, movieID);
        }

        //увеличаване на броя гледания с 1
        increaseViews(movieID);

    }

    public static Genre getGenreById(int id) {
        final Session session = getSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Genre> query = builder.createQuery(Genre.class);
        Root<Genre> root = query.from(Genre.class);

        query.where(builder.equal(root.get("id"), id));

        Genre genre = session.createQuery(query).getSingleResult();

        session.getTransaction().commit();
        session.close();

        return genre;
    }

    public static Movie getMovieById(int id) {
        final Session session = getSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
        Root<Movie> root = query.from(Movie.class);

        query.where(builder.equal(root.get("id"), id));

        Movie movie = session.createQuery(query).getSingleResult();

        session.getTransaction().commit();
        session.close();

        return movie;
    }

    public static User getUserById(int id) {
        final Session session = getSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        query.where(builder.equal(root.get("id"), id));

        User user = session.createQuery(query).getSingleResult();

        session.getTransaction().commit();
        session.close();

        return user;
    }


    public static void increaseViews(int movieId){
        final Session session = getSession();
        session.beginTransaction();

        Movie movie = getMovieById(movieId);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
        Root<Movie> root = query.from(Movie.class);
        query.select(root);

        movie.setViews(movie.getViews() + 1);

        session.update(movie);
        session.getTransaction().commit();
        session.close();
    }

    public static void showTopMovies(){
        final Session session = getSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TopMoviesView> criteriaQuery = builder.createQuery(TopMoviesView.class);
        Root<TopMoviesView> topMoviesViewRoot = criteriaQuery.from(TopMoviesView.class);
        criteriaQuery.select(topMoviesViewRoot);

        List<TopMoviesView> topMoviesViewList = session.createQuery(criteriaQuery).getResultList();
        for(TopMoviesView topMoviesView : topMoviesViewList){
            System.out.println(topMoviesView.getGenre() + "   "
                    + topMoviesView.getMovie() + "   " +
                    topMoviesView.getViews());
            System.out.println("\n");
        }

        session.getTransaction().commit();
        session.close();
    }


    //TODO: предложения с топ най-гледаните филми по категории от всички потребители.
    // направена е процедура showTopMovies в базата
    //SELECT DISTINCT g.name, m.name, m.views FROM Movies AS m
    // JOIN Movies_Genres AS m_g
    // ON (m.id = m_g.MovieID)
    // JOIN Genres AS g
    // ON (g.id = m_g.GenreID)
    // GROUP BY g.name, m.name, m.views
    // ORDER BY views DESC



}

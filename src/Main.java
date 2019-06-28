import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/*
Имаме система за гледане на филми онлайн, които са платени.
Като потребител аз трябва да мога да се регистрирам с име и имейл.
Като потребител мога да депозирам колкото си искам пари.
При всяко гледане на филм моя депозит се намалява с цената за гледане на съответния филм.
Ако веднъж съм платил за определен филм, то мога да го гледам колкото си искам пъти.
Също така за системата е важно да отчита колко пъти е бил гледан всеки филм.
Филмите се разпределят по категории: Ужаси, фентъзи, тъпи филми без съдържание(Game of Thrones) и т.н.
Когато като потребител влезна в системата, тя ми показа предложения с топ най-гледаните филми по категории от всички потребители.
*/

public class Main {
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

    public static void main(final String[] args) throws Exception {
//        final Session session = getSession();
//        session.beginTransaction();

//        User user1 = new User("mimi", "mimi@abv.bg", 200.20);
//        session.save(user1);
//        Genre horror = new Genre("Horror");
//        Genre thriller = new Genre("Thriller");
//        //Movie movie1 = new Movie("Amytiville Horror", 5.50, horror, thriller);
//        Movie movie2 = new Movie("Horrorche", 3.60);
//        movie2.getGenreList().add(horror);
//        movie2.getGenreList().add(thriller);
        //session.save(movie2);

//        User user3 = new User("bobo", "hhfhfhf", 500);
//        Genre comedy = new Genre("comedy");
//        Movie movie3 = new Movie("American pie", 3.00, comedy);
//        session.save(user3);
//        session.save(movie3);
////        movie3.getGenreList().add(comedy);
////        comedy.getMovieList().add(movie3);
////        user2.getMovieList().add(movie3);
//        System.out.println(user3);


//        Functions.insertGenre("Sci-fi");
//        Functions.insertGenre("Drama");
//        Functions.insertUser("Geri", "geri@abv.bg", 300);
//        Functions.insertMovie("Izvynzemnite", 5.00, Functions.getGenreById(23), Functions.getGenreById(24));

//        Movie izvynzemnite2 = Functions.getMovieById(19);
//        User geri2 = Functions.getUserById(14);
//        Functions.buyMovie(geri2, izvynzemnite2);
//        Functions.buyMovie(Functions.getUserById(14), Functions.getMovieById(19));
//        Functions.watchMovie(Functions.getUserById(14), Functions.getMovieById(19));
//        Functions.watchMovie(Functions.getUserById(14), Functions.getMovieById(4));
//        Functions.buyMovie(Functions.getUserById(14), Functions.getMovieById(19));
//        Functions.buyMovie(14, 19);
//        Functions.buyMovie(14,4);

//        Functions.buyMovie(1,19);
//        Functions.watchMovie(1,19);
//        System.out.println(Functions.getUserById(1).getMovieList());

//        Functions.insertUserDetail(14, "Gergana Gergana", "1234567890");
//        Functions.insertUserDetail(11, "bobchoto", "1234567890");
//        System.err.println(Functions.getUserById(14).getUserDetail());

//        System.out.println(Functions.getUserDetailById(1));
//        Functions.showTopMovies();


        Functions.showTopMovies();
//        session.getTransaction().commit();
//        session.close();




    }
}
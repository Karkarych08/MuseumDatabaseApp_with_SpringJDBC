package university.app.Interfaces;

import university.app.dao.artistDAO;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;

public interface artist {

    Object findAllartist() throws SQLException;

    Collection<artistDAO> findAllartistbycountry(String country);

    Collection<artistDAO> findAllartistbydate(Calendar date);

    Collection<artistDAO> findById(long id);

    void insert (String firstname, String secondname, String familyname, Calendar dateofbirth, String country, Calendar dateofdeath);

    void update (artistDAO artist);

    void deletebyId (artistDAO artist);
}

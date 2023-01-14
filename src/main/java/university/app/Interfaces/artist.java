package university.app.Interfaces;

import university.app.dao.artistDAO;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public interface artist {

    Object findAllartist() throws SQLException;

    Collection<artistDAO> findAllartistbycountry(String country);

    Collection<artistDAO> findAllartistbydate(Calendar date);

    Collection<artistDAO> findById(long id);

    void insert (String firstname, String secondname, String familyname, Calendar dateofbirth, String country, Calendar dateofdeath);

    void update (long id, String firstname, String secondname, String familyname, Date dateofbirth, String country, Date dateofdeath) throws IllegalAccessException;

    void deletebyId (long id);
}

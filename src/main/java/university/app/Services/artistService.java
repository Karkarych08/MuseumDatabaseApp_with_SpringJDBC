package university.app.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import university.app.Interfaces.artist;
import university.app.Interfaces.artistRepository;
import university.app.dao.artistDAO;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class artistService implements artist {

    private final artistRepository aRep;

    @Override
    public Collection<artistDAO> findAllartist() throws SQLException {

        return aRep.findAll();
    }

    public Collection<artistDAO> findAllartistbycountry(String country){

        return aRep.findAllByCountry(country);
    }

    public Collection<artistDAO> findAllartistbydate(Calendar date) {

        return aRep.findOlderThenDate(date);
    }

    public Collection<artistDAO> findById(long id){
        return aRep.findById(id);
    }

    @Override
    public void insert(String firstname, String secondname, String familyname, Calendar dateofbirth, String country, Calendar dateofdeath) {
        aRep.insert(firstname, secondname, familyname, dateofbirth, country, dateofdeath);
    }

    @Override
    public void update(long id, String firstname, String secondname, String familyname, Date dateofbirth, String country, Date dateofdeath){
        aRep.update(id,firstname, secondname, familyname, dateofbirth, country, dateofdeath);
    }

    @Override
    public void deletebyId(long id) {
        aRep.deletebyId(id);
    }

}

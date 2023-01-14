package university.app.Services;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import university.app.Interfaces.Locale_If;
import university.app.Services.Exceptions.LocaleNotSupportedException;
import university.app.dao.artistDAO;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Scanner;

@ShellComponent
@RequiredArgsConstructor
public class UserInterface {

    private final artistService artistService;
    private final Massage_localization message;
    private final Locale_If locale;
    private final Scanner in = new Scanner(System.in);

    @ShellMethod("Find all persons")
    public void findAll() throws SQLException {
        for (artistDAO artist : artistService.findAllartist()) {
            System.out.println(artist);
        }
    }

    @ShellMethod("Find by parameter")
    public void findby(@ShellOption @NotNull String parameter){
        switch (parameter){
            case "country" -> {
                    System.out.print(message.localize("countryENTER"));
                    String country = in.next();
                    if (!Objects.equals(artistService.findAllartistbycountry(country).toString(), "[]"))
                        for (artistDAO artist: artistService.findAllartistbycountry(country)){
                            System.out.println(artist);
                        }
                    else System.out.println(message.localize("NoMatchingData"));
            }
            case "date" -> {
                Calendar date = new GregorianCalendar();
                System.out.print(message.localize("dateENTER"));
                date.set(in.nextInt(), in.nextInt(),in.nextInt());
                if (!Objects.equals(artistService.findAllartistbydate(date).toString(), "[]"))
                    for (artistDAO artist: artistService.findAllartistbydate(date)){
                        System.out.println(artist);
                    }
                else System.out.println(message.localize("NoMatchingData"));
            }
            case "id" -> {
                System.out.print(message.localize("EnterID"));
                long id = in.nextLong();
                if (!Objects.equals(artistService.findById(id).toString(), "[]"))
                    for (artistDAO artist: artistService.findById(id)){
                        System.out.println(artist);
                }
                else System.out.println(message.localize("NoMatchingData"));
        }
            default -> System.out.println(message.localize("defaultFindByMSG"));
        }
    }

    @ShellMethod("Change Language")
    public void changeLanguage(){
        System.out.print(message.localize("ChangeLanguageMSG"));
        try {
            locale.set(in.next().toLowerCase());
        }catch (LocaleNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @ShellMethod("Insert artist")
    public void insert(@ShellOption String firstname,
                       @ShellOption String secondname,
                       @ShellOption String familyname,
                       @ShellOption Integer dayofbirth,
                       @ShellOption Integer mounthofbirth,
                       @ShellOption Integer yearofbirth,
                       @ShellOption String country,
                       @ShellOption Integer dayofdeath,
                       @ShellOption Integer mounthofdeath,
                       @ShellOption Integer yearofdeath){
        Calendar datebirth = new GregorianCalendar(dayofbirth,mounthofbirth,yearofbirth);
        Calendar datedeath = new GregorianCalendar(dayofdeath,mounthofdeath,yearofdeath);
        try {
            artistService.insert(firstname,secondname,familyname,datebirth,country,datedeath);
            System.out.println(message.localize("InsertComplete"));
        }catch (Exception e){
            System.out.println(message.localize("InsertError"));
        }
    }

    @ShellMethod("Insert artist manually")
    public void insertManually(){
        Calendar dateofbirth = new GregorianCalendar();
        Calendar dateofdeath = new GregorianCalendar();
        System.out.println(message.localize("firstnameEnter"));
        String firstname = in.next();
        System.out.println(message.localize("secondnameEnter"));
        String secondname = in.next();
        System.out.println(message.localize("familynameEnter"));
        String familyname = in.next();
        System.out.println(message.localize("dateofbirthENTER"));
        dateofbirth.set(in.nextInt(), in.nextInt(),in.nextInt());
        System.out.println(message.localize("dateofdeathENTER"));
        dateofdeath.set(in.nextInt(), in.nextInt(),in.nextInt());
        System.out.println(message.localize("countryENTER"));
        String country = in.next();
        try {
            artistService.insert(firstname,secondname,familyname,dateofbirth,country,dateofdeath);
            System.out.println(message.localize("InsertComplete"));
        }catch (Exception e){
            System.out.println(message.localize("InsertError"));
            e.printStackTrace();
        }
    }
}

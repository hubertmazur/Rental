package pl.mazur.rental;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Util {

    public Date parseDate(SimpleDateFormat simpleDateFormat, String date) throws ParseException {
        return simpleDateFormat.parse(date);
    }

}

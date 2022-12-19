package softuni.exam.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateXmlAdapter extends XmlAdapter<String, LocalDate> {

    private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate unmarshal(String v) {
        try {
            return LocalDate.parse(v, PATTERN);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public String marshal(LocalDate v) {
        return v.format(PATTERN);
    }
}

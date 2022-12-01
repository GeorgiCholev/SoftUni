package softuni.exam.util.xmlAdapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateXmlAdapter extends XmlAdapter<String, LocalDate> {

    private static final  DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v, PATTERN);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.format(PATTERN);
    }
}

package softuni.exam.util.xmlAdapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeXmlAdapter extends XmlAdapter<String, LocalDateTime> {

    private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        try {
            return LocalDateTime.parse(v, PATTERN);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        return v.format(PATTERN);
    }
}

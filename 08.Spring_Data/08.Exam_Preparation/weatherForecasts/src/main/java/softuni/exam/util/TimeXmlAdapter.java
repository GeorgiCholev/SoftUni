package softuni.exam.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Time;

public class TimeXmlAdapter extends XmlAdapter<String, Time> {
    @Override
    public Time unmarshal(String v) throws Exception {
        return Time.valueOf(v);
    }

    @Override
    public String marshal(Time v) throws Exception {
        return v.toString();
    }
}

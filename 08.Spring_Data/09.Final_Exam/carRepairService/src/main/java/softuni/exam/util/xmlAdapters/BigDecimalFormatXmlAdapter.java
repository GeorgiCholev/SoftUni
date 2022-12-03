package softuni.exam.util.xmlAdapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;

public class BigDecimalFormatXmlAdapter extends XmlAdapter<String, BigDecimal> {

    @Override
    public BigDecimal unmarshal(String v) {
        try {
            return new BigDecimal(String.format("%.2f", Double.parseDouble(v)));
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }

    @Override
    public String marshal(BigDecimal v) {
        return v.toString();
    }
}

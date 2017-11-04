import java.text.DateFormat;
import java.util.Currency;
import java.util.Date;

public class TestClass {
    public static long longValue = 1323214L;
    public double doubleValue = 1.2;
    public Integer intValue = 7513;
    public String stringValue = "asd";
    public byte byteValue = 1;
    public char charValue = 'c';
    public String dateValue = DateFormat.getDateInstance(DateFormat.DEFAULT, MainFrame.currentLocale).format(new Date());
    public Currency currencyValue = Currency.getInstance(MainFrame.currentLocale);
    public boolean booleanValue = true;
}

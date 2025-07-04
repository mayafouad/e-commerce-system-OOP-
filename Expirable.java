import java.sql.Date;

public interface Expirable {
    void setExpirationDate(Date date);
    Date getExpirationDate();
}

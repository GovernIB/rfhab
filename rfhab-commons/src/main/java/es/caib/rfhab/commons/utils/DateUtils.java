package es.caib.rfhab.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author jpou
 * 
 */
public class DateUtils {

    protected String fullDateToDate(String fullDate) {

        if (fullDate == null)
            return null;

        String strDate = fullDate;
        SimpleDateFormat sdfFullDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date;
        try {
            date = sdfFullDate.parse(fullDate);
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
            strDate = sdfDate.format(date);
        } catch (ParseException ex) {
            Logger.getLogger(StringUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return strDate;
    }
}

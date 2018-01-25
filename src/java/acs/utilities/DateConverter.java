/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.utilities;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Aimable
 */
public class DateConverter implements Serializable {

    public DateConverter() {
    }

    public LocalDateTime stringToDateTime(String dateString) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            if (!(dateString instanceof String)) {
                throw new IllegalArgumentException("The value submitted cannot be converted into date format");
            }
            else {
                return LocalDateTime.parse(dateString, formatter);
            }
        } catch (DateTimeParseException ex) {
            return null;
        }
    }
    
    public LocalDate stringToDate(String dateString) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            if (!(dateString instanceof String)) {
                throw new IllegalArgumentException("The value submitted cannot be converted into date format");
            }
            else {
                return LocalDate.parse(dateString, formatter);
            }
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    public String dateTimeToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (!(date instanceof LocalDateTime)) {
            throw new IllegalArgumentException("The value submitted cannot be converted into string format");
        }
        else {
            return date.format(formatter);
        }
    }

    
    public String dateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (!(date instanceof LocalDate)) {
            throw new IllegalArgumentException("The value submitted cannot be converted into string format");
        }
        else {
            return date.format(formatter);
        }
    }

}

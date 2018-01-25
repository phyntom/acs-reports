/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.entities;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author aimable
 */
@Converter(autoApply = false)
public class DateTimeConverter implements AttributeConverter<String, Timestamp> {

    @Override
    public java.sql.Timestamp convertToDatabaseColumn(String localDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); // your format
            Date date = format.parse(localDate);
            return new Timestamp(date.getTime());
        } catch (ParseException ex) {
            ex.printStackTrace();
            return new Timestamp(new Date().getTime());
        }
    }

    @Override
    public String convertToEntityAttribute(Timestamp timestamp) {
        return timestamp.toString();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.entities;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author aimable
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public java.sql.Date convertToDatabaseColumn(java.time.LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    @Override
    public java.time.LocalDate convertToEntityAttribute(java.sql.Date timestamp) {
        return timestamp.toLocalDate();
    }
}

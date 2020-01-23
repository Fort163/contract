package test.contract.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Created by Павел on 19.01.2020.
 */
@Entity(name = "propertyType")
@Table(name = "PROPERTY_TYPE")
public class PropertyType extends HandbookEntity {
    private static final long serialVersionUID = 2464308697146179693L;

}

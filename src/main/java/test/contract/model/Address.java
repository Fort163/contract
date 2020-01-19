package test.contract.model;

import javax.persistence.*;
/**
 * Created by Павел on 19.01.2020.
 */
@Entity(name = "address")
@Table(name = "ADDRESS")
public class Address extends AuditEntity {
    private static final long serialVersionUID = -7348940154440063343L;
    private String state;
    private String index;
    private String region;
    private String district;
    private String street;
    private Integer house;
    private String housing;
    private String building;
    private Integer flat;
    private Man man;
    private PropertyType propertyType;

    @Column(name = "STATE")
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "INDEX")
    public String getIndex() {
        return index;
    }
    public void setIndex(String index) {
        this.index = index;
    }

    @Column(name = "REGION")
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }

    @Column(name = "DISTRICT")
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }

    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "HOUSE")
    public Integer getHouse() {
        return house;
    }
    public void setHouse(Integer house) {
        this.house = house;
    }

    @Column(name = "HOUSING")
    public String getHousing() {
        return housing;
    }
    public void setHousing(String housing) {
        this.housing = housing;
    }

    @Column(name = "BUILDING")
    public String getBuilding() {
        return building;
    }
    public void setBuilding(String building) {
        this.building = building;
    }

    @Column(name = "FLAT")
    public Integer getFlat() {
        return flat;
    }
    public void setFlat(Integer flat) {
        this.flat = flat;
    }

    @ManyToOne
    @JoinColumn(name = "FK_PROPERTY_TYPE_ID", foreignKey = @ForeignKey(name = "ADDRESS_2_PROPERTY_TYPE"))
    public PropertyType getPropertyType() {
        return propertyType;
    }
    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    @ManyToOne
    @JoinColumn(name = "FK_MAN_ID", foreignKey = @ForeignKey(name = "ADDRESS_2_MAN"))
    public Man getMan() {
        return this.man;
    }
    public void setMan(Man man) {
        this.man = man;
    }


}

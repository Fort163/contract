package test.contract.vo;

import test.contract.model.Address;
import test.contract.model.Contract;
import test.contract.model.Man;
import test.contract.model.Pasport;
import test.contract.utils.DateHelpler;

import java.io.Serializable;

/**
 * Created by Павел on 23.01.2020.
 */
public class ContractVO implements Serializable{

    private Long contractId;
    private Long addressId;
    private String num;
    private String conclusionDate;
    private Double insuranceAmount;
    private Double insurancePremium;
    private String calculationDate;
    private String periodFrom;
    private String periodTo;
    private Long manId;
    private Long propertyType;
    private Long coefficientArea;
    private Long coefficientConstruction;
    private String comment;
    private String state;
    private String index;
    private String region;
    private String district;
    private String city;
    private String street;
    private Integer house;
    private String housing;
    private String building;
    private Integer flat;
    private String fullName;
    private String birthDay;
    private Long seriesDoc;
    private Long numDoc;

    public ContractVO() {
    }

    public ContractVO(Contract contract) {
        Man man = contract.getMan();
        Pasport pasport = man.getPasport();
        Address address = man.getAddressList().get(0);
        this.contractId = contract.getId();
        this.addressId = address.getId();
        this.num = contract.getNum();
        this.conclusionDate = DateHelpler.localDateToString(contract.getConclusionDate());
        this.insuranceAmount = contract.getInsuranceAmount();
        this.insurancePremium = contract.getInsurancePremium();
        this.calculationDate = DateHelpler.localDateToString(contract.getCalculationDate());;
        this.periodFrom = DateHelpler.localDateToString(contract.getPeriodFrom());;
        this.periodTo = DateHelpler.localDateToString(contract.getPeriodTo());;
        this.manId = man.getId();
        this.propertyType = contract.getPropertyType().getId();
        this.coefficientArea = contract.getCoefficientArea().getId();
        this.coefficientConstruction = contract.getCoefficientConstruction().getId();
        this.comment = contract.getComment();
        this.state = address.getState();
        this.index = address.getIndex();
        this.region = address.getRegion();
        this.district = address.getDistrict();
        this.city = address.getCity();
        this.street = address.getStreet();
        this.house = address.getHouse();
        this.housing = address.getHousing();
        this.building = address.getBuilding();
        this.flat = address.getFlat();
        this.fullName = new StringBuilder(man.getLastName()).append(" ")
                .append(man.getFirstName()).append(" ")
                .append(man.getSecondName()).toString();
        this.birthDay =  DateHelpler.localDateToString(man.getBirthDay());
        this.seriesDoc = pasport.getSeriesDoc();
        this.numDoc = pasport.getNumDoc();
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getConclusionDate() {
        return conclusionDate;
    }

    public void setConclusionDate(String conclusionDate) {
        this.conclusionDate = conclusionDate;
    }

    public Double getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(Double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public Double getInsurancePremium() {
        return insurancePremium;
    }

    public void setInsurancePremium(Double insurancePremium) {
        this.insurancePremium = insurancePremium;
    }

    public String getCalculationDate() {
        return calculationDate;
    }

    public void setCalculationDate(String calculationDate) {
        this.calculationDate = calculationDate;
    }

    public String getPeriodFrom() {
        return periodFrom;
    }

    public void setPeriodFrom(String periodFrom) {
        this.periodFrom = periodFrom;
    }

    public String getPeriodTo() {
        return periodTo;
    }

    public void setPeriodTo(String periodTo) {
        this.periodTo = periodTo;
    }

    public Long getManId() {
        return manId;
    }

    public void setManId(Long manId) {
        this.manId = manId;
    }

    public Long getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(Long propertyType) {
        this.propertyType = propertyType;
    }

    public Long getCoefficientArea() {
        return coefficientArea;
    }

    public void setCoefficientArea(Long coefficientArea) {
        this.coefficientArea = coefficientArea;
    }

    public Long getCoefficientConstruction() {
        return coefficientConstruction;
    }

    public void setCoefficientConstruction(Long coefficientConstruction) {
        this.coefficientConstruction = coefficientConstruction;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    public String getHousing() {
        return housing;
    }

    public void setHousing(String housing) {
        this.housing = housing;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Integer getFlat() {
        return flat;
    }

    public void setFlat(Integer flat) {
        this.flat = flat;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public Long getSeriesDoc() {
        return seriesDoc;
    }

    public void setSeriesDoc(Long seriesDoc) {
        this.seriesDoc = seriesDoc;
    }

    public Long getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(Long numDoc) {
        this.numDoc = numDoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractVO that = (ContractVO) o;

        if (contractId != null ? !contractId.equals(that.contractId) : that.contractId != null) return false;
        if (addressId != null ? !addressId.equals(that.addressId) : that.addressId != null) return false;
        if (num != null ? !num.equals(that.num) : that.num != null) return false;
        if (conclusionDate != null ? !conclusionDate.equals(that.conclusionDate) : that.conclusionDate != null)
            return false;
        if (insuranceAmount != null ? !insuranceAmount.equals(that.insuranceAmount) : that.insuranceAmount != null)
            return false;
        if (insurancePremium != null ? !insurancePremium.equals(that.insurancePremium) : that.insurancePremium != null)
            return false;
        if (calculationDate != null ? !calculationDate.equals(that.calculationDate) : that.calculationDate != null)
            return false;
        if (periodFrom != null ? !periodFrom.equals(that.periodFrom) : that.periodFrom != null) return false;
        if (periodTo != null ? !periodTo.equals(that.periodTo) : that.periodTo != null) return false;
        if (manId != null ? !manId.equals(that.manId) : that.manId != null) return false;
        if (propertyType != null ? !propertyType.equals(that.propertyType) : that.propertyType != null) return false;
        if (coefficientArea != null ? !coefficientArea.equals(that.coefficientArea) : that.coefficientArea != null)
            return false;
        if (coefficientConstruction != null ? !coefficientConstruction.equals(that.coefficientConstruction) : that.coefficientConstruction != null)
            return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (index != null ? !index.equals(that.index) : that.index != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (house != null ? !house.equals(that.house) : that.house != null) return false;
        if (housing != null ? !housing.equals(that.housing) : that.housing != null) return false;
        if (building != null ? !building.equals(that.building) : that.building != null) return false;
        if (flat != null ? !flat.equals(that.flat) : that.flat != null) return false;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        if (birthDay != null ? !birthDay.equals(that.birthDay) : that.birthDay != null) return false;
        if (seriesDoc != null ? !seriesDoc.equals(that.seriesDoc) : that.seriesDoc != null) return false;
        return numDoc != null ? numDoc.equals(that.numDoc) : that.numDoc == null;
    }

    @Override
    public int hashCode() {
        int result = contractId != null ? contractId.hashCode() : 0;
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + (conclusionDate != null ? conclusionDate.hashCode() : 0);
        result = 31 * result + (insuranceAmount != null ? insuranceAmount.hashCode() : 0);
        result = 31 * result + (insurancePremium != null ? insurancePremium.hashCode() : 0);
        result = 31 * result + (calculationDate != null ? calculationDate.hashCode() : 0);
        result = 31 * result + (periodFrom != null ? periodFrom.hashCode() : 0);
        result = 31 * result + (periodTo != null ? periodTo.hashCode() : 0);
        result = 31 * result + (manId != null ? manId.hashCode() : 0);
        result = 31 * result + (propertyType != null ? propertyType.hashCode() : 0);
        result = 31 * result + (coefficientArea != null ? coefficientArea.hashCode() : 0);
        result = 31 * result + (coefficientConstruction != null ? coefficientConstruction.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (index != null ? index.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (house != null ? house.hashCode() : 0);
        result = 31 * result + (housing != null ? housing.hashCode() : 0);
        result = 31 * result + (building != null ? building.hashCode() : 0);
        result = 31 * result + (flat != null ? flat.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (birthDay != null ? birthDay.hashCode() : 0);
        result = 31 * result + (seriesDoc != null ? seriesDoc.hashCode() : 0);
        result = 31 * result + (numDoc != null ? numDoc.hashCode() : 0);
        return result;
    }
}

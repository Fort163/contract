package test.contract.form;

import java.time.LocalDate;

/**
 * Created by Павел on 22.01.2020.
 */
public class CalculationForm {

    private Double insuranceAmount;
    private String periodFrom;
    private String periodTo;
    private Long propertyType;
    private Long coefficientArea;
    private Long coefficientConstruction;

    public Double getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(Double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
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
}

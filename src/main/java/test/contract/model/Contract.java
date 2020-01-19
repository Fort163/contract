package test.contract.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity(name = "contract")
@Table(name = "CONTRACT")
public class Contract extends AuditEntity {
    private static final long serialVersionUID = -6098705130091335324L;

    private Long num;
    private LocalDate conclusionDate;
    private Double insuranceAmount;
    private Double insurancePremium;
    private LocalDate paymentDate;
    private LocalDate periodFrom;
    private LocalDate periodTo;
    private Man man;
    private PropertyType propertyType;
    private CoefficientArea coefficientArea;
    private CoefficientConstruction coefficientConstruction;

    @Column(name = "NUM",unique = true)
    @Size(min=6, max=6)
    @NotNull
    public Long getNum() {
        return num;
    }
    public void setNum(Long num) {
        this.num = num;
    }

    @Column(name = "CONCLUSION_DATE",columnDefinition = "DATE")
    public LocalDate getConclusionDate() {
        return conclusionDate;
    }
    public void setConclusionDate(LocalDate conclusionDate) {
        this.conclusionDate = conclusionDate;
    }

    @Column(name = "INSURANCE_AMOUNT")
    @Digits(integer = 100,fraction = 2)
    public Double getInsuranceAmount() {
        return insuranceAmount;
    }
    public void setInsuranceAmount(Double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    @Column(name = "INSURANCE_PREMIUM")
    @Digits(integer = 100,fraction = 2)
    public Double getInsurancePremium() {
        return insurancePremium;
    }
    public void setInsurancePremium(Double insurancePremium) {
        this.insurancePremium = insurancePremium;
    }

    @Column(name = "PAYMENT_DATE")
    public LocalDate getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Column(name = "PERIOD_FROM",columnDefinition = "DATE")
    public LocalDate getPeriodFrom() {
        return periodFrom;
    }
    public void setPeriodFrom(LocalDate periodFrom) {
        this.periodFrom = periodFrom;
    }

    @Column(name = "PERIOD_TO",columnDefinition = "DATE")
    public LocalDate getPeriodTo() {
        return periodTo;
    }
    public void setPeriodTo(LocalDate periodTo) {
        this.periodTo = periodTo;
    }

    @ManyToOne
    @JoinColumn(name = "FK_MAN_ID", foreignKey = @ForeignKey(name = "CONTRACT_2_MAN"))
    public Man getMan() {
        return man;
    }
    public void setMan(Man man) {
        this.man = man;
    }

    @ManyToOne
    @JoinColumn(name = "FK_PROPERTY_TYPE_ID", foreignKey = @ForeignKey(name = "CONTRACT_2_PROPERTY_TYPE"))
    public PropertyType getPropertyType() {
        return propertyType;
    }
    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    @ManyToOne
    @JoinColumn(name = "FK_COEFFICIENT_AREA_ID", foreignKey = @ForeignKey(name = "CONTRACT_2_COEFFICIENT_AREA"))
    public CoefficientArea getCoefficientArea() {
        return coefficientArea;
    }
    public void setCoefficientArea(CoefficientArea coefficientArea) {
        this.coefficientArea = coefficientArea;
    }

    @ManyToOne
    @JoinColumn(name = "FK_COEFFICIENT_CONSTRUCTION_ID", foreignKey = @ForeignKey(name = "CONTRACT_2_COEFFICIENT_CONSTRUCTION"))
    public CoefficientConstruction getCoefficientConstruction() {
        return coefficientConstruction;
    }
    public void setCoefficientConstruction(CoefficientConstruction coefficientConstruction) {
        this.coefficientConstruction = coefficientConstruction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Contract contract = (Contract) o;

        if (num != null ? !num.equals(contract.num) : contract.num != null) return false;
        if (conclusionDate != null ? !conclusionDate.equals(contract.conclusionDate) : contract.conclusionDate != null)
            return false;
        if (insuranceAmount != null ? !insuranceAmount.equals(contract.insuranceAmount) : contract.insuranceAmount != null)
            return false;
        if (insurancePremium != null ? !insurancePremium.equals(contract.insurancePremium) : contract.insurancePremium != null)
            return false;
        if (paymentDate != null ? !paymentDate.equals(contract.paymentDate) : contract.paymentDate != null)
            return false;
        if (periodFrom != null ? !periodFrom.equals(contract.periodFrom) : contract.periodFrom != null) return false;
        if (periodTo != null ? !periodTo.equals(contract.periodTo) : contract.periodTo != null) return false;
        if (man != null ? !man.equals(contract.man) : contract.man != null) return false;
        if (propertyType != null ? !propertyType.equals(contract.propertyType) : contract.propertyType != null)
            return false;
        if (coefficientArea != null ? !coefficientArea.equals(contract.coefficientArea) : contract.coefficientArea != null)
            return false;
        return coefficientConstruction != null ? coefficientConstruction.equals(contract.coefficientConstruction) : contract.coefficientConstruction == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + (conclusionDate != null ? conclusionDate.hashCode() : 0);
        result = 31 * result + (insuranceAmount != null ? insuranceAmount.hashCode() : 0);
        result = 31 * result + (insurancePremium != null ? insurancePremium.hashCode() : 0);
        result = 31 * result + (paymentDate != null ? paymentDate.hashCode() : 0);
        result = 31 * result + (periodFrom != null ? periodFrom.hashCode() : 0);
        result = 31 * result + (periodTo != null ? periodTo.hashCode() : 0);
        result = 31 * result + (man != null ? man.hashCode() : 0);
        result = 31 * result + (propertyType != null ? propertyType.hashCode() : 0);
        result = 31 * result + (coefficientArea != null ? coefficientArea.hashCode() : 0);
        result = 31 * result + (coefficientConstruction != null ? coefficientConstruction.hashCode() : 0);
        return result;
    }
}

package test.contract.vo;

import java.io.Serializable;

/**
 * Created by Павел on 22.01.2020.
 */
public class ResultCalculationVO implements Serializable{
    private static final long serialVersionUID = 2498338575926812577L;

    private String calculationDate;
    private Double insurancePremium;

    public ResultCalculationVO() {
    }

    public ResultCalculationVO(String calculationDate, Double insurancePremium) {
        this.calculationDate = calculationDate;
        this.insurancePremium = insurancePremium;
    }

    public String getCalculationDate() {
        return calculationDate;
    }

    public void setCalculationDate(String calculationDate) {
        this.calculationDate = calculationDate;
    }

    public Double getInsurancePremium() {
        return insurancePremium;
    }

    public void setInsurancePremium(Double insurancePremium) {
        this.insurancePremium = insurancePremium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultCalculationVO that = (ResultCalculationVO) o;

        if (calculationDate != null ? !calculationDate.equals(that.calculationDate) : that.calculationDate != null)
            return false;
        return insurancePremium != null ? insurancePremium.equals(that.insurancePremium) : that.insurancePremium == null;
    }

    @Override
    public int hashCode() {
        int result = calculationDate != null ? calculationDate.hashCode() : 0;
        result = 31 * result + (insurancePremium != null ? insurancePremium.hashCode() : 0);
        return result;
    }
}

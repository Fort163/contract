package test.contract.vo;

import test.contract.model.Contract;
import test.contract.model.Man;
import test.contract.utils.DateHelpler;

/**
 * Created by kutyakov on 18.01.2020.
 */
public class ContractGridModelVO extends ModelVO{

    private String fullName;
    private String num;
    private String dateConclusion;
    private Double insurancePremium;
    private String fromToDate;

    public ContractGridModelVO(){
    }

    public ContractGridModelVO(Contract contract) {
        super(contract.getId());
        Man man = contract.getMan();
        this.fullName = new StringBuilder(man.getLastName())
                        .append(" ")
                        .append(man.getFirstName())
                        .append(" ")
                        .append(man.getSecondName())
                        .toString();
        this.num = contract.getNum();
        this.dateConclusion = DateHelpler.localDateToString(contract.getConclusionDate());
        this.insurancePremium = contract.getInsurancePremium();
        this.fromToDate = new StringBuilder(DateHelpler.localDateToString(contract.getPeriodFrom()))
                            .append("-")
                            .append(DateHelpler.localDateToString(contract.getPeriodTo()))
                            .toString();
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateConclusion() {
        return dateConclusion;
    }
    public void setDateConclusion(String dateConclusion) {
        this.dateConclusion = dateConclusion;
    }

    public String getFromToDate() {
        return fromToDate;
    }
    public void setFromToDate(String fromToDate) {
        this.fromToDate = fromToDate;
    }

    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }

    public Double getInsurancePremium() {
        return insurancePremium;
    }
    public void setInsurancePremium(Double insurancePremium) {
        this.insurancePremium = insurancePremium;
    }
}

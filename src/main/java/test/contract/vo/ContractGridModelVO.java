package test.contract.vo;

import test.contract.model.Contract;
import test.contract.model.Man;
import test.contract.utils.StringHelpler;

/**
 * Created by kutyakov on 18.01.2020.
 */
public class ContractGridModelVO {

    private Long id;
    private String fullName;
    private Long number;
    private String dateConclusion;
    private Float price;
    private String fromToDate;

    public ContractGridModelVO(){
    }

    public ContractGridModelVO(Contract contract) {

        //this.fullName = StringHelpler.formatFIO(man.getFirstName(),man.getLastName(),man.getSecondName());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getDateConclusion() {
        return dateConclusion;
    }

    public void setDateConclusion(String dateConclusion) {
        this.dateConclusion = dateConclusion;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getFromToDate() {
        return fromToDate;
    }

    public void setFromToDate(String fromToDate) {
        this.fromToDate = fromToDate;
    }
}

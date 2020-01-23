package test.contract.vo;

import test.contract.model.HandbookEntity;

/**
 * Created by Павел on 19.01.2020.
 */
public class HandbookVO extends ModelVO {
    private static final long serialVersionUID = -5947101932914714408L;

    private String name;
    private Float coefficient;

    public HandbookVO() {
    }

    public HandbookVO(HandbookEntity handbookEntity) {
        super(handbookEntity.getId());
        this.coefficient = handbookEntity.getCoefficient();
        this.name = handbookEntity.getName();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Float getCoefficient() {
        return coefficient;
    }
    public void setCoefficient(Float coefficient) {
        this.coefficient = coefficient;
    }
}

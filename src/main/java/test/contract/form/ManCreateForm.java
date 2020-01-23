package test.contract.form;

import test.contract.model.Man;
import test.contract.model.Pasport;
import test.contract.utils.DateHelpler;
import test.contract.utils.RepositoryHelper;

/**
 * Created by Павел on 23.01.2020.
 */
public class ManCreateForm {

    private Long manId;
    private String lastName;
    private String firstName;
    private String secondName;
    private String birthDay;
    private Long pasportId;
    private Long seriesDoc;
    private Long numDoc;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public Long getManId() {
        return manId;
    }

    public void setManId(Long manId) {
        this.manId = manId;
    }

    public Long getPasportId() {
        return pasportId;
    }

    public void setPasportId(Long pasportId) {
        this.pasportId = pasportId;
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
}

package test.contract.vo;

import test.contract.model.Man;
import test.contract.model.Pasport;
import test.contract.utils.DateHelpler;

/**
 * Created by Павел on 23.01.2020.
 */
public class ManVO extends ModelVO{

    private static final long serialVersionUID = 912099206957390310L;

    private String lastName;
    private String firstName;
    private String secondName;
    private String birthDay;
    private Long pasportId;
    private Long seriesDoc;
    private Long numDoc;

    public ManVO() {
    }

    public ManVO(Man man) {
        super(man.getId());
        this.lastName = man.getLastName();
        this.firstName = man.getFirstName();
        this.secondName = man.getSecondName();
        this.birthDay = DateHelpler.localDateToString(man.getBirthDay());
        Pasport pasport = man.getPasport();
        if(pasport!=null) {
            this.pasportId = pasport.getId();
            this.seriesDoc = pasport.getSeriesDoc();
            this.numDoc = pasport.getNumDoc();
        }
    }

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

    public Long getPasportId() {
        return pasportId;
    }

    public void setPasportId(Long pasportId) {
        this.pasportId = pasportId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManVO manVO = (ManVO) o;

        if (lastName != null ? !lastName.equals(manVO.lastName) : manVO.lastName != null) return false;
        if (firstName != null ? !firstName.equals(manVO.firstName) : manVO.firstName != null) return false;
        if (secondName != null ? !secondName.equals(manVO.secondName) : manVO.secondName != null) return false;
        if (birthDay != null ? !birthDay.equals(manVO.birthDay) : manVO.birthDay != null) return false;
        if (pasportId != null ? !pasportId.equals(manVO.pasportId) : manVO.pasportId != null) return false;
        if (seriesDoc != null ? !seriesDoc.equals(manVO.seriesDoc) : manVO.seriesDoc != null) return false;
        return numDoc != null ? numDoc.equals(manVO.numDoc) : manVO.numDoc == null;
    }

    @Override
    public int hashCode() {
        int result = lastName != null ? lastName.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (birthDay != null ? birthDay.hashCode() : 0);
        result = 31 * result + (pasportId != null ? pasportId.hashCode() : 0);
        result = 31 * result + (seriesDoc != null ? seriesDoc.hashCode() : 0);
        result = 31 * result + (numDoc != null ? numDoc.hashCode() : 0);
        return result;
    }
}

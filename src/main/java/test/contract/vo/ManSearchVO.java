package test.contract.vo;

import test.contract.model.Man;
import test.contract.model.Pasport;
import test.contract.utils.DateHelpler;

/**
 * Created by Павел on 23.01.2020.
 */
public class ManSearchVO{
    private static final long serialVersionUID = 3300719425810432988L;

    private Long manId;
    private String fullName;
    private String birthDay;
    private String pasport;
    private Long pasportId;
    private Long seriesDoc;
    private Long numDoc;

    public ManSearchVO() {
    }

    public ManSearchVO(Man man) {
        this.manId = man.getId();
        this.fullName = new StringBuilder(man.getLastName()).append(" ")
                .append(man.getFirstName()).append(" ")
                .append(man.getSecondName()).toString();
        this.birthDay = DateHelpler.localDateToString(man.getBirthDay());
        Pasport pasport = man.getPasport();
        if(pasport!=null&&pasport.getNumDoc()!=null) {
            this.pasport = new StringBuilder().append(pasport.getSeriesDoc())
                    .append(" ").append(pasport.getNumDoc()).toString();
            this.pasportId = pasport.getId();
            this.numDoc = pasport.getNumDoc();
            this.seriesDoc = pasport.getSeriesDoc();
        }
    }

    public Long getManId() {
        return manId;
    }

    public void setManId(Long manId) {
        this.manId = manId;
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

    public String getPasport() {
        return pasport;
    }

    public void setPasport(String pasport) {
        this.pasport = pasport;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManSearchVO that = (ManSearchVO) o;

        if (manId != null ? !manId.equals(that.manId) : that.manId != null) return false;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        if (birthDay != null ? !birthDay.equals(that.birthDay) : that.birthDay != null) return false;
        if (pasport != null ? !pasport.equals(that.pasport) : that.pasport != null) return false;
        if (pasportId != null ? !pasportId.equals(that.pasportId) : that.pasportId != null) return false;
        if (seriesDoc != null ? !seriesDoc.equals(that.seriesDoc) : that.seriesDoc != null) return false;
        return numDoc != null ? numDoc.equals(that.numDoc) : that.numDoc == null;
    }

    @Override
    public int hashCode() {
        int result = manId != null ? manId.hashCode() : 0;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (birthDay != null ? birthDay.hashCode() : 0);
        result = 31 * result + (pasport != null ? pasport.hashCode() : 0);
        result = 31 * result + (pasportId != null ? pasportId.hashCode() : 0);
        result = 31 * result + (seriesDoc != null ? seriesDoc.hashCode() : 0);
        result = 31 * result + (numDoc != null ? numDoc.hashCode() : 0);
        return result;
    }
}

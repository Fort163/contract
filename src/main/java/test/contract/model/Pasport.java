package test.contract.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by Павел on 19.01.2020.
 */
@Entity(name = "pasport")
@Table(name = "PASPORT")
public class Pasport extends AuditEntity {

    private Long seriesDoc;
    private Long numDoc;
    private Man man;

    @Column(name = "SERIES_DOC",length = 4)
    public Long getSeriesDoc() {
        return seriesDoc;
    }
    public void setSeriesDoc(Long seriesDoc) {
        this.seriesDoc = seriesDoc;
    }

    @Column(name = "NUM_DOC",length = 6)
    public Long getNumDoc() {
        return numDoc;
    }
    public void setNumDoc(Long numDoc) {
        this.numDoc = numDoc;
    }

    @OneToOne(mappedBy = "pasport")
    public Man getMan() {
        return man;
    }
    public void setMan(Man man) {
        this.man = man;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Pasport pasport = (Pasport) o;

        if (seriesDoc != null ? !seriesDoc.equals(pasport.seriesDoc) : pasport.seriesDoc != null) return false;
        if (numDoc != null ? !numDoc.equals(pasport.numDoc) : pasport.numDoc != null) return false;
        return man != null ? man.equals(pasport.man) : pasport.man == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (seriesDoc != null ? seriesDoc.hashCode() : 0);
        result = 31 * result + (numDoc != null ? numDoc.hashCode() : 0);
        result = 31 * result + (man != null ? man.hashCode() : 0);
        return result;
    }
}

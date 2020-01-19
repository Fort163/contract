package test.contract.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by Павел on 19.01.2020.
 */
@Entity(name = "pasport")
@Table(name = "PASPORT")
public class Pasport extends AuditEntity {

    private Long series;
    private Long num;
    private Man man;

    @Column(name = "SERIES")
    @Size(min=4, max=4)
    public Long getSeries() {
        return series;
    }
    public void setSeries(Long series) {
        this.series = series;
    }

    @Column(name = "NUM")
    @Size(min=6, max=6)
    public Long getNum() {
        return num;
    }
    public void setNum(Long num) {
        this.num = num;
    }

    @ManyToOne
    @JoinColumn(name = "FK_MAN_ID", foreignKey = @ForeignKey(name = "PASPORT_2_MAN"))
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

        if (series != null ? !series.equals(pasport.series) : pasport.series != null) return false;
        if (num != null ? !num.equals(pasport.num) : pasport.num != null) return false;
        return man != null ? man.equals(pasport.man) : pasport.man == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (series != null ? series.hashCode() : 0);
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + (man != null ? man.hashCode() : 0);
        return result;
    }
}

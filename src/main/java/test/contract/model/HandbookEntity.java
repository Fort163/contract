package test.contract.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

/**
 * Created by Павел on 19.01.2020.
 */
@MappedSuperclass
public class HandbookEntity extends AuditEntity{
    private static final long serialVersionUID = -8479692791681593038L;

    private String name;
    private Float coefficient;
    private LocalDate deprecatedFrom;

    @Column(name = "NAME")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DEPRECATED_FROM", columnDefinition = "DATE")
    public LocalDate getDeprecatedFrom() {
        return deprecatedFrom;
    }
    public void setDeprecatedFrom(LocalDate deprecatedFrom) {
        this.deprecatedFrom = deprecatedFrom;
    }

    @Column(name = "COEFFICIENT")
    public Float getCoefficient() {
        return coefficient;
    }
    public void setCoefficient(Float coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        HandbookEntity that = (HandbookEntity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (coefficient != null ? !coefficient.equals(that.coefficient) : that.coefficient != null) return false;
        return deprecatedFrom != null ? deprecatedFrom.equals(that.deprecatedFrom) : that.deprecatedFrom == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (coefficient != null ? coefficient.hashCode() : 0);
        result = 31 * result + (deprecatedFrom != null ? deprecatedFrom.hashCode() : 0);
        return result;
    }
}

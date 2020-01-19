package test.contract.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity(name = "coefficientArea")
@Table(name = "COEFFCIENT_AREA")
public class CoefficientArea extends AuditEntity {
    private static final long serialVersionUID = 3950373055220987086L;

    private String name;
    private LocalDate deprecatedFrom;
    /*
    private Float minArea;
    private Float maxArea;
     */

    @Column(name = "NAME",unique = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CoefficientArea that = (CoefficientArea) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return deprecatedFrom != null ? deprecatedFrom.equals(that.deprecatedFrom) : that.deprecatedFrom == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (deprecatedFrom != null ? deprecatedFrom.hashCode() : 0);
        return result;
    }
}

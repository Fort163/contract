package test.contract.form;

import test.contract.model.HandbookEntity;
import test.contract.utils.RepositoryHelper;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 * Created by Павел on 21.01.2020.
 */
public class HandbookForm implements Serializable{

    private Long id;
    private String name;
    private Float coefficient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public<EntityType extends HandbookEntity> EntityType toEntity(Class<EntityType> entityTypeClass){
        try {
            Constructor<EntityType> ctor = entityTypeClass.getConstructor();
            EntityType entityType = RepositoryHelper.createEntity(ctor.newInstance());
            entityType.setCoefficient(this.coefficient);
            entityType.setName(this.name);
            entityType.setId(this.id);
            return entityType;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HandbookForm that = (HandbookForm) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return coefficient != null ? coefficient.equals(that.coefficient) : that.coefficient == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (coefficient != null ? coefficient.hashCode() : 0);
        return result;
    }
}

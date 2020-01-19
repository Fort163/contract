package test.contract.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
/**
 * Created by Павел on 19.01.2020.
 */
@MappedSuperclass
public class ModelEntity implements Serializable {
    private static final long serialVersionUID = -7885784469715651448L;
    protected Long id;

    public ModelEntity() {
    }

    public ModelEntity(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_GEN")
    @SequenceGenerator(name = "ID_GEN", sequenceName = "id_sequence", allocationSize = 1)
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(o != null && this.getClass() == o.getClass()) {
            ModelEntity that = (ModelEntity)o;
            return Objects.equals(this.id, that.id);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id});
    }
}

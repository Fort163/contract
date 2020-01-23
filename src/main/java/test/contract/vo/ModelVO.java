package test.contract.vo;

import java.io.Serializable;

/**
 * Created by Павел on 19.01.2020.
 */
public class ModelVO implements Serializable{
    private static final long serialVersionUID = 2800935280311264674L;

    private Long id;

    public ModelVO() {
    }

    public ModelVO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

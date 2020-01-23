package test.contract.service;

import test.contract.form.ManCreateForm;
import test.contract.form.ManSearchForm;
import test.contract.vo.ManSearchVO;
import test.contract.vo.ManVO;

import java.util.List;

/**
 * Created by Павел on 23.01.2020.
 */
public interface IManService {

    List<ManSearchVO> search(ManSearchForm manSearchForm);

    Boolean create(ManCreateForm manCreateForm);

    ManVO getMan(Long manId);
}

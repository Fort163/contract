package test.contract.service;

import test.contract.form.HandbookForm;
import test.contract.model.CoefficientArea;
import test.contract.model.CoefficientConstruction;
import test.contract.model.PropertyType;
import test.contract.vo.HandbookVO;

import java.util.List;

/**
 * Created by Павел on 20.01.2020.
 */
public interface IHandbookService {

    List<HandbookVO> getPropertyTypeList();

    List<HandbookVO> getCoefficientAreaList();

    List<HandbookVO> getCoefficientConstructionList();

    Boolean createPropertyType(HandbookForm handbookForm);

    Boolean createCoefficientArea(HandbookForm handbookForm);

    Boolean createCoefficientConstruction(HandbookForm handbookForm);

    Boolean archivePropertyType(Long id);

    Boolean archiveCoefficientArea(Long id);

    Boolean archiveCoefficientConstruction(Long id);

    PropertyType getPropertyType(Long id);

    CoefficientArea getCoefficientArea(Long id);

    CoefficientConstruction getCoefficientConstruction(Long id);

}

package test.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.contract.dao.CoefficientAreaRepository;
import test.contract.dao.CoefficientConstructionRepository;
import test.contract.dao.PropertyTypeRepository;
import test.contract.form.HandbookForm;
import test.contract.model.CoefficientArea;
import test.contract.model.CoefficientConstruction;
import test.contract.model.PropertyType;
import test.contract.utils.ResultHelper;
import test.contract.vo.HandbookVO;

import java.time.LocalDate;
import java.util.List;

@Service
public class HandbookService implements IHandbookService {

    @Autowired
    private CoefficientAreaRepository coefficientAreaRepository;

    @Autowired
    private CoefficientConstructionRepository coefficientConstructionRepository;

    @Autowired
    private PropertyTypeRepository propertyTypeRepository;

    @Override
    public List<HandbookVO> getPropertyTypeList() {
        return ResultHelper.obtainVO(propertyTypeRepository.findAllByDeprecatedFromIsNull());
    }

    @Override
    public List<HandbookVO> getCoefficientAreaList() {
        return ResultHelper.obtainVO(coefficientAreaRepository.findAllByDeprecatedFromIsNull());
    }

    @Override
    public List<HandbookVO> getCoefficientConstructionList() {
        return ResultHelper.obtainVO(coefficientConstructionRepository.findAllByDeprecatedFromIsNull());
    }

    @Override
    @Transactional
    public Boolean createPropertyType(HandbookForm handbookForm) {
        Long count = propertyTypeRepository.countAllByNameAndDeprecatedFromIsNull(handbookForm.getName());
        if(handbookForm.getId()!=null||count==0) {
            PropertyType propertyType = handbookForm.toEntity(PropertyType.class);
            propertyTypeRepository.save(propertyType);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Boolean createCoefficientArea(HandbookForm handbookForm) {
        Long count = coefficientAreaRepository.countAllByNameAndDeprecatedFromIsNull(handbookForm.getName());
        if(handbookForm.getId()!=null||count==0) {
            CoefficientArea coefficientArea = handbookForm.toEntity(CoefficientArea.class);
            coefficientAreaRepository.save(coefficientArea);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Boolean createCoefficientConstruction(HandbookForm handbookForm) {
        Long count = coefficientConstructionRepository.countAllByNameAndDeprecatedFromIsNull(handbookForm.getName());
        if(handbookForm.getId()!=null||count==0) {
            CoefficientConstruction coefficientConstruction = handbookForm.toEntity(CoefficientConstruction.class);
            coefficientConstructionRepository.save(coefficientConstruction);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Boolean archivePropertyType(Long id) {
        propertyTypeRepository.archivePropertyType(id, LocalDate.now());
        return true;
    }

    @Override
    public Boolean archiveCoefficientArea(Long id) {
        coefficientAreaRepository.archiveCoefficientArea(id, LocalDate.now());
        return true;
    }

    @Override
    public Boolean archiveCoefficientConstruction(Long id) {
        coefficientConstructionRepository.archiveCoefficientConstruction(id, LocalDate.now());
        return true;
    }

    @Override
    public PropertyType getPropertyType(Long id){
        return propertyTypeRepository.getOne(id);
    }

    @Override
    public CoefficientArea getCoefficientArea(Long id) {
        return coefficientAreaRepository.getOne(id);
    }

    @Override
    public CoefficientConstruction getCoefficientConstruction(Long id) {
        return coefficientConstructionRepository.getOne(id);
    }
}

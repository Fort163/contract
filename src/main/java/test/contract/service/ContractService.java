package test.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.contract.dao.AddressRepository;
import test.contract.dao.ContractRepository;
import test.contract.dao.ManRepository;
import test.contract.form.CalculationForm;
import test.contract.form.ContractCreateForm;
import test.contract.model.Address;
import test.contract.model.Contract;
import test.contract.model.Man;
import test.contract.utils.DateHelpler;
import test.contract.utils.RepositoryHelper;
import test.contract.utils.ResultHelper;
import test.contract.vo.ContractGridModelVO;
import test.contract.vo.ContractVO;
import test.contract.vo.ResultCalculationVO;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContractService implements IContractService{

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ManRepository manRepository;

    @Autowired
    private IHandbookService iHandbookService;

    @Override
    public List<ContractGridModelVO> getList() {
        return ResultHelper.obtainVO(contractRepository.findAll(), Contract.class,ContractGridModelVO.class);
    }

    @Override
    @Transactional
    public ResultCalculationVO calculation(CalculationForm calculationForm) {
        LocalDate from = DateHelpler.formatDateExt(calculationForm.getPeriodFrom());
        LocalDate to = DateHelpler.formatDateExt(calculationForm.getPeriodTo());
        Long days = ChronoUnit.DAYS.between(from, to);
        Float coefficientPT = iHandbookService.getPropertyType(calculationForm.getPropertyType()).getCoefficient();
        Float coefficientCA = iHandbookService.getCoefficientArea(calculationForm.getCoefficientArea()).getCoefficient();
        Float coefficientCC = iHandbookService.getCoefficientConstruction(calculationForm.getCoefficientConstruction()).getCoefficient();
        //Страховая премия = (Страховая сумма / кол-во дней) * Коэф.ТН * Коэф.ГП * Коэф.Пл
        Double insurancePremium =
            (calculationForm.getInsuranceAmount()/days)*coefficientPT*coefficientCC*coefficientCA;
        return new ResultCalculationVO(DateHelpler.localDateToString(LocalDate.now()),insurancePremium);
    }

    @Override
    @Transactional
    public Boolean createContract(ContractCreateForm contractCreateForm) {
        Contract contract = null;
        if(contractCreateForm.getContractId()!=null){
            contract = RepositoryHelper.updateEntity(contractRepository.getOne(contractCreateForm.getContractId()));
        }
        else {
            contract = RepositoryHelper.createEntity(new Contract());
        }
        contract.setNum(contractCreateForm.getNum());
        contract.setConclusionDate(DateHelpler.formatDateExt(contractCreateForm.getConclusionDate()));
        contract.setCalculationDate(DateHelpler.formatDateExt(contractCreateForm.getCalculationDate()));
        contract.setInsuranceAmount(contractCreateForm.getInsuranceAmount());
        contract.setInsurancePremium(contractCreateForm.getInsurancePremium());
        contract.setPeriodFrom(DateHelpler.formatDateExt(contractCreateForm.getPeriodFrom()));
        contract.setPeriodTo(DateHelpler.formatDateExt(contractCreateForm.getPeriodTo()));
        Man man = manRepository.getOne(contractCreateForm.getManId());
        contract.setMan(man);
        contract.setPropertyType(iHandbookService.getPropertyType(contractCreateForm.getPropertyType()));
        contract.setCoefficientArea(iHandbookService.getCoefficientArea(contractCreateForm.getCoefficientArea()));
        contract.setCoefficientConstruction(iHandbookService.getCoefficientConstruction(contractCreateForm.getCoefficientConstruction()));
        contract.setComment(contractCreateForm.getComment());
        Address address = null;
        if(contractCreateForm.getAddressId()!=null){
            address = RepositoryHelper.updateEntity(addressRepository.getOne(contractCreateForm.getAddressId()));
        }
        else {
            address = RepositoryHelper.createEntity(new Address());
        }
        address.setState(contractCreateForm.getState());
        address.setIndex(contractCreateForm.getIndex());
        address.setRegion(contractCreateForm.getRegion());
        address.setDistrict(contractCreateForm.getDistrict());
        address.setCity(contractCreateForm.getCity());
        address.setStreet(contractCreateForm.getStreet());
        address.setHouse(contractCreateForm.getHouse());
        address.setHousing(contractCreateForm.getHousing());
        address.setBuilding(contractCreateForm.getBuilding());
        address.setFlat(contractCreateForm.getFlat());
        address.setMan(man);
        addressRepository.save(address);
        contractRepository.save(contract);
        return true;
    }

    @Override
    @Transactional
    public ContractVO openContract(Long contractId) {
        return ResultHelper.obtainVO(contractRepository.getOne(contractId), Contract.class,ContractVO.class);
    }
}

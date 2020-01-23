package test.contract.service;

import test.contract.form.CalculationForm;
import test.contract.form.ContractCreateForm;
import test.contract.vo.ContractGridModelVO;
import test.contract.vo.ContractVO;
import test.contract.vo.ResultCalculationVO;

import java.util.List;

/**
 * Created by kutyakov on 18.01.2020.
 */
public interface IContractService {

    List<ContractGridModelVO> getList();

    ResultCalculationVO calculation(CalculationForm calculationForm);

    Boolean createContract(ContractCreateForm contractCreateForm);

    ContractVO openContract(Long contractId);
}

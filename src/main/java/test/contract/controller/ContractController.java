package test.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.contract.form.CalculationForm;
import test.contract.form.ContractCreateForm;
import test.contract.service.IContractService;
import test.contract.vo.ContractGridModelVO;
import test.contract.vo.ContractVO;
import test.contract.vo.ResultCalculationVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/page/contract")
public class ContractController {

    @Autowired
    private IContractService iContractService;
    @RequestMapping("/getContractList")
    public Map<String, List<ContractGridModelVO>> getContractList() {
        Map map = new HashMap();
        map.put("result",iContractService.getList());
        return map;
    }

    @RequestMapping(value = "/openContract")
    public ContractVO openContract(@RequestParam(value = "contractId")Long contractId) {
        return iContractService.openContract(contractId);
    }

    @RequestMapping("/calculation")
    public ResultCalculationVO calculation(CalculationForm calculationForm) {
        return iContractService.calculation(calculationForm);
    }

    @RequestMapping("/createContract")
    public Boolean createContract(ContractCreateForm contractCreateForm) {
        return iContractService.createContract(contractCreateForm);
    }

}

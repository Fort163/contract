package test.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.contract.service.IContractService;
import test.contract.vo.ContractGridModelVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/page/contract")
public class ContractController {

    @Autowired
    private IContractService iContractService;

    @RequestMapping("/getList")
    Map<String, List<ContractGridModelVO>> searchSuppliersBySupplierName() {//@RequestParam("value") String name
        Map map = new HashMap();
        map.put("result",iContractService.getList());
        return map;
    }

}

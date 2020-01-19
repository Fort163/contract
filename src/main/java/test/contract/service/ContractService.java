package test.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.contract.dao.ContractRepository;
import test.contract.model.Man;
import test.contract.vo.ContractGridModelVO;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractService implements IContractService{

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public List<ContractGridModelVO> getList() {
        /*List<Long> ids = new ArrayList<>();
        ids.add(1120666L);
        ids.add(1120668L);
        ids.add(1120684L);
        ids.add(1120696L);
        List<Man> mans = contractRepository.findAllByIdIn(ids);
        List<ContractGridModelVO> contractGridModelVO = new ArrayList<>();
        for(Man man:mans){
            contractGridModelVO.add(new ContractGridModelVO(man));
        }
        return contractGridModelVO;*/
        return null;
    }

}

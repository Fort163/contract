package test.contract.service;

import test.contract.vo.ContractGridModelVO;

import java.util.List;

/**
 * Created by kutyakov on 18.01.2020.
 */
public interface IContractService {

    List<ContractGridModelVO> getList();

}

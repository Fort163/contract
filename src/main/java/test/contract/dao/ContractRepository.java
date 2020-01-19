package test.contract.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.contract.model.Contract;
import test.contract.model.Man;

import java.util.List;

/**
 * Created by kutyakov on 18.01.2020.
 */
@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {



}

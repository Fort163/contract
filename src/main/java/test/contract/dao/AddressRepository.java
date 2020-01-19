package test.contract.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.contract.model.Address;
import test.contract.model.Contract;

/**
 * Created by Павел on 19.01.2020.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}

package test.contract.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.contract.model.Man;

import java.util.List;

/**
 * Created by Павел on 19.01.2020.
 */
@Repository
public interface ManRepository extends JpaRepository<Man, Long> {

    List<Man> findAllByLastNameContainingAndFirstNameContainingAndSecondNameContaining(String lastName,String firstName,String secondName);

}

package test.contract.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.contract.model.CoefficientConstruction;

/**
 * Created by Павел on 19.01.2020.
 */
@Repository
public interface CoefficientConstructionRepository extends JpaRepository<CoefficientConstruction, Long> {
}

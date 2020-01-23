package test.contract.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import test.contract.model.CoefficientArea;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Павел on 19.01.2020.
 */
@Repository
public interface CoefficientAreaRepository extends JpaRepository<CoefficientArea, Long> {

    List<CoefficientArea> findAllByDeprecatedFromIsNull();

    Long countAllByNameAndDeprecatedFromIsNull(String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE coefficientArea as coefficientArea set coefficientArea.deprecatedFrom=:deprecatedFrom where coefficientArea.id=:id ")
    void archiveCoefficientArea(@Param(value = "id") Long id, @Param(value = "deprecatedFrom") LocalDate now);
}

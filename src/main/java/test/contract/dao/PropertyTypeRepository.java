package test.contract.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import test.contract.model.PropertyType;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Павел on 19.01.2020.
 */
@Repository
public interface PropertyTypeRepository extends JpaRepository<PropertyType, Long> {

    List<PropertyType> findAllByDeprecatedFromIsNull();

    Long countAllByNameAndDeprecatedFromIsNull(String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE propertyType as propertyType set propertyType.deprecatedFrom=:deprecatedFrom where propertyType.id=:id ")
    void archivePropertyType(@Param(value = "id") Long id, @Param(value = "deprecatedFrom") LocalDate deprecatedFrom);

}

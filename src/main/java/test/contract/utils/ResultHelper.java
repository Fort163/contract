package test.contract.utils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ResultHelper {
/*

    */
/**
     * Just create a wrapper map for a client response
     * @param result Result object
     * @param <T> Type of result object
     * @return Map<String,T> where putted result by a "result" key
     *//*

    public static <T> Map<String, T> createResultMap(T result) {
        Map<String, T> map = new HashMap<>();
        map.put("result", result);
        return map;
    }

    */
/**
     * Just create a wrapper map for a Enum. Converts it to EnumVO
     * @param enumeration An enum values
     * @param enumClass A class of the enum
     * @param <T> Type of result object
     * @return Map<String,T> where putted result by a "result" key
     *//*

    public static <T extends Enum<T>> Map<String, List<EnumVO>> createResultMap(T[] enumeration, Class<T> enumClass) {
        Map<String, List<EnumVO>> map = new HashMap<>();
        map.put("result", ResultHelper.obtainEnumVO(Arrays.asList(enumeration), enumClass));
        return map;
    }

    */
/**
     * Just create a wrapper map for a client response
     * @param result Result object
     * @return Map<String,T> where putted result by a "result" key
     *//*

    public static <T extends ModelVO> Map<String, Object> createResultPaginationMap(List<T> result, Long totalCount) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        map.put("total", totalCount);
        return map;
    }

    public static <T extends ModelVO> Map<String, Object> createResultPaginationMap(PageableWrapper<T> result) {
        return createResultPaginationMap(result.getEntityList(), result.getTotalRowCount());
    }

    public static <Type1, Type2, ReturnType> BiFunction<Type1, Type2, ReturnType> nullBiFunction() {
        return ((type1, type2) -> null);
    }

    public static <RootType> BiFunction<Root<RootType>, CriteriaBuilder, Selection<Long>> selectorCount() {
        return ((rootTypeRoot, criteriaBuilder) -> criteriaBuilder.count(rootTypeRoot));
    }

    */
/**
     * Creates value object from entity object
     * @param obj Object in database
     * @param entityTypeClass Database entity class
     * @param valueTypeClass VO class for this entity class(VO class must has constructor via entity class object)
     * @return New VO object constructed by database entity object
     *//*

    public static <EntityType, ValueType> ValueType obtainVO(EntityType obj, Class<EntityType> entityTypeClass, Class<ValueType> valueTypeClass) {
        try {
            Constructor<ValueType> ctor = valueTypeClass.getConstructor(entityTypeClass);
            return ctor.newInstance(obj);
        } catch (Exception ex) {
            return null;
        }
    }


    */
/**
     * Creates list of VO objects based on a database entity object
     * @param obj Iterable of database entity objects
     * @param entityTypeClass Database entity class
     * @param valueTypeClass VO class
     * @return List of VO objects
     *//*

    public static <EntityType, ValueType> List<ValueType> obtainVO(Iterable<EntityType> obj, Class<EntityType> entityTypeClass, Class<ValueType> valueTypeClass) {
        return StreamSupport.stream(obj.spliterator(), true).map(x -> obtainVO(x, entityTypeClass, valueTypeClass)).collect(Collectors.toList());
    }

    */
/**
     * Creates value object from entity object
     * @param obj Object in database
     * @return New VO object constructed by database entity object
     *//*

    public static <EnumType extends Enum<EnumType>> EnumVO obtainEnumVO(Enum<EnumType> obj, Class<EnumType> enumTypeClass) {
        try {
            return new EnumVO(obj, enumTypeClass);
        } catch (Exception ex) {
            return null;
        }
    }

    */
/**
     * Creates list of VO objects based on a database entity object
     * @param obj Iterable of database entity objects
     * @param enumTypeClass Database entity class
     * @return List of VO objects
     *//*

    public static <EnumType extends Enum<EnumType>> List<EnumVO> obtainEnumVO(Iterable<EnumType> obj, Class<EnumType> enumTypeClass) {
        return StreamSupport.stream(obj.spliterator(), true).map(x -> obtainEnumVO(x, enumTypeClass)).collect(Collectors.toList());
    }

    */
/**
     * special case with HandbookEntity
     * @param obj Iterable HandbookEntity object
     * @return List<HandbookVO>
     *//*

    public static List<HandbookVO> obtainVO(Iterable<? extends HandbookEntity> obj) {
        Iterable<HandbookEntity> it = StreamSupport.stream(obj.spliterator(), true)
                .map(x -> (HandbookEntity)x).collect(Collectors.toList());
        return obtainVO(it, HandbookEntity.class, HandbookVO.class);
    }

    */
/**
     * special case with HandbookEntity
     * @param obj Iterable HandbookEntity object
     * @return List<HandbookVO>
     *//*

    public static HandbookVO obtainVO(HandbookEntity obj) {
        return obtainVO(obj, HandbookEntity.class, HandbookVO.class);
    }
*/

}

package test.contract.utils;

import test.contract.model.HandbookEntity;
import test.contract.vo.HandbookVO;

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

/**
 * Created by Павел on 19.01.2020.
 */
public class ResultHelper {

    public static <EntityType, ValueType> List<ValueType> obtainVO(Iterable<EntityType> obj, Class<EntityType> entityTypeClass, Class<ValueType> valueTypeClass) {
        return StreamSupport.stream(obj.spliterator(), true).map(x -> obtainVO(x, entityTypeClass, valueTypeClass)).collect(Collectors.toList());
    }

    public static <EntityType, ValueType> ValueType obtainVO(EntityType obj, Class<EntityType> entityTypeClass, Class<ValueType> valueTypeClass) {
        try {
            Constructor<ValueType> ctor = valueTypeClass.getConstructor(entityTypeClass);
            return ctor.newInstance(obj);
        } catch (Exception ex) {
            return null;
        }
    }

    public static List<HandbookVO> obtainVO(Iterable<? extends HandbookEntity> obj) {
        Iterable<HandbookEntity> it = StreamSupport.stream(obj.spliterator(), true)
                .map(x -> (HandbookEntity)x).collect(Collectors.toList());
        return obtainVO(it, HandbookEntity.class, HandbookVO.class);
    }

    public static HandbookVO obtainVO(HandbookEntity obj) {
        return obtainVO(obj, HandbookEntity.class, HandbookVO.class);
    }

}

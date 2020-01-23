package test.contract.utils;

import test.contract.model.AuditEntity;

/**
 * Created by Павел on 21.01.2020.
 */
public class RepositoryHelper {

    public static <EntityType extends AuditEntity> EntityType createEntity(EntityType entity){
        entity.setAuditParamsForCreation(null);
        return entity;
    }

    public static <EntityType extends AuditEntity> EntityType updateEntity(EntityType entity){
        entity.setAuditParamsForUpdate(null);
        return entity;
    }

}

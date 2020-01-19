package test.contract.utils;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityExistsException;
import java.io.Serializable;
import java.time.LocalDateTime;

public class RepositoryHelper {
/*

    */
/**
     * Creates entity, but checks on existence the object like that in database
     * @param entity Entity to create
     * @param repository Repository to call save where
     * @return New object ID in database
     *//*

    public static <
            EntityType extends AuditEntity,
            IDType extends Serializable,
            RepoType extends IExistableRepository<EntityType, IDType>
            >
    IDType createUniqueEntity(EntityType entity, RepoType repository) {
        return createUniqueEntity(entity, repository, false);
    }

    */
/**
     * Creates entity, but checks on existence the object like that in database
     * @param entity Entity to create
     * @param repository Repository to call save where
     * @return New object ID in database
     *//*

    public static <
            EntityType extends AuditEntity,
            IDType extends Serializable,
            RepoType extends IExistableRepository<EntityType, IDType>
            >
    IDType createUniqueEntity(EntityType entity, RepoType repository, boolean flush, boolean safe) {
        if(entity != null) {
            if(repository.exists(entity)) {
                if(safe) {
                    return null;
                } else {
                    throw new EntityExistsException("Такой объект сущности " + entity.getClass().getSimpleName() + " уже существует");
                }
            }
            return createEntity(entity, repository, flush);
        } else {
            return null;
        }
    }

    public static <
            EntityType extends AuditEntity,
            IDType extends Serializable,
            RepoType extends IExistableRepository<EntityType, IDType>
            >
    IDType createUniqueEntity(EntityType entity, RepoType repository, boolean flush) {
        return createUniqueEntity(entity, repository, flush, false);
    }

    */
/**
     * Save entity with audit fields
     * @param entity Object itself
     * @param repository JpaRepository instance
     * @param <EntityType> Entity type that extends from AuditEntity
     * @param <IDType> ID type. Default Long
     * @return ID
     *//*

    public static <EntityType extends AuditEntity, IDType extends Serializable> IDType createEntity(EntityType entity, JpaRepository<EntityType, IDType> repository) {
        return createEntity(entity, repository, false);
    }

    */
/**
     * Save entity with audit fields
     * @param entity Object itself
     * @param repository JpaRepository instance
     * @param <EntityType> Entity type that extends from AuditEntity
     * @param <IDType> ID type. Default Long
     * @return ID
     *//*

    public static <EntityType extends AuditEntity, IDType extends Serializable> IDType createEntity(EntityType entity, JpaRepository<EntityType, IDType> repository, boolean flush) {
        if(entity != null) {
            return (IDType) (flush ? repository.saveAndFlush(createEntity(entity)).getId() : repository.save(createEntity(entity)).getId());
        } else {
            return null;
        }
    }

    public static <EntityType extends AuditEntity> EntityType createEntity(EntityType entity) {
        if(entity != null) {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            String userName = securityContext.getAuthentication().getName();
            LocalDateTime currentTime = LocalDateTime.now();
            entity.setCreatedWhen(currentTime);
            entity.setUpdatedWhen(currentTime);
            entity.setAuditParamsForCreation(userName);
            return entity;
        } else {
            return null;
        }
    }

    */
/**
     * Update entity with audit fields
     * @param entity Object itself
     * @param repository JpaRepository instance
     * @param <EntityType> Entity type that extends from AuditEntity
     * @param <IDType> ID type. Default Long
     * @return ID
     *//*

    public static <EntityType extends AuditEntity, IDType extends Serializable> IDType updateEntity(EntityType entity, JpaRepository<EntityType, IDType> repository) {
        if(entity != null) {
            return (IDType) repository.save(updateEntity(entity)).getId();
        } else {
            return null;
        }
    }

    public static <EntityType extends AuditEntity, IDType extends Serializable> IDType saveEntity(EntityType entity, JpaRepository<EntityType, IDType> repository) {
        return entity.getId() != null ? updateEntity(entity, repository) : createEntity(entity, repository);
    }

    public static <EntityType extends AuditEntity> EntityType updateEntity(EntityType entity) {
        if(entity != null) {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            String userName = securityContext.getAuthentication().getName();
            LocalDateTime currentTime = LocalDateTime.now();
            entity.setUpdatedWhen(currentTime);
            entity.setAuditParamsForUpdate(userName);
            return entity;
        } else {
            return null;
        }
    }
*/

}

package com.dangerfield.hiltplayground.db

import com.dangerfield.hiltplayground.models.Blog
import com.dangerfield.hiltplayground.data.EntityMapper
import javax.inject.Inject

/*
Clean architecture, what you want to store isnt always formatted the same as what you want to show (Domain Model)
 */
class CacheMapper @Inject constructor() : EntityMapper<BlogCacheEntity, Blog> {
    override fun mapFromEntity(entity: BlogCacheEntity): Blog {
        return Blog (
            id = entity.id,
            title = entity.title,
            body = entity.body,
            category =  entity.category,
            image = entity.image
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogCacheEntity {
        return BlogCacheEntity (
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            category =  domainModel.category,
            image = domainModel.image
        )
    }

    fun mapFromEntityList(entities: List<BlogCacheEntity>) : List<Blog> {
        return entities.map { mapFromEntity(it) }
    }
}
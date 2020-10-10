package com.dangerfield.hiltplayground.data.blog

import com.dangerfield.hiltplayground.data.EntityMapper
import com.dangerfield.hiltplayground.models.BlogData
import javax.inject.Inject

/*
 Clean Architecture: There's data you get from the network, and then theres data
 you use to create a user experience. They dont always look the same.
 */
class BlogNetworkMapper @Inject constructor() :
    EntityMapper<BlogNetworkEntity, BlogData> {
    override fun mapFromEntity(entity: BlogNetworkEntity): BlogData {
        return BlogData(
            body = entity.body,
            title = entity.title,
            image = entity.image,
            id = entity.id,
            category = entity.category
        )
    }

    override fun mapToEntity(domainModel: BlogData): BlogNetworkEntity {
        return BlogNetworkEntity(
            body = domainModel.body,
            title = domainModel.title,
            image = domainModel.image,
            id = domainModel.id,
            category = domainModel.category
        )
    }

    fun mapFromEntityList(list: List<BlogNetworkEntity>) : List<BlogData> {
        return list.map { mapFromEntity(it) }
    }
}
package com.dangerfield.hiltplayground.data

import com.dangerfield.hiltplayground.models.Blog
import javax.inject.Inject

/*
 Clean Architecture: There's data you get from the network, and then theres data
 you use to create a user experience. They dont always look the same.
 */
class NetworkMapper @Inject constructor() : EntityMapper<BlogNetworkEntity, Blog> {
    override fun mapFromEntity(entity: BlogNetworkEntity): Blog {
        return Blog(
            body = entity.body,
            title = entity.title,
            image = entity.image,
            id = entity.id,
            category = entity.category
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogNetworkEntity {
        return BlogNetworkEntity(
            body = domainModel.body,
            title = domainModel.title,
            image = domainModel.image,
            id = domainModel.id,
            category = domainModel.category
        )
    }

    fun mapFromEntityList(list: List<BlogNetworkEntity>) : List<Blog> {
        return list.map { mapFromEntity(it) }
    }
}
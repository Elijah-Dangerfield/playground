package com.dangerfield.hiltplayground.db

import com.dangerfield.hiltplayground.data.EntityMapper
import com.dangerfield.hiltplayground.models.UserData
import javax.inject.Inject

class UserCacheMapper @Inject constructor() :
    EntityMapper<UserCacheEntity, UserData> {

    fun mapFromEntityList(list: List<UserCacheEntity>) : List<UserData> {
        return list.map { mapFromEntity(it) }
    }

    override fun mapFromEntity(entity: UserCacheEntity): UserData {
        return UserData(name = entity.name, id = entity.id)
    }

    override fun mapToEntity(domainModel: UserData): UserCacheEntity {
        return UserCacheEntity(id = domainModel.id, name = domainModel.name)
    }
}
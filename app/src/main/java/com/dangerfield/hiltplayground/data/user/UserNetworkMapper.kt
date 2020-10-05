package com.dangerfield.hiltplayground.data.user

import com.dangerfield.hiltplayground.data.EntityMapper
import com.dangerfield.hiltplayground.models.UserData
import javax.inject.Inject

class UserNetworkMapper @Inject constructor() :
    EntityMapper<UserNetworkEntity, UserData> {

    fun mapFromEntityList(list: List<UserNetworkEntity>) : List<UserData> {
        return list.map { mapFromEntity(it) }
    }

    override fun mapFromEntity(entity: UserNetworkEntity): UserData {
        return UserData(name = entity.name, id = entity.id)
    }

    override fun mapToEntity(domainModel: UserData): UserNetworkEntity {
        return UserNetworkEntity() // we will never need to map to a network entity, only from
        //TODO is there a cleaner way to do this then?
    }
}
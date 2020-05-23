package data.model

import domain.model.Identifiable

interface DataEntity<Key, DomainObject> : Identifiable<Key> {
    fun toDomain(): DomainObject
}
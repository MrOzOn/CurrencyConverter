package com.mrozon.currencyconverter.utils

abstract class BaseMapper<EntityDb, Model> {

    abstract fun map(entity: EntityDb) : Model

    fun map(entityList: List<EntityDb>) : List<Model> {
        val modelList = arrayListOf<Model>()
        entityList.forEach { entityDb ->
            map(entityDb).let { model ->
                modelList.add(model)
            }
        }
        return modelList
    }
}

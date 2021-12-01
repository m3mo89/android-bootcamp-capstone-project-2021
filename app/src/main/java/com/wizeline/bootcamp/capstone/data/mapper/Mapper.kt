package com.wizeline.bootcamp.capstone.data.mapper

interface Mapper<I, O> {
    fun map(from: I): O

    fun mapList(from: List<I>): List<O> {
        return from.map { map(it) }
    }
}

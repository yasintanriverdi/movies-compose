package com.yasintanriverdi.moviescompose.data.mapper

interface Mapper<in F, out T> {
    suspend fun map(from: F): T
}

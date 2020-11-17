package com.yasintanriverdi.moviescompose.model

import com.yasintanriverdi.moviescompose.data.mapper.Mapper
import com.yasintanriverdi.moviescompose.network.model.NetworkResponse

sealed class RepositoryResult<out T : Any> {
    /**
     * Success
     */
    data class Success<T : Any>(val result: T?) : RepositoryResult<T>()

    /**
     * Failure
     */
    data class Fail<U : Any>(val errorResult: U) : RepositoryResult<Nothing>()

    /**
     * Network error
     */
    data class Exception(val error: Throwable?) : RepositoryResult<Nothing>()
}

fun <T : Any> NetworkResponse<T, Any>.toRepositoryResult(): RepositoryResult<T> {
    return when (this) {
        is NetworkResponse.Success -> RepositoryResult.Success(this.body)
        is NetworkResponse.ApiError -> RepositoryResult.Fail(this)
        is NetworkResponse.NetworkError -> RepositoryResult.Exception(this.error)
        is NetworkResponse.UnknownError -> RepositoryResult.Exception(this.error)
    }
}

fun <T : Any, U : Any> NetworkResponse<T, Any>.toRepositoryResult(mapper: Mapper<T, U>): RepositoryResult<U> {
    return when (this) {
        is NetworkResponse.Success -> RepositoryResult.Success(
            if (this.body != null) mapper.map(
                this.body
            ) else null
        )
        is NetworkResponse.ApiError -> RepositoryResult.Fail(this)
        is NetworkResponse.NetworkError -> RepositoryResult.Exception(this.error)
        is NetworkResponse.UnknownError -> RepositoryResult.Exception(this.error)
    }
}

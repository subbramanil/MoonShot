package com.haroldadmin.moonshot.core

/**
 * A sealed class to represent UI states associated with a resource.
 */
sealed class Resource<out T> {

    /**
     * A data class to represent the scenario where the resource is available without any errors
     */
    data class Success <out T>(val data: T, val isCached: Boolean = false) : Resource<T>()

    /**
     * A data class to represent the scenario where a resource may or may not be available due to an error
     */
    data class Error <out T, out E> (val data: T?, val error: E?) : Resource<T>()

    /**
     * A class to represent the loading state of an object
     */
    object Loading : Resource<Nothing>()

    object Uninitialized : Resource<Nothing>()
}

operator fun <T> Resource<T>.invoke(): T? {
    return if (this is Resource.Success)
        this.data
    else
        null
}
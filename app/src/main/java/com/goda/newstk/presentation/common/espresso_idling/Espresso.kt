package com.goda.newstk.presentation.common.espresso_idling

object Espressoo {

    private const val RESOURCE = "VIEW_MODEL"

    private val mCountingIdlingResource = BackGroundIdlingResource(RESOURCE)

    fun increment() = mCountingIdlingResource.increment()

    fun decrement() = mCountingIdlingResource.decrement()

    fun getIdlingResource() = mCountingIdlingResource
}
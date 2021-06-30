package com.goda.newstk.domain.common

import io.reactivex.Scheduler


interface ExecutionThread {
    val scheduler: Scheduler
}
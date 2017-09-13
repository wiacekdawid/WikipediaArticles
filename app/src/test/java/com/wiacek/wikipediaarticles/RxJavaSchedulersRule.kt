package com.wiacek.wikipediaarticles

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Created by wiacek.dawid@gmail.com
 */

class RxJavaSchedulersRule : TestRule {

    private val immediate = object : Scheduler() {
        override fun createWorker(): Worker {
            return ExecutorScheduler.ExecutorWorker({
                it.run()
            })
        }
    }

    override fun apply(base: Statement, d: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.setIoSchedulerHandler(
                        { Schedulers.trampoline() })
                RxJavaPlugins.setComputationSchedulerHandler(
                        { Schedulers.trampoline() })
                RxJavaPlugins.setNewThreadSchedulerHandler(
                        { Schedulers.trampoline() })
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                        { immediate })

                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}
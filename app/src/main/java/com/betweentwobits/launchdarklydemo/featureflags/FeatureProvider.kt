package com.betweentwobits.launchdarklydemo.featureflags

interface FeatureProvider {
    fun isFeatureEnabled(feature: Feature): Boolean
    fun hasFeature(feature: Feature): Boolean
    fun tearDown()
}

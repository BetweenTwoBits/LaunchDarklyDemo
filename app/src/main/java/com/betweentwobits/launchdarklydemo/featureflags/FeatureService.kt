package com.betweentwobits.launchdarklydemo.featureflags

import android.app.Application

class FeatureService(application: Application) : FeatureProvider {
    private val provider = LaunchDarklyFeatureProvider(application)

    override fun isFeatureEnabled(feature: Feature): Boolean {
        return provider.isFeatureEnabled(feature)
    }

    override fun hasFeature(feature: Feature): Boolean {
        return provider.hasFeature(feature)
    }

    override fun tearDown() {
        provider.tearDown()
    }
}

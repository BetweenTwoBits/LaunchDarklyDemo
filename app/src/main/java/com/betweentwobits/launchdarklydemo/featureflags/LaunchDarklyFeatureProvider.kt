package com.betweentwobits.launchdarklydemo.featureflags

import android.app.Application
import android.util.Log
import com.betweentwobits.launchdarklydemo.BuildConfig
import com.launchdarkly.android.LDClient
import com.launchdarkly.android.LDConfig
import com.launchdarkly.android.LDUser

class LaunchDarklyFeatureProvider(application: Application) : FeatureProvider {
    private val client: LDClient

    init {
        val apiKey = if (BuildConfig.DEBUG) TEST_MOBILE_KEY else PROD_MOBILE_KEY

        val ldConfig = LDConfig.Builder()
            .setMobileKey(apiKey)
            .build()

        val user = LDUser.Builder("user_key")
            .email("fake@example.com")
            .build()

        client = LDClient.init(application, ldConfig, user, 5)
    }

    override fun isFeatureEnabled(feature: Feature): Boolean {
        if (!hasFeature(feature)) {
            Log.d(TAG, "Feature \"${feature.title}\" is not setup in the Launch Darkly console, using default value.")
        }
        return client.boolVariation(feature.key, feature.defaultValue)
    }

    override fun hasFeature(feature: Feature): Boolean = client.allFlags().containsKey(feature.key)

    override fun tearDown() = client.flush()

    private companion object {
        private val TAG = LaunchDarklyFeatureProvider::class.java.simpleName
        // TO USE: you need a real key to make this work.
        private const val TEST_MOBILE_KEY = "test"
        private const val PROD_MOBILE_KEY = "prod"
    }
}

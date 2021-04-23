package com.betweentwobits.launchdarklydemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.betweentwobits.launchdarklydemo.featureflags.Feature.*
import com.betweentwobits.launchdarklydemo.featureflags.FeatureService

class MainActivity : AppCompatActivity() {
    private lateinit var flagService: FeatureService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flagService = FeatureService(application)

        logFeature(NewFeatureOne.title, flagService.isFeatureEnabled(NewFeatureOne))
        logFeature(NewFeatureTwo.title, flagService.isFeatureEnabled(NewFeatureTwo))
        logFeature(NewFeatureThree.title, flagService.isFeatureEnabled(NewFeatureThree))
    }

    override fun onDestroy() {
        super.onDestroy()
        flagService.tearDown()
    }

    private fun logFeature(featureName: String, enabled: Boolean) {
        if (enabled) {
            Log.d(TAG, "Feature \"$featureName\" Enabled")
        } else {
            Log.d(TAG, "Feature \"$featureName\" Disabled")
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}

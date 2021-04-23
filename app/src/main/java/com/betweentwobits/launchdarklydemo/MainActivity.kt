package com.betweentwobits.launchdarklydemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.betweentwobits.launchdarklydemo.featureflags.Feature.NewFeatureOne
import com.betweentwobits.launchdarklydemo.featureflags.Feature.NewFeatureTwo
import com.betweentwobits.launchdarklydemo.featureflags.FeatureService

class MainActivity : AppCompatActivity() {
    private lateinit var flagService: FeatureService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flagService = FeatureService(application)

        if (flagService.isFeatureEnabled(NewFeatureOne)) {
            Log.d(TAG, "${NewFeatureOne.title} Enabled.")
        } else {
            Log.d(TAG, "${NewFeatureOne.title} Disabled")
        }

        if (flagService.isFeatureEnabled(NewFeatureTwo)) {
            Log.d(TAG, "${NewFeatureTwo.title} Enabled")
        } else {
            Log.d(TAG, "${NewFeatureTwo.title} Disabled")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        flagService.tearDown()
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}

package com.betweentwobits.launchdarklydemo.featureflags

sealed class Feature(
    val key: String,
    val title: String,
    val defaultValue: Boolean
) {
    object NewFeatureOne : Feature("test-flag-1", "Test Feature 1", false)
    object NewFeatureTwo : Feature("test-flag-2", "Test Feature 2", false)
    object NewFeatureThree : Feature("test-flag-3", "Test Feature 3", false)
}

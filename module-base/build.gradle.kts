plugins {
    id("modulePlugin")
}

android {
    namespace = "com.xiaojinzi.tally.module.base"
}

dependencies {

    api(project(":lib-res"))
    api(libs.xiaojinzi.android.support.init)
    api(libs.xiaojinzi.android.support.activitystack)
    api(libs.xiaojinzi.android.support.ktx.retrofit)
    api(libs.xiaojinzi.android.module.storage)
    api(libs.xiaojinzi.android.module.ffmpeg)
    api(libs.xiaojinzi.android.module.ali.oss)
    api(libs.xiaojinzi.android.module.ali.pay)
    api(libs.xiaojinzi.android.module.wx.sdk)
    api(libs.xiaojinzi.android.reactive.core)
    api(libs.xiaojinzi.android.reactive.template)
    api(libs.xiaojinzi.android.reactive.template.compose)

    api(libs.kcomponent.core)

    api(libs.androidx.work.manager.ktx)

    api(libs.lottie.compose)

    api(libs.compose.runtime)
    api(libs.compose.runtime.android)
    api(libs.compose.ui.android)
    api(libs.compose.foundation.android)
    api(libs.compose.foundation.layout.android)
    api(libs.compose.material.android)
    api(libs.compose.material3)

    api(libs.glance.appwidget)
    api(libs.glance.material3)

    // api(libs.koin.android)
    // implementation(libs.hilt)

}
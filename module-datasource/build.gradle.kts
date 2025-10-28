plugins {
    id("moduleBusinessPlugin")
}

android {
    namespace = "com.xiaojinzi.tally.module.network"
}

dependencies {
    api(libs.okhttp3)
    api(libs.retrofit)
    api(libs.retrofit.converter.gson)
}
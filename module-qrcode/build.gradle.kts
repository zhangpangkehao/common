plugins {
    id("moduleBusinessPlugin")
}

android {
    namespace = "com.xiaojinzi.tally.module.qrcode"
}

dependencies {
    implementation("com.google.zxing:core:3.5.3")
    implementation("io.github.g00fy2.quickie:quickie-bundled:1.9.0")
}
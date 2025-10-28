plugins {
    id("moduleBusinessPlugin")
}

android {
    namespace = "com.xiaojinzi.tally.module.imagepicker"
}

dependencies {
    // PictureSelector basic (Necessary)
    implementation("io.github.lucksiege:pictureselector:v3.11.2")
    // image compress library (Not necessary)
    implementation("io.github.lucksiege:compress:v3.11.2")
    // uCrop library (Not necessary)
    implementation("io.github.lucksiege:ucrop:v3.11.2")
    // simple camerax library (Not necessary)
    implementation("io.github.lucksiege:camerax:v3.11.2")
}
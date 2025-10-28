# 原地址
~~~
https://github.com/xiaojinzi123/yike-app
~~~

# 编译Release版本：
~~~
./gradlew assemblePrdRelease
~~~

# 安装到手机（会覆盖原应用，保留数据）
APP地址在：yike\opensource\app\build\outputs\apk\prd\release
~~~
adb install -r ../opensource/app/build/outputs/apk/prd/release/opensource-prd-release.apk
~~~

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

以下修改均来自AI：claude-sonnet-4-5
1.取消混淆，使其可以使用Android studio导出数据

2.修复第二次打开闪退的问题

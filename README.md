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


修改内容
~~~
问题分析

  问题根因：应用进程保留时，数据库初始化状态丢失

  完整流程：

  ✅ 第一次启动（正常）

  LoadingAct → latestUserId == null → 进入登录页面 → 用户登录
  → afterLogin() 被调用 → 数据库初始化 ✓
  → 进入主界面 → MainRouterInterceptor 访问 tallyDataSourceSpi → 成功

  ❌ 第二次启动（出错）

  Android 保留进程但清除部分状态（TallyDb._database = null）
  → LoadingAct → latestUserId != null → 跳过登录流程 ⚠️
  → 只更新 token，不初始化数据库
  → 尝试进入主界面 → MainRouterInterceptor (L18-20)
  → 访问 AppServices.tallyDataSourceSpi
  → 实例化 TallyDataSourceSpiImpl
  → 初始化属性 allBookStateOb 访问 TallyDb.database
  → ❌ 抛出异常：数据库未初始化ø

  关键代码位置：
  1. LoadingUseCase.kt:64-91 - 跳过了数据库初始化
  2. MainRouterInterceptor.kt:18-20 - 过早访问数据源
  3. TallyDataSourceSpiImpl.kt:74-90 - 类属性初始化时访问数据库

  💡 解决方案

  在 LoadingUseCase 中添加数据库初始化检查：

module-user\src\main\java\com\xiaojinzi\tally\module\user\module\loading\domain\LoadingUseCase.kt
增加65行到80行代码：
// 检查数据库是否已初始化，如果未初始化则先初始化
// 这种情况可能发生在应用进程被保留但数据库状态丢失时
val isDatabaseInit = AppServices
    .tallyDataSourceInitSpi
    .isInitStateOb
    .first()
if (!isDatabaseInit && latestUserId.isNotEmpty()) {
    // 重新初始化数据库
    AppServices
        .tallyDataSourceInitSpi
        .initTallyDataBase(userId = latestUserId)
    // 销毁之前可能存在的数据源实例
    AppServices
        .destroySpiAboutTallyDatabase()
}
~~~

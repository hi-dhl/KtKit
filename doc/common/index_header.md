KtKit 是用 Kotlin 语言编写的工具箱，包含了项目中常用的一系列工具，是 Jetpack ktx 系列的补充，涉及到了很多从 Kotlin 源码、Jetpack ktx、anko 等等知名的开源项目中学习的技巧，包含了 Kotlin 委托属性、高阶函数、扩展函数、内联、注解的使用等等，再次感谢以下项目提供的思路。

* Kotlin 官方 API
* [anko](https://github.com/Kotlin/anko)
* Google Jetpack ktx
* ......

## Download

**正式版本: 此版本包含稳定版本的 API**

```
// Project 级别的 `build.gradle`
allprojects {
    repositories {
        mavenCentral()
    }
}

// 模块级 `build.gradle`
dependencies {
    implementation "com.hi-dhl:ktkit:${ktkitVersion}"
}
```

**快照版本: 此版本包含最新的 API，版本号点击 [snapshots](https://oss.sonatype.org/content/repositories/snapshots/com/hi-dhl/ktkit/) 前往查看**

```
// Project 级别的 `build.gradle`
allprojects {
    repositories {
        maven { url 'https://oss.sonatype.org/content/repositories/snapshots/' }
    }
}

// 模块级 `build.gradle`
dependencies {
    implementation "com.hi-dhl:ktkit:${ktkitVersion}"
}
```

**如果这个仓库对你有帮助，请在仓库右上角帮我 star 一下，非常感谢你的支持，同时也欢迎你提交 PR**  ❤️❤️❤️


**执行 commit 或者 push 之前请先执行 `./gradlew spotlessApply`  会按照官方标准去格式化**

### 联系我

* 个人微信：hi-dhl
* 公众号：ByteCode，包含 Jetpack ，Kotlin ，Android 10 系列源码，译文，LeetCode / 剑指 Offer / 多线程 / 国内外大厂算法题 等等一系列文章

<img src='http://cdn.51git.cn/2020-10-20-151047.png' width = 350px/>




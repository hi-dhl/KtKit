**正式版本: 此版本包含稳定版本的 API**

[![Maven Central](https://img.shields.io/maven-central/v/com.hi-dhl/ktkit.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.hi-dhl%22%20AND%20a:%22ktkit%22)

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

<a href="https://oss.sonatype.org/content/repositories/snapshots/com/hi-dhl/ktkit"><img src="https://img.shields.io/badge/SNAPSHOT-v1.0.2-9933CC.svg?style=flat"></a>

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


项目中引用了 spotless 插件，执行 `./gradlew spotlessApply`  会将 Java 、Kotlin 、xml 、gradle 、md 、gitignore 等等文件按照官方标准去格式化。这也是 Google 提交代码的时候，推荐的方式。




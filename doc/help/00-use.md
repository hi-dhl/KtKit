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
    
}
```

**快照版本: 此版本包含最新的 API，版本号点击 [snapshots](https://oss.sonatype.org/content/repositories/snapshots/com/hi-dhl/ktkit/) 前往查看**

```
// Project 级别的 `build.gradle`
repositories {
    maven { url 'https://oss.sonatype.org/content/repositories/snapshots/' }

}

// 模块级 `build.gradle`
dependencies {
    implementation "com.hi-dhl:ktkit:1.0.0-SNAPSHOT"
}
```


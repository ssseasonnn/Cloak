![](Cloak.png)

[![](https://jitpack.io/v/ssseasonnn/Cloak.svg)](https://jitpack.io/#ssseasonnn/Cloak)

*Read this in other languages: [中文](README.zh.md), [English](README.md), [Change Log](CHANGELOG.md)*

# Cloak

Get or put data from SharedPreference safely and quickly.

## Prepare

1. Add the JitPack repository to your build file
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

2. Add the dependency

```gradle
dependencies {
	implementation 'com.github.ssseasonnn:Cloak:1.0.0'
}
```


## Usage

```kotlin
//define config:
object Config {
    var booleanSp by mutableSp<Boolean>()
    var intSp by mutableSp<Int>()
    var longSp by mutableSp<Long>()
    var floatSp by mutableSp<Float>()
    var stringSp by mutableSp<String>()

    var customSp by mutableSp<CustomBean>()

    var arraySp by mutableSp<Array<String>>()

    var listSp by mutableSp<List<String>>()
}

//save config：
Config.booleanSp = true
Config.customSp = CustomBean(123, true, "abc")


//use config：
val booleanSp = Config.booleanSp
```

### License

> ```
> Copyright 2019 Season.Zlc
>
> Licensed under the Apache License, Version 2.0 (the "License");
> you may not use this file except in compliance with the License.
> You may obtain a copy of the License at
>
>    http://www.apache.org/licenses/LICENSE-2.0
>
> Unless required by applicable law or agreed to in writing, software
> distributed under the License is distributed on an "AS IS" BASIS,
> WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
> See the License for the specific language governing permissions and
> limitations under the License.
> ```

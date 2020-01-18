package zlc.season.cloakapp

import zlc.season.cloak.mutableSp

/**
 * Recommend usage
 */
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
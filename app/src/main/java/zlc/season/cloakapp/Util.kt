package zlc.season.cloakapp

class CustomBean(
    var a: Int = 0,
    var b: Boolean = false,
    var c: String = ""
) {
    override fun toString(): String {
        return "[a=$a,b=$b,c=$c]"
    }
}
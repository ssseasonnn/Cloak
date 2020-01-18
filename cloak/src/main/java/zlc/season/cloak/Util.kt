package zlc.season.cloak

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import zlc.season.claritypotion.ClarityPotion.Companion.clarityPotion
import kotlin.reflect.KProperty
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.jvm.jvmErasure
import kotlin.reflect.typeOf

internal val gson by lazy { Gson() }

/**
 * For custom sp file name
 */
interface Cloak {
    fun spFileName(): String = javaClass.name
}

internal fun Any.createSp(isAppSp: Boolean): SharedPreferences {
    val spFileName = when {
        isAppSp -> clarityPotion.packageName
        this is Cloak -> spFileName()
        else -> this.javaClass.name
    }
    return clarityPotion.getSharedPreferences(spFileName, MODE_PRIVATE)
}

@ExperimentalStdlibApi
@Suppress("UNCHECKED_CAST")
internal fun <T> SharedPreferences.get(
    customKey: String,
    kProperty: KProperty<*>,
    defaultValue: T?
): T {
    val key = if (customKey.isEmpty()) kProperty.name else customKey
    val result: Any = when (val kType = kProperty.returnType) {
        //basic type
        typeOf<Int>() -> getInt(customKey, defaultValue.or { 0 })
        typeOf<Float>() -> getFloat(key, defaultValue.or { 0f })
        typeOf<Long>() -> getLong(key, defaultValue.or { 0L })
        typeOf<Boolean>() -> getBoolean(key, defaultValue.or { false })
        typeOf<String>() -> getString(key, defaultValue.or { "" })
        else -> {
            when {
                kType.isSubtypeOf(typeOf<Array<*>>()) || kType.isSubtypeOf(typeOf<List<*>>()) -> {
                    val jsonStr = getString(key, "") ?: ""
                    if (jsonStr.isEmpty()) {
                        defaultValue.or {
                            gson.fromJson("[]", kType.jvmErasure.java)
                        }
                    } else {
                        gson.fromJson(jsonStr, kType.jvmErasure.java)
                    }
                }
                else -> {
                    val jsonStr = getString(key, "") ?: ""
                    if (jsonStr.isEmpty()) {
                        defaultValue.or {
                            gson.fromJson("{}", kType.jvmErasure.java)
                        }
                    } else {
                        gson.fromJson(jsonStr, kType.jvmErasure.java)
                    }
                }
            }
        }
    }
    return result as T
}

@ExperimentalStdlibApi
@Suppress("UNCHECKED_CAST")
internal fun <T> SharedPreferences.Editor.put(
    customKey: String,
    kProperty: KProperty<*>,
    value: T
) {
    val key = if (customKey.isEmpty()) kProperty.name else customKey
    when (val kType = kProperty.returnType) {
        typeOf<Int>() -> putInt(key, value as Int)
        typeOf<Float>() -> putFloat(key, value as Float)
        typeOf<Long>() -> putLong(key, value as Long)
        typeOf<Boolean>() -> putBoolean(key, value as Boolean)
        typeOf<String>() -> putString(key, value as String)
        else -> putString(key, gson.toJson(value))
    }
}

internal inline fun <P, reified R> P?.or(defaultValue: () -> R): R {
    return this as? R ?: defaultValue()
}
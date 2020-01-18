package zlc.season.cloak

import android.annotation.SuppressLint
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Get or put data from SharedPreference safely and quickly.
 *
 * usage:
 *      val test by sp<String>()
 *
 * @param customKey customKey for sp
 * @param defaultValue defaultValue for sp
 * @param useCommit if true , editor will use commit() method instead apply()
 */
fun <T> Any.sp(
    customKey: String = "",
    defaultValue: T? = null,
    useCommit: Boolean = false
) = SharedPreferenceDelegate(false, customKey, defaultValue, useCommit)

/**
 * Usage:
 *      var test by mutableSp<String>()
 */
fun <T> Any.mutableSp(
    customKey: String = "",
    defaultValue: T? = null,
    useCommit: Boolean = false
) = MutableSharedPreferenceDelegate(false, customKey, defaultValue, useCommit)

/**
 * Get or put data from SharedPreference safely and quickly.
 *
 * usage:
 *      val test by appSp<String>()
 *
 * @param customKey customKey for sp
 * @param defaultValue defaultValue for sp
 * @param useCommit if true , editor will use commit() method instead apply()
 */
fun <T> Any.appSp(
    customKey: String = "",
    defaultValue: T? = null,
    useCommit: Boolean = false
) = SharedPreferenceDelegate(true, customKey, defaultValue, useCommit)

/**
 * Usage:
 *      var test by mutableAppSp<String>()
 */
fun <T> Any.mutableAppSp(
    customKey: String = "",
    defaultValue: T? = null,
    useCommit: Boolean = false
) = MutableSharedPreferenceDelegate(
    true,
    customKey,
    defaultValue,
    useCommit
)

@UseExperimental(ExperimentalStdlibApi::class)
class SharedPreferenceDelegate<T>(
    private val isAppSp: Boolean,
    private val customKey: String,
    private val defaultValue: T?,
    private val useCommit: Boolean
) : ReadOnlyProperty<Any, T> {

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        val sp = thisRef.createSp(isAppSp)
        val key = if (customKey.isEmpty()) property.name else customKey
        return sp.get(key, property, defaultValue)
    }
}


@UseExperimental(ExperimentalStdlibApi::class)
class MutableSharedPreferenceDelegate<T>(
    private val isAppSp: Boolean,
    private val customKey: String,
    private val defaultValue: T?,
    private val useCommit: Boolean
) : ReadWriteProperty<Any, T> {

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        val sp = thisRef.createSp(isAppSp)
        val key = if (customKey.isEmpty()) property.name else customKey
        return sp.get(key, property, defaultValue)
    }

    @SuppressLint("ApplySharedPref")
    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        val editor = thisRef.createSp(isAppSp).edit()
        val key = if (customKey.isEmpty()) property.name else customKey
        editor.put(key, property, value)

        if (useCommit) {
            editor.commit()
        } else {
            editor.apply()
        }
    }
}



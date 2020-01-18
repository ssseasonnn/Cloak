package zlc.season.cloakapp

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_test.*
import zlc.season.cloak.appSp
import zlc.season.cloak.mutableAppSp
import zlc.season.cloak.mutableSp
import zlc.season.cloak.sp

class TestActivity : AppCompatActivity() {

    //not same
    private val test by sp<Int>()

    //same data
    private val appIntSp by appSp<Int>()
    private val appBooleanSp by appSp<Boolean>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        textView.text = """
            test = $test
            appIntSp = $appIntSp
            appBooleanSp = $appBooleanSp
            customSp = ${Config.customSp}
        """.trimIndent()
    }
}

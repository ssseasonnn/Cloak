package zlc.season.cloakapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import zlc.season.cloak.mutableAppSp
import zlc.season.cloak.mutableSp

class MainActivity : AppCompatActivity() {
    //this will only use in MainActivity
    private var test by mutableSp<Int>()

    //We can use these in our app
    private var appIntSp by mutableAppSp<Int>()
    private var appBooleanSp by mutableAppSp<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test = 123

        appIntSp = 122
        appBooleanSp = true

        Config.customSp = CustomBean(123, true, "abc")

        btn_test.setOnClickListener {
            startActivity(Intent(this@MainActivity, TestActivity::class.java))
        }
    }
}

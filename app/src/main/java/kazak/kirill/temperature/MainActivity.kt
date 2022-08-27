package kazak.kirill.temperature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kazak.kirill.temperature.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        vb.btnDownload.setOnClickListener {
            loadData()
        }
    }

    private fun loadData() {
        vb.btnDownload.isEnabled = false
        vb.pb.visibility = View.VISIBLE
        getLocation{
            vb.tvLocation.text = it
            getTemperature {
                vb.tvTemperature.text = it.toString()
                vb.btnDownload.isEnabled = true
                vb.pb.visibility = View.GONE
            }
        }

    }

    private fun getLocation(callback: (String) -> Unit) {
        thread {
            Thread.sleep(5000)
            runOnUiThread { callback.invoke("Moscow") }
        }
    }

    private fun getTemperature(callback: (Int) -> Unit) {
        thread{
            runOnUiThread {
                Toast.makeText(this@MainActivity, "Start temperature loading!", Toast.LENGTH_SHORT).show()
            }
            Thread.sleep(5000)

            runOnUiThread { callback.invoke(17) }
        }
    }
}
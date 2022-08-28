package kazak.kirill.temperature

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kazak.kirill.temperature.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val vb by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)

        vb.btnDownload.setOnClickListener {
            lifecycleScope.launch {
                loadData()
            }
        }
    }

    private suspend fun loadData() {
        vb.btnDownload.isEnabled = false
        vb.pb.visibility = View.VISIBLE
        vb.tvLocation.text = getLocation()
        vb.tvTemperature.text = getTemperature().toString()
        vb.btnDownload.isEnabled = true
        vb.pb.visibility = View.GONE
    }

    private suspend fun getLocation(): String {
        delay(5000)
        return "Moscow"
    }

    private suspend fun getTemperature(): Int {
        Toast.makeText(this@MainActivity, "Start temperature loading!", Toast.LENGTH_SHORT).show()
        delay(5000)
        return 17
    }

}
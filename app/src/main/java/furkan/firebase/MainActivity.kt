package furkan.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class MainActivity : AppCompatActivity() {

    companion object{
        const val WARNING_TEXT = "warning_text"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig

        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.config_warning)

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    findViewById<TextView>(R.id.tvWarning).text= remoteConfig[WARNING_TEXT].asString()
                } else {
                    findViewById<TextView>(R.id.tvWarning).text= remoteConfig[WARNING_TEXT].asString()
                }

            }

    }
}
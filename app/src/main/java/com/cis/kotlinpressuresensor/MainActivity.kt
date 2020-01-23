package com.cis.kotlinpressuresensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

// 기압 센서를 이용하면 공기압을 측정할 수 있다.
// 사용자가 올라갔는지 내려갔는지 등을 측정할 수 있다.
class MainActivity : AppCompatActivity() {
    var manager : SensorManager? = null
    var listener : SensorListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        listener = SensorListener()

        startBtn.setOnClickListener {
            val sensor = manager?.getDefaultSensor(Sensor.TYPE_PRESSURE)
            val chk = manager?.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)

            if (chk == false) {
                tv.text = "기압 센서를 지원하지 않습니다."
            }
        }
    }

    inner class SensorListener : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent?) {
            if (event?.sensor?.type == Sensor.TYPE_PRESSURE) {
                tv.text = "현재 기압 : ${event?.values[0]} millibar"
            }
        }

    }
}

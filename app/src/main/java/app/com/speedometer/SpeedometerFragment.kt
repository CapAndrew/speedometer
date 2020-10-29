package app.com.speedometer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.speedometer_layout.*


class SpeedometerFragment : Fragment() {

    companion object {

        fun newInstance() = SpeedometerFragment()
    }

    private var speed = 20

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        accelerate.setOnClickListener {
            increaseSpeed(speed++)
        }

        accelerateBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                increaseSpeed(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                Toast.makeText(context, "start tracking", Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                Toast.makeText(context, "stop tracking", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun increaseSpeed(int: Int) {
        speedometer2.changeSpeed(int)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.speedometer_layout, container, false)
    }
}

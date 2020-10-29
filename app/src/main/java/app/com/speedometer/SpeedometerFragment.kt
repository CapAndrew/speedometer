package app.com.speedometer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.speedometer_layout.*


class SpeedometerFragment : Fragment() {

    companion object {

        fun newInstance() = SpeedometerFragment()
    }

    private var speed = 125

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        accelerate.setOnClickListener {
            increaseSpeed()
        }
    }

    private fun increaseSpeed(){

            speed += 10
            speedometer.changeAngle(speed)
        
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.speedometer_layout, container, false)
    }
}
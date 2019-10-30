package qc.ca.cstj.yannickbray.ui.succursale


import android.os.Bundle
import android.view.FrameMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import kotlinx.android.synthetic.main.fragment_succursale.*

import qc.ca.cstj.yannickbray.R
import qc.ca.cstj.yannickbray.models.Succursale

/**
 * A simple [Fragment] subclass.
 */
class SuccursaleFragment : Fragment() {

    private var succursales = listOf<Succursale>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_succursale, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}

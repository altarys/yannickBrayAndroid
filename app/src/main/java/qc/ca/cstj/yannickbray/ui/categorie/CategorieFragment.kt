package qc.ca.cstj.yannickbray.ui.categorie


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import qc.ca.cstj.yannickbray.R
import qc.ca.cstj.yannickbray.models.Categorie

/**
 * A simple [Fragment] subclass.
 */
class CategorieFragment : Fragment() {

    private var categories = listOf<Categorie>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categorie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)


    }


}

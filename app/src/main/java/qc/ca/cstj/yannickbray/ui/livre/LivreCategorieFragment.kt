package qc.ca.cstj.yannickbray.ui.livre


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import qc.ca.cstj.yannickbray.R
import qc.ca.cstj.yannickbray.models.Livre

/**
 * A simple [Fragment] subclass.
 */
class LivreCategorieFragment : Fragment() {

    private val args: LivreCategorieFragmentArgs by navArgs()
    private var livres = listOf<Livre>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_livre_categorie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.categorie.name
    }




}

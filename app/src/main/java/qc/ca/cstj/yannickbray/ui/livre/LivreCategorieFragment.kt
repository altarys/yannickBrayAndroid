package qc.ca.cstj.yannickbray.ui.livre


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.fragment_categorie.*
import kotlinx.android.synthetic.main.fragment_livre_categorie.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

import qc.ca.cstj.yannickbray.R
import qc.ca.cstj.yannickbray.Services
import qc.ca.cstj.yannickbray.adapters.CategorieRecyclerViewAdapter
import qc.ca.cstj.yannickbray.adapters.LivreRecyclerViewAdapter
import qc.ca.cstj.yannickbray.models.Categorie
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

        rcvLivre.layoutManager = LinearLayoutManager(this.context)

        // On charge tous les livres de la catégorie choisie
        loadLivre()
    }

    private fun loadLivre() {

        val url = Services.API_URL_GET_LIVRE_BY_CATEGORIE + args.categorie.nom
        // On appel le service avec la catégorie choisi, elle retourne une liste de livres en JSON
        url.httpGet().responseJson { request, response, result ->
            when(result) {
                is Result.Success -> {
                    // Si on arrive à faire une requête à la BD. On insère les livres de la catégories dans le recyclerView
                    livres = Json.parse(Livre.serializer().list,  result.value.obj()["results"].toString())
                    rcvLivre.adapter = LivreRecyclerViewAdapter(livres)
                    rcvLivre.adapter!!.notifyDataSetChanged()
                }
            }
        }
    }




}

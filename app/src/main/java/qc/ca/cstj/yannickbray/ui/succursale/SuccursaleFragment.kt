package qc.ca.cstj.yannickbray.ui.succursale


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.fragment_succursale.*

import kotlinx.serialization.json.Json
import kotlinx.serialization.list

import qc.ca.cstj.yannickbray.R
import qc.ca.cstj.yannickbray.Services
import qc.ca.cstj.yannickbray.adapters.SuccursaleRecyclerViewAdapter
import qc.ca.cstj.yannickbray.models.Succursale

/**
 * A simple [Fragment] subclass.
 */
class SuccursaleFragment : Fragment() {
    // Contient une liste d'objet succursale
    private var succursales = listOf<Succursale>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_succursale, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        // indique que le recyclerView sera en mode linéaire ( une entrée par ligne )
        rcvSuccursale.layoutManager = LinearLayoutManager(this.context)
        // Méthode qui s'occupe de charger la liste des succursales
        loadSuccursales()
    }

    private fun loadSuccursales() {
        // Envoi une requête get au serveur qui retourne les succursales contenu en bd
        Services.API_URL_GET_SUCCURSALE.httpGet().responseJson { request, response, result ->
            when(result) {
                is Result.Success -> {
                    // Désérialise la liste de succursales obtenu du serveur et peuple la liste d'objet succursale
                    // Utilise le nonstrict pour éviter d'avoir à déclarer le href et la collection d'inventaire qui ne serons pas utile dans ce cas ci
                    succursales = Json.nonstrict.parse(Succursale.serializer().list,  result.value.obj()["results"].toString())
                    rcvSuccursale.adapter = SuccursaleRecyclerViewAdapter(succursales)
                    rcvSuccursale.adapter!!.notifyDataSetChanged()
                }
            }
        }
    }


}

package qc.ca.cstj.yannickbray.ui.categorie


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
import kotlinx.android.synthetic.main.fragment_categorie.*

import kotlinx.serialization.json.Json
import kotlinx.serialization.list

import qc.ca.cstj.yannickbray.R
import qc.ca.cstj.yannickbray.Services
import qc.ca.cstj.yannickbray.adapters.CategorieRecyclerViewAdapter
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

        rcvCategorie.layoutManager = LinearLayoutManager(this.context)
        loadCategories()
    }

    private fun loadCategories() {
        Services.API_URL_GET_CATEGORIE.httpGet().responseJson {request, response, result ->
            when(result) {
                is Result.Success -> {

                    categories = Json.parse(Categorie.serializer().list,  result.value.obj()["results"].toString())
                    rcvCategorie.adapter = CategorieRecyclerViewAdapter(categories)
                    rcvCategorie.adapter!!.notifyDataSetChanged()
                }
            }
        }
    }


}


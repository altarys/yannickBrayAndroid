package qc.ca.cstj.yannickbray.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_categorie.view.*
import qc.ca.cstj.yannickbray.R
import qc.ca.cstj.yannickbray.models.Categorie
import qc.ca.cstj.yannickbray.ui.categorie.CategorieFragment

class CategorieRecyclerViewAdapter(private val values: List<Categorie>) : RecyclerView.Adapter<CategorieRecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorieRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_categorie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        with(holder){
            view.tag = item
            view.setOnClickListener {
                val categorie = it.tag as Categorie

                it.findNavController().navigate(direction)
            }
            bind(item)
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        fun bind(categorie: Categorie) {
            view.txvNom.text = categorie.name
        }
    }
}
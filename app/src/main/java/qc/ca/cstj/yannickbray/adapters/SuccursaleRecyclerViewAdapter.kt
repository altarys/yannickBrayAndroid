package qc.ca.cstj.yannickbray.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_detail_succursale.view.*
import kotlinx.android.synthetic.main.item_succursale.view.*
import qc.ca.cstj.yannickbray.R
import qc.ca.cstj.yannickbray.models.Succursale
import qc.ca.cstj.yannickbray.ui.succursale.SuccursaleFragmentDirections

class SuccursaleRecyclerViewAdapter(private val values: List<Succursale>) : RecyclerView.Adapter<SuccursaleRecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuccursaleRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_succursale, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        with(holder){
            view.tag = item
            view.setOnClickListener {
                val succursale = it.tag as Succursale

                val direction = SuccursaleFragmentDirections.actionNavSuccursaleToNavDetailSuccursale(succursale)
                // Navigue vers DetailSuccursale en passant en paramètre la succursale cliquée
                it.findNavController().navigate(direction)
            }
            bind(item)
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        fun bind(succursale: Succursale) {
            // Affiche le nom de la succursale
            view.txvNom.text = succursale.appelatif

        }
    }
}
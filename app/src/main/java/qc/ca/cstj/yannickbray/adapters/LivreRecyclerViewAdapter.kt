package qc.ca.cstj.yannickbray.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_categorie.view.*
import kotlinx.android.synthetic.main.item_livre.view.*
import qc.ca.cstj.yannickbray.R
import qc.ca.cstj.yannickbray.models.Livre
import qc.ca.cstj.yannickbray.ui.categorie.CategorieFragmentDirections
import qc.ca.cstj.yannickbray.ui.livre.LivreCategorieFragment
import qc.ca.cstj.yannickbray.ui.livre.LivreCategorieFragmentDirections

class LivreRecyclerViewAdapter(private val values: List<Livre>) : RecyclerView.Adapter<LivreRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LivreRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_livre, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        with(holder) {
            view.tag = item
            view.setOnClickListener {
                val livre = it.tag as Livre

                // On set le livre dans les arguments de navigation
                val direction = LivreCategorieFragmentDirections.actionNavLivreCategorieToDetailLivreFragment(livre)
                it.findNavController().navigate(direction)
            }
            bind(item)
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(livre: Livre) {
            // On lie les éléments d'un livre aux chamnps de texte présent dans la vue.
            view.txvTitre.text = livre.titre
            view.txvAuteur.text = "par "+livre.auteur
            Picasso.get().load(livre.imgurl).fit().centerInside().into(view.imvLivre)
        }
    }
}
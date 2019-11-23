package qc.ca.cstj.yannickbray.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_commentaire.view.*
import qc.ca.cstj.yannickbray.R
import qc.ca.cstj.yannickbray.models.Commentaire

class CommentaireRecyclerViewAdapter(private val values: List<Commentaire>) : RecyclerView.Adapter<CommentaireRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // On initialise le view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_commentaire, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Sur l'évènement bind, on initialise le tag avec le commentaire à l'index indiqué
        val item = values[position]
        with(holder){
            view.tag = item
            bind(item)
        }
    }

    inner class  ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(commentaire: Commentaire) {
            // On donne les informations au commentaire
            view.txvUtilisateur.text = commentaire.utilisateur
            view.txvCommentaire.text = commentaire.message
            view.txvDate.text = commentaire.dateCommentaire?.substringBefore('T')
            view.rtbRating.rating = commentaire.etoile.toFloat()
        }
    }
}
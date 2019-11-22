package qc.ca.cstj.yannickbray.ui.livre


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ContentFrameLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.httpPost
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.app_bar_navigation.*
import kotlinx.android.synthetic.main.content_navigation.*
import kotlinx.android.synthetic.main.fragment_detail_livre.*
import kotlinx.android.synthetic.main.fragment_livre_categorie.*

import qc.ca.cstj.yannickbray.R
import qc.ca.cstj.yannickbray.Services
import qc.ca.cstj.yannickbray.adapters.CommentaireRecyclerViewAdapter
import qc.ca.cstj.yannickbray.models.Commentaire
import qc.ca.cstj.yannickbray.models.Livre

/**
 * A simple [Fragment] subclass.
 */
class DetailLivreFragment : Fragment() {

    private val args: DetailLivreFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_livre, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcvCommentaires.layoutManager = LinearLayoutManager(this.context)

        loadDetailsLivre()
        loadCommentairesLivre()

        /*btnAjouter.setOnClickListener {
            val message: String = txtCommentaire.text.toString()
            val utilisateur: String = txtUtilisateur.text.toString()
            val etoile: Int = rtbNouveauCommentaire.rating.toInt()

            val commentaire: Commentaire = Commentaire(utilisateur = utilisateur, message = message,  etoile = etoile, dateCommentaire = "")
        }*/
    }

    private fun loadDetailsLivre(){
        val livre = args.livre

        (activity as AppCompatActivity).supportActionBar?.title = livre.titre
        txvAuteurLivre.text = livre.auteur
        txvCategorieLivre.text = livre.categorie
        txvCodeLivre.text = livre.ISBN.toString()
        txvPrixLivre.text = "%.2f".format(livre.prix) + " $"

        Picasso.get().load(livre.imgurl).fit().centerInside().into(imgLivre)
    }

    private fun loadCommentairesLivre(){
        val commentaires = args.livre.commentaires

        rcvCommentaires.adapter = CommentaireRecyclerViewAdapter(commentaires)
        rcvCommentaires.adapter!!.notifyDataSetChanged()
    }
}

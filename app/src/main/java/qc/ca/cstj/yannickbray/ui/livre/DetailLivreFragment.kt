package qc.ca.cstj.yannickbray.ui.livre


import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_livre.*
import kotlinx.android.synthetic.main.fragment_livre_categorie.*
import kotlinx.android.synthetic.main.item_commentaire.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import qc.ca.cstj.yannickbray.R
import qc.ca.cstj.yannickbray.adapters.CommentaireRecyclerViewAdapter
import qc.ca.cstj.yannickbray.adapters.LivreRecyclerViewAdapter
import qc.ca.cstj.yannickbray.models.Commentaire
import qc.ca.cstj.yannickbray.models.Livre
import java.nio.charset.Charset

/**
 * A simple [Fragment] subclass.
 */
class DetailLivreFragment : Fragment() {

    private val args: DetailLivreFragmentArgs by navArgs()
    private var commentaires = listOf<Commentaire>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_livre, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        commentaires = args.livre.commentaires.sortedWith(compareByDescending { it.dateCommentaire })

        rcvCommentaires.layoutManager = LinearLayoutManager(this.context)

        loadDetailsLivre()
        loadCommentairesLivre()

        btnAjouter.setOnClickListener {
            val message: String = txtCommentaire.text.toString()
            val utilisateur: String = txtUtilisateur.text.toString()
            val etoile: Int = rtbNouveauCommentaire.rating.toInt()

            val commentaire: Commentaire = Commentaire(utilisateur = utilisateur, message = message,  etoile = etoile, dateCommentaire = "")

            if (commentaire.utilisateur.isNotEmpty() && commentaire.etoile != 0) {
                "${args.livre.href}/commentaires".httpPost()
                    .header("Content-Type" to "application/json")
                        .body(Json.stringify(Commentaire.serializer(), commentaire), Charset.forName("UTF-8"))
                            .responseJson { request, response, result ->
                       
                        when(result) {
                            is Result.Success -> {
                                var livre = Json.parse(Livre.serializer(), result.value.content)
                                commentaires = livre.commentaires.sortedWith(compareByDescending { it.dateCommentaire })
                                txtCommentaire.text = Editable.Factory.getInstance().newEditable("")
                                txtUtilisateur.text = Editable.Factory.getInstance().newEditable("")
                                rtbRating.rating = 0.0f

                                loadCommentairesLivre()

                                Toast.makeText(this.context, getString(R.string.alerte_commentaire_ajoute), Toast.LENGTH_LONG).show()

                            }
                            is Result.Failure -> {
                                Toast.makeText(this.context, getString(R.string.alerte_commentaire_erreur), Toast.LENGTH_LONG).show()
                            }

                        }
                    }
            } else {
                Toast.makeText(this.context, getString(R.string.alerte_commentaire_incomplet), Toast.LENGTH_LONG).show()
            }
        }
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
        rcvCommentaires.adapter = CommentaireRecyclerViewAdapter(commentaires)
        rcvCommentaires.adapter!!.notifyDataSetChanged()
    }
}

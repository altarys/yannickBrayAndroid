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
    // Déclaration de variables pour les paramètres et la liste de commentaires
    private val args: DetailLivreFragmentArgs by navArgs()
    private var commentaires = listOf<Commentaire>()

    // Évènement arrivant sur la création de la fenêtre
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_livre, container, false)
    }

    // Évènement arrivant une fois la fenêtre créée
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // On charge les commentaires avec les commentaires du livre passé en paramètre
        commentaires = args.livre.commentaires.sortedWith(compareByDescending { it.dateCommentaire })

        rcvCommentaires.layoutManager = LinearLayoutManager(this.context)

        loadDetailsLivre()
        loadCommentairesLivre()
        // Sur le click du bouton d'ajout du commentaire
        btnAjouter.setOnClickListener {
            val message: String = txtCommentaire.text.toString()
            val utilisateur: String = txtUtilisateur.text.toString()
            val etoile: Int = rtbNouveauCommentaire.rating.toInt()
            // On créé un objet commentaire avec les informations entrées
            val commentaire: Commentaire = Commentaire(utilisateur = utilisateur, message = message,  etoile = etoile, dateCommentaire = "")

            // Valeures minimales, utilisateur et une note
            if (commentaire.utilisateur.isNotEmpty() && commentaire.etoile != 0) {
                // ON fait un post du commentaire
                "${args.livre.href}/commentaires".httpPost()
                    .header("Content-Type" to "application/json")
                        .body(Json.stringify(Commentaire.serializer(), commentaire), Charset.forName("UTF-8"))
                            .responseJson { request, response, result ->
                        // On vérifie la valeur de result
                        when(result) {
                            // S'il s'ajit d'un succès
                            is Result.Success -> {
                                // On stocke le nouvel objet livre retourné
                                var livre = Json.parse(Livre.serializer(), result.value.content)
                                // On met à jour les commentaires
                                commentaires = livre.commentaires.sortedWith(compareByDescending { it.dateCommentaire })
                                // On remet la section d'ajout d'un commentaire à jour
                                txtCommentaire.text = Editable.Factory.getInstance().newEditable("")
                                txtUtilisateur.text = Editable.Factory.getInstance().newEditable("")
                                rtbRating.rating = 0.0f
                                // On remet à jour les commentaires
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
        // On affiche le nom du livre comme titre
        (activity as AppCompatActivity).supportActionBar?.title = livre.titre
        txvAuteurLivre.text = livre.auteur
        // Affichage des catégories, on utilise un StringBuilder
        var lesCategories: StringBuilder = java.lang.StringBuilder()
        for (x in 0 until livre.categories.count()){
            if (x > 0)
                lesCategories.append(", ")
            lesCategories.append(livre.categories[x].nom)
        }
        txvCategorieLivre.text = lesCategories
        txvCodeLivre.text = livre.ISBN.toString()
        txvPrixLivre.text = "%.2f".format(livre.prix) + " $"
        // On affiche l'image
        Picasso.get().load(livre.imgurl).fit().centerInside().into(imgLivre)
    }

    private fun loadCommentairesLivre(){
        // On peuple les commentaires
        rcvCommentaires.adapter = CommentaireRecyclerViewAdapter(commentaires)
        rcvCommentaires.adapter!!.notifyDataSetChanged()
    }
}

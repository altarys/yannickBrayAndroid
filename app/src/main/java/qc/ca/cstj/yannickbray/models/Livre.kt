package qc.ca.cstj.yannickbray.models

import kotlinx.serialization.Serializable

@Serializable
data class Livre(val categories: List<Categorie>, val titre: String, val prix: Double, val auteur: String, val sujet: String, val ISBN: Int, val imgurl: String, val commentaires: List<Commentaire>, val inventaires: Inventaire, val href: String, val version: String) : java.io.Serializable
// data class Livre(val categorie: String, val titre: String, val prix: Double, val auteur: String, val sujet: String, val ISBN: Int, val imgurl: String, val commentaires: List<Commentaire>, val inventaires: Inventaire, val href: String, val version: String) : java.io.Serializable
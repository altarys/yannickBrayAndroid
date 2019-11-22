package qc.ca.cstj.yannickbray.models

import kotlinx.serialization.Serializable

@Serializable
data class Commentaire(val utilisateur: String, val message: String, val etoile: Int, val dateCommentaire: String?) : java.io.Serializable
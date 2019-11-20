package qc.ca.cstj.yannickbray.models

import kotlinx.serialization.Serializable

@Serializable
data class Succursale(val appelatif: String, val adresse: String, val ville: String, val codePostal: String,
                      val province: String, val telephone: String, val telecopieur: String, val information: String
                     ) : java.io.Serializable


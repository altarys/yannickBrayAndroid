package qc.ca.cstj.yannickbray

class Services {
    companion object {
        // Jérémie Ouimet : J'ai un processeur AMD, donc j'utilise NOX et son localhost est 172.17.100.2

        val API_URL_GET_CATEGORIE = "http://10.0.2.2:4500/categories/"
        // val API_URL_GET_CATEGORIE = "http://172.17.100.2:4500/categories/"

        val API_URL_GET_SUCCURSALE = "http://10.0.2.2:4500/succursales/"
        // val API_URL_GET_SUCCURSALE = "http://172.17.100.2:4500/succursales/"

        val API_URL_GET_LIVRE_BY_CATEGORIE = "http://10.0.2.2:4500/livres?categorie="
        // val API_URL_GET_LIVRE_BY_CATEGORIE = "http://172.17.100.2:4500/livres?categorie="

        val API_URL = "http://10.0.2.2:4500/"
        // val API_URL = "http://172.17.100.2:4500/"
    }
}
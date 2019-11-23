package qc.ca.cstj.yannickbray.ui.succursale


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_detail_succursale.*
import kotlinx.android.synthetic.main.fragment_detail_succursale.view.*

import qc.ca.cstj.yannickbray.R

/**
 * A simple [Fragment] subclass.
 */
class DetailSuccursaleFragment : Fragment() {
    // Succursale obtenu en paramètre
    private val args: DetailSuccursaleFragmentArgs by navArgs()
 

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_succursale, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Assigne les différentes propriétés de l'objet succursale à des champs de texte
        view.txvAppelatif.text = args.succursale.appelatif
        view.txvAdresse.text = args.succursale.adresse
        view.txvVille.text = args.succursale.ville +"(" + args.succursale.province + ")"
        view.txvCodePostal.text = args.succursale.codePostal
        // Téléphone et télécopieur son afficher en partie pour l'ajout de caractères spéciaux ( paranthèse, trait d'union etc )
        view.txvTelephone.text = "Téléphone : (" + args.succursale.telephone.subSequence(0,3) +") " + args.succursale.telephone.subSequence(4,7) + " -" +args.succursale.telephone.subSequence(7,12)
        view.txvTelecopieur.text = "Télécopieur : (" + args.succursale.telecopieur.subSequence(0,3)+") " + args.succursale.telecopieur.subSequence(4,7) + " -" +args.succursale.telecopieur.subSequence(7,12)
        view.txvInformation.text = "Informations : \n" + args.succursale.information
        // Horaire de la succursale hardcoder
        view.txvHoraire.text = "Lundi et Mardi : 9 h 30 à 18 h \nMercredi, Jeudi et Vendredi : 9 h 30 à 21 h \nSamedi : 9 h à 17 h \nDimanche : 10 h à 17 h"
    }


}

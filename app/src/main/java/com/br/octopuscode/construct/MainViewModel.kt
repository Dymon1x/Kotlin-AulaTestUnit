package com.br.octopuscode.construct

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference

class MainViewModel(cr: CollectionReference) : ViewModel() {

    val resListProds = MutableLiveData<ArrayList<Prod>>()

    val resSend = MutableLiveData<Boolean>()


    fun setValueLiveDataProd(listProd: ArrayList<Prod>) {
        resListProds.value = listProd

    }

    //enviando o documento para o firestore // cr.document(id) pode passar um id para o documento
    fun sendProd(prod: Prod) {
        cr.document().set(prod).addOnSuccessListener { //sucesso entra aqui quando enviado
            resSend.value = true

        }.addOnCanceledListener { // fracasso entra aqui
            resSend.value = false

        }
    }

    fun getProds() {// ja pega a lista
        cr.get().addOnSuccessListener { documents ->

            var listProds = arrayListOf<Prod>()

            for (document in documents) {
//                Log.i("TAG", document.toString())

                val prod: Prod = document.toObject(Prod::class.java)
                listProds.add(prod)
            }
            resListProds.value = listProds

        }

        //deleta no firebase store
        fun delProds(){
            cr.document("7JbT5vOFvXrmHqeUpbyV").delete().addOnSuccessListener {

            }.addOnCanceledListener {

            }
        }

        //atualiza
        fun updateProds(prod: Prod){
            cr.document("7JbT5vOFvXrmHqeUpbyV").set(prod).addOnSuccessListener {
                resSend.value = true

            }.addOnCanceledListener {
                resSend.value = false

            }
        }



        fun defaultListProd() = arrayListOf(
            Prod("Cimento", "Acabemento", 1, 32.0),
            Prod("Cimento", "Acabemento", 1, 32.0),
            Prod("Cimento", "Acabemento", 1, 32.0),
            Prod("Cimento", "Acabemento", 1, 32.0),
            Prod("Cimento", "Acabemento", 1, 32.0),
            Prod("Cimento", "Acabemento", 1, 32.0),
            Prod("Cimento", "Acabemento", 1, 32.0),
            Prod("Cimento", "Acabemento", 1, 32.0),
            Prod("Cimento", "Acabemento", 1, 32.0),
        )
    }
}
/*
    fun defaultListProd() = MutableLiveData<ArrayList<Prod>>().apply {
        value = arrayListOf(
            Prod("Cimento", "Acabemento", 1, 32.0),
            Prod("Cimento", "Acabemento", 1, 32.0),
            Prod("Cimento", "Acabemento", 1, 32.0),
            Prod("Cimento", "Acabemento", 1, 32.0),
            Prod("Cimento", "Acabemento", 1, 32.0),
            Prod("Cimento", "Acabemento", 1, 32.0),
            Prod("Cimento", "Acabemento", 1, 32.0),
            Prod("Cimento", "Acabemento", 1, 32.0),
            Prod("Cimento", "Acabemento", 1, 32.0),
        )
    }
*/

//}
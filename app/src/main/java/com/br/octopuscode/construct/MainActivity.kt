package com.br.octopuscode.construct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var prodAdapter: ProductAdapter
/*
    private val viewModel: MainViewModel by viewModels()
*/

    private val viewModel by viewModels<MainViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(cr) as T
            }
        }
    }

    //salvar no db firebase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
//        val listProds = defaultListProd()
//        Log.i("TAG", listProds.toString())
//        initAdapter(listProds)

        viewModel.sendProd(Prod("Argamassa", "Reboco", 1, 100.0))
        viewModel.getProds()


/*        var listProds = viewModel.defaultListProd()
        viewModel.setValueLiveDataProd(listProds)*/
        viewModel.resListProds.observe(this){
            initAdapter(it)
        }


    }

    private fun initRecycler(){
        rvProds.layoutManager = LinearLayoutManager(this)
        rvProds.setHasFixedSize(true)
    }

    private fun initAdapter(listProds: ArrayList<Prod>){
        prodAdapter = ProductAdapter(listProds, View.OnClickListener {
            Log.i("TAG", "Clicked !!!")
        })

        rvProds.adapter = prodAdapter // erro do rv resolvido
    }



}
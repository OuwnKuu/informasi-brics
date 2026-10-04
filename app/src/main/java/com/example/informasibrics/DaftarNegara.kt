package com.example.informasibrics

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.informasibrics.databinding.ActivityDaftarNegaraBinding

class DaftarNegara : AppCompatActivity() {
    private lateinit var binding: ActivityDaftarNegaraBinding
    private lateinit var adapter: NegaraAdapter
    private val semuaNegara = mutableListOf<Negara>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDaftarNegaraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rvDaftarNegara.layoutManager = LinearLayoutManager(this)
        Thread {
            val daftarNegara = ApiClient.getNegara()
            runOnUiThread {
                semuaNegara.clear()
                semuaNegara.addAll(daftarNegara)

                adapter = NegaraAdapter(semuaNegara.toMutableList())
                binding.rvDaftarNegara.adapter = adapter
            }
        }.start()
// TODO: Bikin SearchView berfungsi 
//        binding.svCariNegara.setOnQueryTextListener()
    }
}
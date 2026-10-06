package com.example.informasibrics

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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

        val daftarNegaraBrics = listOf(
            "BR", "RU", "IN", "CN", "ZA",
            "SA", "EG", "ET", "IR", "AE",
            "ID"
        )

        binding.rvDaftarNegara.layoutManager = LinearLayoutManager(this)
        Thread {
            val daftarNegara = ApiClient.getNegara()
            runOnUiThread {
                semuaNegara.clear()
                val negaraBrics = daftarNegara.filter { negara ->
                    daftarNegaraBrics.contains(negara.iso2)
                }
                semuaNegara.addAll(negaraBrics)

                adapter = NegaraAdapter(semuaNegara.toMutableList())
                binding.rvDaftarNegara.adapter = adapter
            }
        }.start()
        binding.svCariNegara.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val keyword = binding.svCariNegara.query.toString().trim()
                val hasil = semuaNegara.filter {
                    "${it.namaNegara} ${it.iso2} ${it.iso3}"
                        .contains(keyword, ignoreCase = true)
                }
                adapter.updateData(hasil)
                return true
            }
        })
    }
}
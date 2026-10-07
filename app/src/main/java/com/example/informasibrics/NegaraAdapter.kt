package com.example.informasibrics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import coil.decode.SvgDecoder
import coil.load

class NegaraAdapter(private var negaraList: MutableList<Negara>):
    RecyclerView.Adapter<NegaraAdapter.NegaraViewHolder>() {
    val informasiTambahan = mapOf(
        "BR" to "Negara besar di Amerika Selatan yang menjadi salah satu pendiri BRICS.",
        "RU" to "Negara terbesar di dunia yang berada di Benua Asia dan Eropa yang menjadi salah satu pendiri BRICS.",
        "IN" to "Negara dengan jumlah penduduk terbesar saat ini yang berada di Benua Asia yang menjadi salah satu pendiri BRICS.",
        "CN" to "Negara kekuatan ekonomi terbesar di Asia yang menjadi salah satu pendiri BRICS.",
        "ZA" to "Negara dengan 3 ibu kota yang menjadi pengubah nama kelompok organisasi ini dari BRIC menjadi BRICS.",
        "SA" to "Negara di gurun pasir yang menguasai minyak dunia.",
        "EG" to "Negara yang memiliki monumen Piramida Giza yang terkenal.",
        "ET" to "Negara di Afrika yang menjadi tempat lahirnya biji kopi di dunia.",
        "IR" to "Negara teokrasi Islam yang memiliki warisan agung peradaban kuno Persia.",
        "AE" to "Negara berbentuk federasi yang menggabungkan kemewahan yang futuristik dan warisan Arab yang dijaga ketat.",
        "ID" to "Negara kepulauan terbesar di dunia yang memiliki keberagaman suku, budaya, dan bahasa."
    )
    fun updateData(newList: List<Negara>) {
        negaraList.clear()
        negaraList.addAll(newList)
        notifyDataSetChanged()
    }
    class NegaraViewHolder(view: View):
        RecyclerView.ViewHolder(view) {
            val imgNegara: ImageView = view.findViewById(R.id.imgNegara)
            val tNama: TextView? = view.findViewById(R.id.tvNamaNegara)
            val tIso2: TextView? = view.findViewById(R.id.tvIso2)
            val tIso3: TextView? = view.findViewById(R.id.tvIso3)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NegaraViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_negara, parent, false)
        return NegaraViewHolder(view)
    }

    override fun onBindViewHolder(holder: NegaraViewHolder, position: Int) {
        val negara = negaraList[position]
        val safeUrl = negara.benderaNegara.trim()

        holder.tNama?.text = negara.namaNegara
        holder.tIso2?.text = negara.iso2
        holder.tIso3?.text = negara.iso3
        holder.imgNegara.load(safeUrl) {
            crossfade(true)
            addHeader("User-Agent", "Mozilla/5.0")
            error(R.drawable.ic_launcher_background)
            decoderFactory(SvgDecoder.Factory())
        }

        holder.itemView.setOnClickListener {
            val deskripsi = informasiTambahan[negara.iso2] ?: "Belum ada informasi tambahan mengenai negara ini."
            val pesanDetail = """
                Nama Negara: ${negara.namaNegara} ${negara.iso2} | ${negara.iso3}
                deskripsi:
                $deskripsi
            """.trimIndent()

            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Informasi Detail")
                .setMessage(pesanDetail)
                .setPositiveButton("Tutup") { dialog, _->
                    dialog.dismiss()
                }
                .create()
                .show()
        }
    }

    override fun getItemCount(): Int {
        return negaraList.size
    }
}
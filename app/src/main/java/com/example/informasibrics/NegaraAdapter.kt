package com.example.informasibrics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NegaraAdapter(private var negaraList: MutableList<Negara>):
    RecyclerView.Adapter<NegaraAdapter.NegaraViewHolder>() {
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

        holder.tNama?.text = negara.namaNegara
        holder.tIso2?.text = negara.iso2
        holder.tIso3?.text = negara.iso3

        // TODO: Baris kode glide untuk gambar negara
    }

    override fun getItemCount(): Int {
        return negaraList.size
    }
}
package com.example.informasibrics

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.informasibrics.databinding.ActivitySejarahBricsBinding

class SejarahBrics : AppCompatActivity() {
    private lateinit var binding: ActivitySejarahBricsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySejarahBricsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val htmlSejarahBricsString = resources.openRawResource(R.raw.sejarah_brics)
            .bufferedReader()
            .use { it.readText() }
        binding.tvSejarah.text = HtmlCompat.fromHtml(htmlSejarahBricsString, HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.tvSejarah.movementMethod = LinkMovementMethod.getInstance()

        val htmlPerkembanganBricsString = resources.openRawResource(R.raw.perkembangan_brics)
            .bufferedReader()
            .use { it.readText() }
        binding.tvPerkembangan.text = HtmlCompat.fromHtml(htmlPerkembanganBricsString, HtmlCompat.FROM_HTML_MODE_LEGACY)

        binding.tvPerkembangan.movementMethod = LinkMovementMethod.getInstance()

        val htmlTantanganBricsString = resources.openRawResource(R.raw.tantangan_brics)
            .bufferedReader()
            .use { it.readText() }
        binding.tvTantangan.text = HtmlCompat.fromHtml(htmlTantanganBricsString, HtmlCompat.FROM_HTML_MODE_LEGACY)

        binding.tvTantangan.movementMethod = LinkMovementMethod.getInstance()
    }
}
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
        val htmlString = resources.openRawResource(R.raw.sejarah_brics)
            .bufferedReader()
            .use { it.readText() }
        binding.tvSejarah.text = HtmlCompat.fromHtml(htmlString, HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.tvSejarah.movementMethod = LinkMovementMethod.getInstance()
    }
}
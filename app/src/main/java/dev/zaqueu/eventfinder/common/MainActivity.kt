package dev.zaqueu.eventfinder.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.zaqueu.eventfinder.R
import dev.zaqueu.eventfinder.common.presentation.AppFragmentFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAppFragmentFactory()
        setContentView(R.layout.activity_main)
    }

    private fun setupAppFragmentFactory() {
        supportFragmentManager.fragmentFactory = AppFragmentFactory()
    }
}

package com.example.hearthstoneapp.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import xavier.albanet.projetprogrammationmobile.R

class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //if(getSupportActionBar()!=null)
        //  this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics())
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val intent = Intent(this@splash, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
            finish()
        }, time.toLong())
    }

    companion object {
        private const val time = 500
    }
}
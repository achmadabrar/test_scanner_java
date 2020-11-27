package com.achmadabrar.cakaptestapp.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.achmadabrar.cakaptestapp.R
import com.achmadabrar.cakaptestapp.core.base.BaseActivity

class ContactActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
    }
}
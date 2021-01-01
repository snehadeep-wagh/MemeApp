package com.example.android.notes.getData

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.android.notes.MVVM.NotesViewModel
import com.example.android.notes.MainActivity
import com.example.android.notes.R
import com.example.android.notes.Room.NotesEntity
import com.example.android.notes.databinding.ActivityGetDataBinding

class GetData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var note: String
        val binding = DataBindingUtil.setContentView<ActivityGetDataBinding>(this, R.layout.activity_get_data)
        binding.button.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                note = binding.getdataid.text.toString()
                if(note.isNotEmpty())
                {
                    Intent(this, MainActivity::class.java).also {
                        startActivity(it)
                    }

                    val noteVM = ViewModelProvider(
                        this,
                        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
                    ).get(NotesViewModel::class.java)
                    noteVM.insertNote(NotesEntity(note))
                }
                else
                {
                    Toast.makeText(applicationContext, "Please add some text", Toast.LENGTH_SHORT).show()
                }

            }

            val hide = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hide.hideSoftInputFromWindow(binding.root.windowToken, 0)
        }
    }
}
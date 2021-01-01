package com.example.android.notes

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.android.notes.MVVM.NotesViewModel
import com.example.android.notes.RecyclerView.INotesHolder
import com.example.android.notes.RecyclerView.NotesAdapter
import com.example.android.notes.Room.NotesEntity
import com.example.android.notes.databinding.ActivityMainBinding
import com.example.android.notes.getData.GetData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INotesHolder {
    lateinit var noteVM: NotesViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        lateinit var getnote: String
        //floatbutton ----
        floatingActionButton2.setOnClickListener {
            Intent(this, GetData::class.java).also {
               startActivity(it)
            }
        }


        // RecyclerView ----
        recyclerView.layoutManager = LinearLayoutManager(this)
        val notesAdapter: NotesAdapter = NotesAdapter(this, this)
        recyclerView.adapter = notesAdapter


        // VIEW MODEL ----
        noteVM = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NotesViewModel::class.java)

        noteVM.getAllNotes.observe(this, Observer { list ->
            list?.let {
                notesAdapter.updateList(it)
            }

        })


        // submit button ----
        binding.SubmitButton.setOnClickListener {
            val note: String = binding.editText.text.toString()
            Log.i("Print", note)
            if(note.isNotEmpty())
            {
                noteVM.insertNote(NotesEntity(note))
            }

            val hide = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hide.hideSoftInputFromWindow(binding.root.windowToken, 0)

        }
    }


    override fun onItemClicked(notesEntity: NotesEntity) {
            noteVM.deleteNote(notesEntity)

    }


}
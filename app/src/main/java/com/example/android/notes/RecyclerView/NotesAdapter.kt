package com.example.android.notes.RecyclerView

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Constraints
import androidx.recyclerview.widget.RecyclerView
import com.example.android.notes.MainActivity
import com.example.android.notes.R
import com.example.android.notes.Room.NotesEntity

class NotesAdapter(context: Context, val listner: INotesHolder): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val listOfNotes = ArrayList<NotesEntity>()

    class NotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val previewText = itemView.findViewById<TextView>(R.id.previewText)!!
        val deleteId = itemView.findViewById<ImageView>(R.id.deleteId)!!
        val constrainid = itemView.findViewById<ConstraintLayout>(R.id.constrainid)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val NotesViewHolderObject = NotesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.notespreview, parent, false))
        NotesViewHolderObject.deleteId.setOnClickListener {
            listner.onItemClicked(listOfNotes[NotesViewHolderObject.adapterPosition])
        }

        return NotesViewHolderObject
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentEntity = listOfNotes[position]
        holder.previewText.text = currentEntity.notesText


    }

    override fun getItemCount(): Int {
        return listOfNotes.size
    }

    fun updateList(newList: List<NotesEntity>)
    {
        listOfNotes.clear()
        listOfNotes.addAll(newList.reversed())

        notifyDataSetChanged()
    }

    fun onItemClickedChangesize()
    {

    }
}

interface INotesHolder
{
    fun onItemClicked(notesEntity: NotesEntity)


}

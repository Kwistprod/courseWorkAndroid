package com.example.myapplication.Controllers.NotesController;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.note.Note;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class RVNOTEAdapter extends RecyclerView.Adapter<RVNOTEAdapter.NoteViewHolder>{

    public static class NoteViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextInputEditText txt;
        public NoteViewHolder(@NonNull View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardnote);
            txt = (TextInputEditText) itemView.findViewById(R.id.txtNote);
        }
    }

    List<Note> notes;
    public RVNOTEAdapter(List<Note> notes){
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_notes, parent, false);
        NoteViewHolder nvh = new NoteViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.txt.setText(notes.get(position).getNote());
       holder.txt.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
                notes.get(position).setNote(s.toString());
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });
    }

    public void addNote(Note n){
        notes.add(0, n);
        notifyDataSetChanged();
        notifyItemInserted(0);
        notifyItemRangeChanged(0, notes.size());
    }

    public List<Note> getNotes(){
        return this.notes;
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}

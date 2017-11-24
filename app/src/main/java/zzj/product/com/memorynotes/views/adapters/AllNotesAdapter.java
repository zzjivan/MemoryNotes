package zzj.product.com.memorynotes.views.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import zzj.product.com.memorynotes.R;
import zzj.product.com.memorynotes.provider.database.NoteContract;

/**
 * Created by zzj on 17-11-23.
 */

public class AllNotesAdapter extends RecyclerView.Adapter<AllNotesAdapter.NotesViewHolder> {
    private Context context;
    private Cursor cursor;

    public AllNotesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public AllNotesAdapter.NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NotesViewHolder holder = new NotesViewHolder(LayoutInflater.from(
                context).inflate(R.layout.notes_list_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(AllNotesAdapter.NotesViewHolder holder, int position) {
        if (cursor.moveToPosition(position)) {
            holder.title.setText(cursor.getString(cursor.getColumnIndex(NoteContract.NoteDetail.DELETE_TIME)));
            holder.content.setText(cursor.getString(cursor.getColumnIndex(NoteContract.NoteDetail.NOTE_CONTENT)));
        }
    }

    @Override
    public int getItemCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    /**
     *
     * @param c 传入null时，关闭cursor
     */
    public void setCursor(Cursor c) {
        if (c == null && cursor != null)
            cursor.close();
        cursor = c;
    }

    static class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;

        public NotesViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            content = itemView.findViewById(R.id.note_content);
        }
    }
}

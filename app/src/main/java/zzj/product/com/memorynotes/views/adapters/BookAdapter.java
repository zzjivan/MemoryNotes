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

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private Context context;
    private Cursor cursor;

    public BookAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BookAdapter.BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BookViewHolder holder = new BookViewHolder(LayoutInflater.from(
                context).inflate(R.layout.book_list_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(BookAdapter.BookViewHolder holder, int position) {
        if (cursor.moveToPosition(position)) {
            holder.name.setText(cursor.getString(cursor.getColumnIndex(NoteContract.Book.BOOK_NAME)));
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

    static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView count;

        public BookViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.book_name);
            count = itemView.findViewById(R.id.book_content_count);
        }
    }
}

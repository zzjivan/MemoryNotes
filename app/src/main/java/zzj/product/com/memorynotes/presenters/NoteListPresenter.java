package zzj.product.com.memorynotes.presenters;

/**
 * Created by zzj on 17-11-23.
 */

public interface NoteListPresenter{
    void loadAllNoteList();
    void addNote(String title, String content);
    void deleteNote(int id);
}

package zzj.product.com.memorynotes.presenters;

/**
 * Created by zzj on 17-11-23.
 */

public interface BookListPresenter {
    void loadBook();
    void addBook(String name);
    void deleteBook(int id);
}

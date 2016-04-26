package com.product.helpshopping.ui.entiy;

import com.product.helpshopping.db.gen.Note;

/**
 * Created by Administrator on 2016/3/18 0018.
 */
public class NoteEntiy {
    private Note note;
    private int count;

    public NoteEntiy(int count, Note note) {
        this.count = count;
        this.note = note;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}

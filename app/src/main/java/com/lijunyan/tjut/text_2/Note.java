package com.lijunyan.tjut.text_2;

import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.lijunyan.tjut.notes.db.NotesDB;


public class Note extends ListActivity {


    private OnClickListener btnAddNote_clickHandler = new OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivityForResult(new Intent(Note.this,AtyEditNote.class),REQUEST_CODE_ADD_NOTE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        db = new NotesDB(this);
        dbRead = db.getReadableDatabase();

        adapter = new SimpleCursorAdapter(
                this, R.layout.notes_list_cell,null,
                new String[]{NotesDB.COLUMN_NAME_NOTE_NAME,NotesDB.COLUMN_NAME_NOTE_DATE},
                new int[]{R.id.tvName,R.id.tvDate});
        setListAdapter(adapter);

        refreshNotesListView();

        findViewById(R.id.btnAddNote).setOnClickListener(btnAddNote_clickHandler);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Cursor c = adapter.getCursor();
        c.moveToPosition(position);

        Intent i = new Intent(Note.this,AtyEditNote.class);
        i.putExtra(AtyEditNote.EXTRA_NODE_ID,c.getInt(c.getColumnIndex(NotesDB.COLUMN_NAME_ID)));
        i.putExtra(AtyEditNote.EXTRA_NODE_NAME,c.getInt(c.getColumnIndex(NotesDB.COLUMN_NAME_NOTE_NAME)));
        i.putExtra(AtyEditNote.EXTRA_NODE_CONTENT,c.getInt(c.getColumnIndex(NotesDB.COLUMN_NAME_NOTE_CONTENT)));

        startActivityForResult(i,REQUEST_CODE_EDIT_NOTE);

        super.onListItemClick(l, v, position, id);


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case REQUEST_CODE_ADD_NOTE:
            case REQUEST_CODE_EDIT_NOTE:
                if(requestCode == Note.RESULT_OK){
                    refreshNotesListView();
                }
        }

        super.onActivityResult(requestCode, resultCode, data);



    }





    public  void refreshNotesListView(){

        adapter.changeCursor(dbRead.query(NotesDB.TABLE_NAME_NOTES,null,null,null,null,null,null));
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }


    private SimpleCursorAdapter adapter = null;
    private NotesDB db;
    private SQLiteDatabase dbRead;

    public static final int REQUEST_CODE_ADD_NOTE = 1;
    public static final int REQUEST_CODE_EDIT_NOTE = 2;



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

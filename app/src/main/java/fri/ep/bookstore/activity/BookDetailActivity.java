package fri.ep.bookstore.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class BookDetailActivity extends Activity {

    private static final String TAG = BookDetailActivity.class.getCanonicalName();
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_details);

        tv = (TextView) findViewById(R.id.textView1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.book_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Confirm deletion");
                dialog.setMessage("Are you sure?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteBook();
                    }
                });
                dialog.setNegativeButton("Cancel", null);
                dialog.create().show();
                return true;
            case R.id.action_edit:
                // TODO: start BookAddActivity for editing
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void deleteBook() {
        // TODO: send a DELETE request
    }
}

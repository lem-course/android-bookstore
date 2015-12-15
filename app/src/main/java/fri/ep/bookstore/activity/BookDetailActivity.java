package fri.ep.bookstore.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import fri.ep.bookstore.model.Book;

public class BookDetailActivity extends Activity {

    private static final String TAG = BookDetailActivity.class.getCanonicalName();
    private TextView tv;
    private String uri;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_details);

        tv = (TextView) findViewById(R.id.textView1);
        uri = getIntent().getStringExtra("uri");
    }

    @Override
    protected void onResume() {
        super.onResume();

        final RequestQueue queue = Volley.newRequestQueue(this);
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        final Gson gson = new Gson();
                        book = gson.fromJson(response, Book.class);
                        tv.setText(String.format("Author: %s%nTitle: %s%nPrice: %.2f%nDescription: %s",
                                book.author, book.title, book.price, book.description));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BookDetailActivity.this, "An error occurred.", Toast.LENGTH_LONG).show();
                Log.w(TAG, error.getMessage());
            }
        });

        queue.add(stringRequest);
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
                final Intent intent = new Intent(this, BookAddActivity.class);
                intent.putExtra("book", book);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void deleteBook() {
        final RequestQueue queue = Volley.newRequestQueue(this);
        final StringRequest stringRequest = new StringRequest(Request.Method.DELETE, uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BookDetailActivity.this, "An error occurred.", Toast.LENGTH_LONG).show();
                Log.w(TAG, error.getMessage());
            }
        });

        queue.add(stringRequest);
    }
}

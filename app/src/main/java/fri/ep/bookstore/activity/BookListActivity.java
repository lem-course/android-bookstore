package fri.ep.bookstore.activity;

import fri.ep.bookstore.model.Book;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class BookListActivity extends ListActivity {

    public static final String ALL_BOOKS = "http://192.168.34.106/netbeans/rest-api/books";
    private static final String TAG = BookListActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final RequestQueue queue = Volley.newRequestQueue(this);
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, ALL_BOOKS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        handleResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BookListActivity.this, "An error occurred.", Toast.LENGTH_LONG).show();
                Log.w(TAG, "Exception: " + error.getLocalizedMessage());
            }
        });

        queue.add(stringRequest);
    }

    private void handleResponse(String response) {
        final Gson gson = new Gson();
        final Book[] books = gson.fromJson(response, Book[].class);

        final ArrayAdapter<Book> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, books);
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.book_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert:
                startActivity(new Intent(this, BookAddActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        final Book book = (Book) getListAdapter().getItem(position);
        final Intent intent = new Intent(this, BookDetailActivity.class);
        intent.putExtra("uri", book.uri);
        startActivity(intent);
    }
}

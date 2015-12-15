package fri.ep.bookstore.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class BookAddActivity extends Activity {
    private static final String TAG = BookAddActivity.class.getCanonicalName();
    private EditText author, title, price, description;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_add);

        author = (EditText) findViewById(R.id.etAuthor);
        title = (EditText) findViewById(R.id.etTitle);
        price = (EditText) findViewById(R.id.etPrice);
        description = (EditText) findViewById(R.id.etDescription);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook();
            }
        });
    }

    private void addBook() {
        final RequestQueue queue = Volley.newRequestQueue(this);
        final StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                BookListActivity.ALL_BOOKS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(BookAddActivity.this, "Entry saved.", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(BookAddActivity.this, BookListActivity.class));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BookAddActivity.this, "An error occurred.", Toast.LENGTH_LONG).show();
                Log.w(TAG, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                final Map<String, String> params = new HashMap<>();
                params.put("author", author.getText().toString().trim());
                params.put("title", title.getText().toString().trim());
                params.put("description", description.getText().toString().trim());
                params.put("price", price.getText().toString().trim());
                return params;
            }
        };

        queue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.book_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

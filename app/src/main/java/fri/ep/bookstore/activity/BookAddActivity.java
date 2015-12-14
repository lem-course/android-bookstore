package fri.ep.bookstore.activity;

import fri.ep.bookstore.model.Book;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BookAddActivity extends Activity {
    private EditText author, title, price, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_add);

        author = (EditText) findViewById(R.id.etAuthor);
        title = (EditText) findViewById(R.id.etTitle);
        price = (EditText) findViewById(R.id.etPrice);
        description = (EditText) findViewById(R.id.etDescription);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.book_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void insert(View v) {
        try {
            /*Book book = new Book(0, author.getText().toString(), title.getText().toString(),
                    description.getText().toString(),
                    Double.parseDouble(price.getText().toString()), null);
            new SaveBookAsync(this).execute(book);*/
        } catch (Exception e) {
            Toast.makeText(this, "An error occurred: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private class SaveBookAsync extends AsyncTask<Book, Void, String> {
        private final Context context;

        public SaveBookAsync(Context c) {
            context = c;
        }

        @Override
        protected String doInBackground(Book... input) {
            /*try {
                HttpClient client = new DefaultHttpClient();
                HttpPost request = new HttpPost(BookListActivity.ALL_BOOKS);
                List<NameValuePair> pairs = new ArrayList<NameValuePair>();
                pairs.add(new BasicNameValuePair("author", input[0].author));
                pairs.add(new BasicNameValuePair("title", input[0].title));
                pairs.add(new BasicNameValuePair("description", input[0].description));
                pairs.add(new BasicNameValuePair("price", String.valueOf(input[0].price)));
                request.setEntity(new UrlEncodedFormEntity(pairs));

                HttpResponse response = client.execute(request);
                HttpEntity httpEntity = response.getEntity();
                JSONObject json = new JSONObject(EntityUtils.toString(httpEntity));

                if (json.getString("status").equals("success")) {
                    return null;
                } else {
                    return json.getString("payload");
                }
            } catch (Exception e) {
                return e.getMessage();
            }*/

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result == null) {
                Toast.makeText(context, "Book saved.", Toast.LENGTH_LONG).show();
                startActivity(new Intent(BookAddActivity.this, BookListActivity.class));
            } else {
                Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            }
        }

    }
}

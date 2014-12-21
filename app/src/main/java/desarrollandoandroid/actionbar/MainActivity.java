package desarrollandoandroid.actionbar;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

/**
 * Creado por Pablo Bascuñana el 13/12/14.
 */
public class MainActivity extends ActionBarActivity implements ActionBar.OnNavigationListener, SearchView.OnQueryTextListener, DrawerLayout.DrawerListener {

    EditText mEditTextTexto, mEditTextVacio;
    Button mButton;

    ActionBar mActionBar;

/** Navigation Drawer */
    DrawerLayout mNavigationDrawer;
    ListView mListView;
    String[] mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActionBar = getSupportActionBar();

        mEditTextTexto = (EditText) findViewById(R.id.textView);
        mEditTextVacio = (EditText) findViewById(R.id.textView2);
        mButton = (Button) findViewById(R.id.button);

        //mActionBar.hide();   // Ocultar ActionBar
        //mActionBar.setTitle("Titulo ActionBar");   // Establece un titulo para la ActionBar
        //mActionBar.setSubtitle("Subtitulo ActionBar");   // Establece un subtitulo para la AcionBar
        mActionBar.setIcon(R.drawable.ic_view_headline_white_24dp);

/** Spinner ActionBar */
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST); // Modo de navegación de lista despegable (Spinner)
        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.datos,
                android.R.layout.simple_spinner_dropdown_item);
        mActionBar.setListNavigationCallbacks(mSpinnerAdapter, this);

/** Navigation Drawer */
        mDataList = getResources().getStringArray(R.array.datosLista);
        mNavigationDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mListView = (ListView) findViewById(R.id.left_drawer);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, R.layout.list_item, mDataList);
        mListView.setAdapter(mAdapter);
// Activamos la escucha de los elementos del ListView del NavigationDrawer.
        mListView.setOnItemClickListener(new DrawerItemClickListener());

        mNavigationDrawer.setDrawerListener(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buttonIntent = new Intent(MainActivity.this, secondActivity.class);
                startActivity(buttonIntent);
            }
        });
    }

// SearchView Widget y ShareActionProvider Widget
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
/** SearchView widget */
        MenuItem mSearchItem = menu.findItem(R.id.buscar);

        //Asocio la configuración de búsqueda a la vista de búsqueda (MenuItem y SearchManager asociados a SearchView).
        SearchManager mSearchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView mSearchView = (SearchView) mSearchItem.getActionView();
        mSearchView.setSearchableInfo(mSearchManager.getSearchableInfo(getComponentName()));

        // Introuduzco una pista en la barra de búsqueda
        mSearchView.setQueryHint("Búsqueda en Google");
        mSearchView.setOnQueryTextListener(this);
/** ShareActionProvider Widget */
        MenuItem mShareItem = menu.findItem(R.id.compartir);

        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(mShareItem);
        mShareActionProvider.setShareIntent(conseguirIntent());

        return super.onCreateOptionsMenu(menu);
    }

// Elementos de acción Copiar y Pegar con EditText en ActionBar
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
/** Elementos de accion en la ActionBar copiar */
            case R.id.copiar:
                ClipboardManager mClipBoardManagerCopy = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                String mTextCopy = mEditTextTexto.getText().toString();
                ClipData mClipCopy = ClipData.newPlainText("etiqueta", mTextCopy);
                mClipBoardManagerCopy.setPrimaryClip(mClipCopy);
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.copiado), Toast.LENGTH_SHORT).show();
                return true;
/** Botón ActionBar pegar */
            case R.id.pegar:
                ClipboardManager mClipBoardManagerPaste = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData mClipPaste = mClipBoardManagerPaste.getPrimaryClip();
                ClipData.Item mItem = mClipPaste.getItemAt(0);
                String mTextPaste = mItem.getText().toString();
                mEditTextVacio.setText(mTextPaste);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

/** Spinner en ActionBar */
    @Override
    public boolean onNavigationItemSelected(int i, long l) {
        switch (i) {
            case 0:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.cero), Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.uno), Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.dos), Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.tres), Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.cuatro), Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.cinco), Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.seis), Toast.LENGTH_SHORT).show();
                break;
            case 7:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.siete), Toast.LENGTH_SHORT).show();
                break;
            case 8:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.ocho), Toast.LENGTH_SHORT).show();
                break;
            case 9:
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.nueve), Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

/** Escuchas del SearchView */
    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
        Intent googleIntent = new Intent (Intent.ACTION_WEB_SEARCH);
        googleIntent.putExtra(SearchManager.QUERY, query);
        startActivity(googleIntent);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //Toast.makeText(this, newText, Toast.LENGTH_SHORT).show();
        return false;
    }

    private Intent conseguirIntent() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "http://desarrollandoAndroid.wordpress.com");
        intent.setType("text/plain");
        return intent;
    }

/** Manejamos los eventos de apertura y cierre del Navigation Drawer */
    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        mActionBar.setTitle("NavigationDrawer abierto");
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        mActionBar.setTitle("NavigationDrawer cerrado");
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position){
                case 0:
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.Puno), Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.Pdos), Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.Ptres), Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.Pcuatro), Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.Pcinco), Toast.LENGTH_SHORT).show();
                    break;
                case 5:
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.Pseis), Toast.LENGTH_SHORT).show();
                    break;
                case 6:
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.Psiete), Toast.LENGTH_SHORT).show();
                    break;
                case 7:
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.Pocho), Toast.LENGTH_SHORT).show();
                    break;
                case 8:
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.Pnueve), Toast.LENGTH_SHORT).show();
                    break;
                case 9:
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.Pdiez), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}



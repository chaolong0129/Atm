package com.rff.atm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LOGIN = 100;
    private static final String TAG = MainActivity.class.getSimpleName();
    boolean logon = false;
    private RecyclerView recyclerView;
    private List<Function> functions;
    //    String[] functions = null;

    public class IconAdapter extends RecyclerView.Adapter<IconAdapter.IconHolder> {
        @NonNull
        @Override
        public IconHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = getLayoutInflater().inflate(R.layout.item_icon, viewGroup, false);
            return new IconHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull IconHolder iconHolder, int i) {
            final Function function = functions.get(i);
            iconHolder.nameText.setText(function.getName());
            iconHolder.iconImage.setImageResource(function.icon);
            iconHolder.iconImage.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    itemClicked(function);
                }
            });
        }

        @Override
        public int getItemCount() {
            return functions.size();
        }

        public class IconHolder extends RecyclerView.ViewHolder {
            ImageView iconImage;
            TextView nameText;

            public IconHolder(@NonNull View itemView) {
                super(itemView);

                iconImage = itemView.findViewById(R.id.item_icon);
                nameText = itemView.findViewById(R.id.item_name);
            }
        }
    }

    private void itemClicked(Function function) {
        Log.d(TAG, "itemClicked: "+function.getName());
        switch (function.getIcon())
        {
            case R.drawable.func_transaction:
                break;
            case R.drawable.func_balance:
                break;
            case R.drawable.func_finance:
                break;
            case R.drawable.func_contacts:
                break;
            case R.drawable.func_exit:
                finish();
                break;
                default:break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_LOGIN) {
            if (resultCode != RESULT_OK)
                finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!logon) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, REQUEST_LOGIN);
//            startActivity(intent);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        findViews();
    }

    private void findViews() {
//        functions = getResources().getStringArray(R.array.functions);

        setupFunctions();

        //Recycler
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
        // Adapter
//        FunctionAdapter adapter = new FunctionAdapter(MainActivity.this);
        IconAdapter iconAdapter = new IconAdapter();
        recyclerView.setAdapter(iconAdapter);
    }

    private void setupFunctions() {
        functions = new ArrayList<>();
        String[] funcs = getResources().getStringArray(R.array.functions);
        functions.add(new Function(funcs[0], R.drawable.func_transaction));
        functions.add(new Function(funcs[1], R.drawable.func_balance));
        functions.add(new Function(funcs[2], R.drawable.func_finance));
        functions.add(new Function(funcs[3], R.drawable.func_contacts));
        functions.add(new Function(funcs[4], R.drawable.func_exit));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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

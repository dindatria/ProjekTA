package com.dindatria.shetpi.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.dindatria.shetpi.API.ApiClient;
import com.dindatria.shetpi.Model.DataSapiModel;
import com.dindatria.shetpi.Model.GetDataSapi;
import com.dindatria.shetpi.R;
import com.dindatria.shetpi.adapter.AdapterDataSapi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements AdapterDataSapi.OnDelete2{
private RecyclerView rv_list_datasapi;
private Button btnInput;
private AdapterDataSapi adapterDataSapi;
private List<DataSapiModel> dataSapiModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getWindow().setFlags(WindowManager.LayoutParams.
                FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        rv_list_datasapi=findViewById(R.id.list_datasapi);
        getListDataSapi();
//        buildRecyclerView();
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
//        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
//        inflater.inflate(R.menu.search_menu, menu);

        // below line is to get our menu item.
//        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        // getting search view of our item.
//        SearchView searchView = (SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // inside on query text change method we are
//                // calling a method to filter our recycler view.
//                filter(newText);
//                return false;
//            }
//        });
//        return true;
//    }

//    private void filter(String text) {
//        // creating a new array list to filter our data.
//        ArrayList<CourseModal> filteredlist = new ArrayList<>();
//
//        // running a for loop to compare elements.
//        for (CourseModal item : courseModalArrayList) {
//            // checking if the entered string matched with any item of our recycler view.
//            if (item.getCourseName().toLowerCase().contains(text.toLowerCase())) {
//                // if the item is matched we are
//                // adding it to our filtered list.
//                filteredlist.add(item);
//            }
//        }
//        if (filteredlist.isEmpty()) {
//            // if no item is added in filtered list we are
//            // displaying a toast message as no data found.
//            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
//        } else {
//            // at last we are passing that filtered
//            // list to our adapter class.
//            adapter.filterList(filteredlist);
//        }
//    }
//    private void buildRecyclerView() {
//        // below line we are creating a new array list
//        dataSapiModelList = new ArrayList<>();
//
//        // below line is to add data to our array list.
//        dataSapiModelList.add(new AdapterDataSapi(getListDataSapi();));
//        courseModalArrayList.add(new CourseModal("JAVA", "JAVA Self Paced Course"));
//        courseModalArrayList.add(new CourseModal("C++", "C++ Self Paced Course"));
//        courseModalArrayList.add(new CourseModal("Python", "Python Self Paced Course"));
//        courseModalArrayList.add(new CourseModal("Fork CPP", "Fork CPP Self Paced Course"));
//
//        // initializing our adapter class.
//        adapterDataSapi = new AdapterDataSapi(DataSapiModel, DetailActivity.this);
//
//        // adding layout manager to our recycler view.
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        courseRV.setHasFixedSize(true);
//
//        // setting layout manager
//        // to our recycler view.
//        courseRV.setLayoutManager(manager);
//
//        // setting adapter to
//        // our recycler view.
//        courseRV.setAdapter(adapter);
//    }

    private void getListDataSapi() {
        Call<GetDataSapi> getDataSapiCall = ApiClient.getApiInterface().getDataSapi();
        getDataSapiCall.enqueue(new Callback<GetDataSapi>() {
            @Override
            public void onResponse(Call<GetDataSapi> call, Response<GetDataSapi> response) {
                if (response.isSuccessful()){
                    if (!response.body().isError()){
                        AdapterDataSapi adapterDataSapi = new AdapterDataSapi(response.body().getDataSapiModels(),DetailActivity.this, DetailActivity.this);
                        adapterDataSapi.notifyDataSetChanged();
                        rv_list_datasapi.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        rv_list_datasapi.setHasFixedSize(true);
                        rv_list_datasapi.setAdapter(adapterDataSapi);

                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Tidak Ada Respon Server", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<GetDataSapi> call, Throwable t) {
                Log.e("error",t.getMessage());
                Toast.makeText(getApplicationContext(),"Periksa Koneksi Internet Anda", Toast.LENGTH_LONG).show();
            }

        });
    }


    @Override
    public void onLoaddelete2(boolean is_delete, String message) {
        if (is_delete){
            getListDataSapi();
        }
        Toast.makeText(DetailActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
package sg.toru.rxjavatest.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import sg.toru.rxjavatest.R;
import sg.toru.rxjavatest.data.DummyModel;
import sg.toru.rxjavatest.ui.adapter.RxRecyclerAdapter;

public class RxRecycleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RxRecyclerAdapter adapter;
    private ArrayList<DummyModel> dummyModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_recycle);

        init();
    }
    private int index = 4;
    private void init(){
        dummyModels = new ArrayList<>();
        recyclerView = findViewById(R.id.rcv_rx);
        adapter = new RxRecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        for(int i = 0; i < 5; i++){
            DummyModel model = new DummyModel();
            model.setType("TYPE");
            model.setIndex(String.valueOf(i));
            dummyModels.add(model);
        }
        adapter.submitList(dummyModels);


        findViewById(R.id.btn_add).setOnClickListener(v -> {
            ArrayList<DummyModel> models = new ArrayList<>();
            for(int i = 6; i < 10; i++){
                DummyModel model = new DummyModel();
                model.setType("ADDED");
                model.setIndex(String.valueOf(++index));
                models.add(model);
            }
//            models.addAll(0, dummyModels);
//
//            DummyModel model = new DummyModel();
//            model.setType("ADDED");
//            model.setIndex(String.valueOf(++index));
//            models.add(model);
//
//            dummyModels.add(model);
            adapter.submitList(models);
//            recyclerView.scrollToPosition(adapter.getItemCount()-1);
        });
    }
}
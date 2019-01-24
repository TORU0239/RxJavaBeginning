package sg.toru.rxjavatest.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import sg.toru.rxjavatest.R;

public class RxRecycleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_recycle);

        init();
    }

    private void init(){
        recyclerView = findViewById(R.id.rcv_rx);
    }
}
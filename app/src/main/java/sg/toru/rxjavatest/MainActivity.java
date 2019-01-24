package sg.toru.rxjavatest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText searchEdit;
    private TextView resultTxt;

    private CompositeDisposable disposableArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        disposableArrayList = new CompositeDisposable();
        searchEdit = findViewById(R.id.ed_search);
        resultTxt = findViewById(R.id.txt_opr);

        PublishSubject<String> publishSubject = PublishSubject.create();
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                publishSubject.onNext(s.toString());
                Log.w("MainActivity", "original s::"  + s.toString());
            }
        });

        Disposable publishDisposable = publishSubject
                .filter(s -> s.length() != 0)
                .debounce(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.w("MainActivity", "result s::"  + s);
                    resultTxt.setText(s);
                });
        disposableArrayList.add(publishDisposable);

        findViewById(R.id.btn_run).setOnClickListener(v ->{
            Disposable run = Observable.just(resultTxt.getText())
                    .map(CharSequence::toString)
                    .flatMap((Function<String, ObservableSource<String>>) s -> Observable.just(s.concat("씨발")).delay(500, TimeUnit.MILLISECONDS))
                    .subscribe(s -> Log.e("MainActivity", "s :: " + s));
            disposableArrayList.add(run);
        });

        findViewById(R.id.btn_new_activity).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RxRecycleActivity.class));
        });
    }

    @Override
    protected void onDestroy() {
        if(!disposableArrayList.isDisposed()){
            disposableArrayList.clear();
        }
        super.onDestroy();
    }
}
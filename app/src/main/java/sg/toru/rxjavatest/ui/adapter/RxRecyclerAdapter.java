package sg.toru.rxjavatest.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sg.toru.rxjavatest.R;
import sg.toru.rxjavatest.data.DummyModel;

public class RxRecyclerAdapter extends ListAdapter<DummyModel, RxRecyclerAdapter.RxRecyclerVH> {

    protected RxRecyclerAdapter(@NonNull DiffUtil.ItemCallback<DummyModel> diffCallback) {
        super(diffCallback);
    }

    protected RxRecyclerAdapter(@NonNull AsyncDifferConfig<DummyModel> config) {
        super(config);
    }

    @NonNull
    @Override
    public RxRecyclerVH onCreateViewHolder(@NonNull ViewGroup container, int viewType) {
        View itemView = LayoutInflater.from(container.getContext()).inflate(R.layout.adapter_simple, container, false);
        return new RxRecyclerVH(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull RxRecyclerVH rxRecyclerVH, int position) {
        rxRecyclerVH.bindItem();
    }

    static class RxRecyclerVH extends RecyclerView.ViewHolder{
        public RxRecyclerVH(@NonNull View itemView) {
            super(itemView);
        }

        protected void bindItem(){

        }
    }
}
package sg.toru.rxjavatest.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sg.toru.rxjavatest.R;
import sg.toru.rxjavatest.data.DummyModel;

public class RxRecyclerAdapter extends ListAdapter<DummyModel, RxRecyclerAdapter.RxRecyclerVH> {

    public RxRecyclerAdapter() {
        super(new ListItemCallback());
    }

    @NonNull
    @Override
    public RxRecyclerVH onCreateViewHolder(@NonNull ViewGroup container, int viewType) {
        View itemView = LayoutInflater.from(container.getContext()).inflate(R.layout.adapter_simple, container, false);
        return new RxRecyclerVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RxRecyclerVH rxRecyclerVH, int position) {
        rxRecyclerVH.bindItem(getItem(position));
    }

    static class RxRecyclerVH extends RecyclerView.ViewHolder{
        private TextView index;
        private TextView type;
        public RxRecyclerVH(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.txt_sort);
            index = itemView.findViewById(R.id.txt_item);
        }

        protected void bindItem(DummyModel model){
            type.setText(model.getType());
            index.setText(model.getIndex());
        }
    }
}

class ListItemCallback extends DiffUtil.ItemCallback<DummyModel>{

    @Override
    public boolean areItemsTheSame(@NonNull DummyModel oldItem, @NonNull DummyModel newItem) {
        return (oldItem == newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull DummyModel oldItem, @NonNull DummyModel newItem) {
        return (oldItem.getIndex().equals(newItem.getIndex()));
    }
}
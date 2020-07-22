package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.recyclerview.Data.IMAGE_PATH;
import static com.example.recyclerview.Data.TYPE_HEADER;
import static com.example.recyclerview.Data.TYPE_ITEM;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyAdapter myAdapter;

    private List<Data> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        initData();
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
    }

    private void initData(){
        dataList = new ArrayList<>();
        dataList.add(new Data(TYPE_HEADER,"This is header", null, 0,null));
        for(int i=1; i<=5; i++){
            dataList.add(new Data(TYPE_ITEM,"title"+i, "desp"+1, i,IMAGE_PATH+i));
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        class ItemViewHolder extends RecyclerView.ViewHolder{
            private View itemView;
            private TextView title,description,number;
            private ImageView avatar;
            public ItemViewHolder(@NonNull View itemView) {
                super(itemView);
                itemView = itemView;
                title = itemView.findViewById(R.id.title);
                description = itemView.findViewById(R.id.description);
                number = itemView.findViewById(R.id.number);
                avatar = itemView.findViewById(R.id.avatar);
            }
        }

        class HeaderViewHolder extends RecyclerView.ViewHolder{
            private View headerView;
            private TextView header;
            public HeaderViewHolder(@NonNull View itemView) {
                super(itemView);
                headerView = itemView;
                header = itemView.findViewById(R.id.header);
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @NonNull int viewType) {
            switch (viewType){
                case TYPE_ITEM:
                    View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
                    ItemViewHolder itemViewHolder = new ItemViewHolder(inflate);
                    return itemViewHolder;
                case TYPE_HEADER:
                    View inflate1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);
                    HeaderViewHolder headerViewHolder = new HeaderViewHolder(inflate1);
                    return headerViewHolder;
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            Data data = dataList.get(position);
            if(Objects.nonNull(data)){
                switch (data.type){
                    case TYPE_ITEM:
                        ((ItemViewHolder) holder).title.setText(data.title);
                        ((ItemViewHolder) holder).description.setText(data.description);
                        ((ItemViewHolder) holder).number.setText(String.valueOf(data.number));
                        Glide.with(getBaseContext())
                                .load(data.avatar)
                                .into(((ItemViewHolder) holder).avatar);
                        break;
                    case TYPE_HEADER:
                        ((HeaderViewHolder) holder).header.setText(data.title);
                        break;
                }
            }
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        @Override
        public int getItemViewType(int position) {
            return dataList.get(position).type;
        }
    }
}
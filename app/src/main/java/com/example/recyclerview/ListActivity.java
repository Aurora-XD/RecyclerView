package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        dataList.add(new Data("title1", "desp1", 1));
        dataList.add(new Data("title2", "desp2", 2));
        dataList.add(new Data("title3", "desp3", 3));
        dataList.add(new Data("title4", "desp4", 4));
        dataList.add(new Data("title5", "desp5", 5));
        dataList.add(new Data("title6", "desp6", 6));
        dataList.add(new Data("title7", "desp7", 7));
        dataList.add(new Data("title8", "desp8", 8));
        dataList.add(new Data("title9", "desp9", 9));
        dataList.add(new Data("title10", "desp10", 10));
        dataList.add(new Data("title11", "desp11", 11));
        dataList.add(new Data("title12", "desp12", 12));
        dataList.add(new Data("title13", "desp13", 13));
        dataList.add(new Data("title14", "desp14", 14));
        dataList.add(new Data("title15", "desp15", 15));
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        class MyViewHolder extends RecyclerView.ViewHolder{
            private View itemView;
            private TextView title,description,number;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                itemView = itemView;
                title = itemView.findViewById(R.id.title);
                description = itemView.findViewById(R.id.description);
                number = itemView.findViewById(R.id.number);
            }
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(inflate);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.title.setText(dataList.get(position).title);
            holder.description.setText(dataList.get(position).description);
            holder.number.setText(String.valueOf(dataList.get(position).number));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }
}
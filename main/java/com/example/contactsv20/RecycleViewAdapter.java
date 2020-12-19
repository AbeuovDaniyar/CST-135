package com.example.contactsv20;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> implements Filterable{
    //define
    private List<PersonContact> contactlist;
    private List<PersonContact> contactlistFilterd;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_contactPhoto;
        TextView tv_contactName, tv_contactLastName, tv_contactPhoneNumber;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_contactPhoto = itemView.findViewById(R.id.iv_contactPhoto);
            tv_contactName = itemView.findViewById(R.id.tv_contactName);
            tv_contactLastName = itemView.findViewById(R.id.tv_contactLastName);
            tv_contactPhoneNumber = itemView.findViewById(R.id.tv_contactPhoneNumber);
            parentLayout = itemView.findViewById(R.id.oneLineContactLayout);
        }
    }

    public RecycleViewAdapter(List<PersonContact> contactlistVal, Context context) {
        this.contactlist = new ArrayList<>(contactlistVal);
        this.contactlistFilterd = new ArrayList<>(contactlistVal);
        this.context = context;
    }
    public RecycleViewAdapter(){}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_contact, parent, false);
        //MyViewHolder holder = new MyViewHolder(view);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_contactName.setText(contactlist.get(position).getFirstName());
        holder.tv_contactLastName.setText(contactlist.get(position).getLastName());
        holder.tv_contactPhoneNumber.setText(contactlist.get(position).getPhoneNumber());
        Glide.with(this.context).load("https://upload.wikimedia.org/wikipedia/en/thumb/9/98/John_Wick_TeaserPoster.jpg/220px-John_Wick_TeaserPoster.jpg").into(holder.iv_contactPhoto);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send the control to the editoneitem
                Intent intent = new Intent(context, AddEditContact.class);
                intent.putExtra("id", contactlist.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactlist.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            //perform filter by search string and return filtered list array
            List<PersonContact> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(contactlistFilterd);
            }else {
                String searchStr = constraint.toString().toLowerCase().trim();
                for(PersonContact item: contactlistFilterd){
                    if(item.getFirstName().toLowerCase().contains(searchStr) || item.getLastName().toLowerCase().contains(searchStr)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            //clearing base list and populating it with filtered list
            ContactList contactList = new ContactList();
            contactlist = contactList.getContactlist();
            contactlist.clear();
            contactlist.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}

package br.edu.unicsul.agendatelefonica.ui.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.unicsul.agendatelefonica.R;
import br.edu.unicsul.agendatelefonica.models.Developer;

public class DevAdapter extends RecyclerView.Adapter<DevAdapter.DevAdapterViewHolder> {

    private List<Developer> mDevs;


    public DevAdapter(@NonNull List<Developer> devs) {
        mDevs = devs;

    }

    class DevAdapterViewHolder extends RecyclerView.ViewHolder{
        public final TextView mNameTextView;
        public final TextView mRgmTextView;
        public final ImageView mPhotoImagrView;

        DevAdapterViewHolder(View view) {
            super(view);
            mNameTextView = view.findViewById(R.id.tv_name);
            mRgmTextView = view.findViewById(R.id.tv_rgm);
            mPhotoImagrView = view.findViewById(R.id.iv_avatar);


        }

    }

    @NonNull
    @Override
    public DevAdapter.DevAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.layout_person, parent, false);
        return new DevAdapter.DevAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DevAdapter.DevAdapterViewHolder holder, int position) {
        Developer dev = mDevs.get(position);

        holder.mPhotoImagrView.setImageResource(dev.getAvatarId());
        holder.mNameTextView.setText(dev.getName());
        holder.mRgmTextView.setText(dev.getRgm());
    }

    @Override
    public int getItemCount() {
        return mDevs.size();
    }


    public void setContacts(@NonNull List<Developer> contacts) {
        mDevs.clear();
        mDevs.addAll(contacts);
        notifyDataSetChanged();
    }


}
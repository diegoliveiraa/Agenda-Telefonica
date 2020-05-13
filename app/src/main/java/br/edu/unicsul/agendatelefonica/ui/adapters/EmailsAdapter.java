package br.edu.unicsul.agendatelefonica.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.edu.unicsul.agendatelefonica.R;
import br.edu.unicsul.agendatelefonica.models.Contact;

public class EmailsAdapter extends RecyclerView.Adapter<EmailsAdapter.ListAdapterViewHolder> {

    private List<Contact> mContacts;
    private final ItemClickListener mClickListener;

    public interface ItemClickListener {
        /**
         * @param selectedContact Contact that was clicked
         */
        void onClick(Contact selectedContact);
    }

    public EmailsAdapter(ItemClickListener listener) {
        mClickListener = listener;
        mContacts = new ArrayList<>();
    }

    public EmailsAdapter(@NonNull List<Contact> contacts, ItemClickListener listener) {
        mContacts = contacts;
        mClickListener = listener;

    }

    class ListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView mItemTextView;

        ListAdapterViewHolder(View view) {
            super(view);
            mItemTextView = view.findViewById(R.id.tv_text);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Contact selectedContact = mContacts.get(getAdapterPosition());
            mClickListener.onClick(selectedContact);
        }
    }

    @NonNull
    @Override
    public ListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.layout_text_item, parent, false);
        return new ListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapterViewHolder holder, int position) {
        Contact contact = mContacts.get(position);

        String label = "Nome: " + contact.getNomecontato() + " \nEmail: " + contact.getEmail();

        holder.mItemTextView.setText(label);
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }


    public void setContacts(@NonNull List<Contact> contacts) {
        mContacts.clear();
        mContacts.addAll(contacts);
        notifyDataSetChanged();
    }


}

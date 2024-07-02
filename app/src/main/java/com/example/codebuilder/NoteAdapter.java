    package com.example.codebuilder;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.DiffUtil;
    import androidx.recyclerview.widget.ListAdapter;
    import androidx.recyclerview.widget.RecyclerView;

    import com.google.android.material.card.MaterialCardView;

    public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {

        private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
            @Override
            public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
                return oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getDescription().equals(newItem.getDescription());
            }
        };
        private onItemClickListener listener;

        public NoteAdapter() {
            super(DIFF_CALLBACK);
        }

        @NonNull
        @Override
        public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.note_item, parent, false);

            return new NoteHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
            Note currentNode = getItem(position);
            holder.textViewTitle.setText(currentNode.getTitle());
            holder.textViewDescription.setText(currentNode.getDescription());
            holder.textViewDateTime.setText(currentNode.getDate());


        }

        public Note getNoteAt(int position) {
            return getItem(position);
        }

        public void setOnItemClickListener(onItemClickListener listener) {
            this.listener = listener;
        }

        public interface onItemClickListener {
            void onItemClick(Note note);
        }

        class NoteHolder extends RecyclerView.ViewHolder {
            private TextView textViewTitle;
            private TextView textViewDescription;
            private TextView dateTime;
            private TextView textViewDateTime;
            private MaterialCardView cardView;

            public NoteHolder(@NonNull final View itemView) {
                super(itemView);
                textViewTitle = itemView.findViewById(R.id.text_view_title);
                textViewDescription = itemView.findViewById(R.id.text_view_description);
                dateTime = itemView.findViewById(R.id.text_view_date_time);
                cardView = itemView.findViewById(R.id.cardView);
                textViewDateTime = itemView.findViewById(R.id.text_view_date_time);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();
                        if (listener != null && position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(getItem(position));
                        }
                    }
                });
            }
        }

    }

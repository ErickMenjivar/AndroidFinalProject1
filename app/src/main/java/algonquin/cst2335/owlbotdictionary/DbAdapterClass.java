package algonquin.cst2335.owlbotdictionary;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * An adapter class for the database.
 */
public class DbAdapterClass extends RecyclerView.Adapter<DbAdapterClass.MyViewHolder>{

    private Context mContext;
    private List<WordModelDbClass> mDataW;
    private List<WordModelDbClass> mDataP;
    private List<WordModelDbClass> mDataD;
    private SelectListener listener;

    /**
     * The overloaded constructor for the DbAdapterClass.
     * @param mContext The context.
     * @param mDataW A data list of word model db classes.
     * @param mDataP A data list of word model db classes.
     * @param mDataD A data list of word model db classes.
     * @param listener A selection listener.
     */
    public DbAdapterClass(Context mContext, List<WordModelDbClass> mDataW,List<WordModelDbClass> mDataP,List<WordModelDbClass> mDataD, SelectListener listener) {
        this.mContext = mContext;
        this.mDataW = mDataW;
        this.mDataP = mDataP;
        this.mDataD = mDataD;
        this.listener =listener;
    }

    /**
     * A method to run when the ViewHolder is created.
     * @param parent The view group.
     * @param viewType The view type.
     * @return An instance of the ViewHolder class.
     */
    @NonNull
    @Override
    public DbAdapterClass.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v =inflater.inflate(R.layout.db_items, parent, false);
        return new DbAdapterClass.MyViewHolder(v, listener);
    }

    /**
     * A method to launch when the view holder is bound.
     * @param holder The MyViewHolder instance.
     * @param position The position.
     */
    @Override
    public void onBindViewHolder(@NonNull DbAdapterClass.MyViewHolder holder, int position) {
        holder.definition.setText(mDataD.get(position).getDefinition());
        holder.word.setText(mDataW.get(position).getWord());
        holder.pro.setText(mDataP.get(position).getPronunciation());
    }

    /**
     * A method to get the number of items.
     * @return The number of items.
     */
    @Override
    public int getItemCount() {
        return  mDataD.size();
    }

    /**
     * A class for the MyViewHolder class.
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView definition;
        TextView word;
        TextView pro;
        SelectListener itemListener;

        /**
         * The overloaded constructor for the MyViewHolder class.
         * @param itemView The view.
         * @param itemListener The selection listener.
         */
        public MyViewHolder(@NonNull View itemView, SelectListener itemListener){
            super(itemView);
            definition =  itemView.findViewById(R.id.definition_txt_db);
            word = itemView.findViewById(R.id.word_db);
            pro = itemView.findViewById(R.id.pronunciation_db);
            this.itemListener = itemListener;
            itemView.setOnClickListener(this);
        }

        /**
         * An on click listener.
         * @param view The view.
         */
        @Override
        public void onClick(View view) {
            itemListener.onItemClicked(getBindingAdapterPosition());
        }
    }
}

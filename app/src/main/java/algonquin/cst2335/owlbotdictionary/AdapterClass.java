package algonquin.cst2335.owlbotdictionary;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;


import java.util.List;

/**
 * An adapter class for inflating a recycler view.
 */
public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {

    private Context mContext;
    private List<WordModelClass> mData;
    private SelectListener listener;

    /**
     * The constructor for the adapter class.
     * @param mContext The context
     * @param mData The data list
     * @param listener The select listener
     */
    public AdapterClass(Context mContext, List<WordModelClass> mData, SelectListener listener) {
        this.mContext = mContext;
        this.mData = mData;
        this.listener =listener;
    }

    /**
     * A method to launch when the view holder is created.
     * @param parent The parent view group
     * @param viewType The view type
     * @return An instance of MyViewHolder.
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v =inflater.inflate(R.layout.itemr_word, parent, false);
        return new MyViewHolder(v, listener);
    }

    /**
     * A method to launch when the view holder is bound.
     * @param holder The MyViewHolder instance.
     * @param position The position.
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position) {
        holder.definition.setText(mData.get(position).getDefinition());
    }

    /**
     * A method to get the number of items.
     * @return The number of items.
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * A class representing the MyViewHolder.
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView definition;
        SelectListener itemListener;

        /**
         * The constructor for the MyViewHolder class.
         * @param itemView The view.
         * @param itemListener The select listener.
         */
        public MyViewHolder(@NonNull View itemView, SelectListener itemListener){
            super(itemView);
            definition =  itemView.findViewById(R.id.definition_txt);
            this.itemListener = itemListener;
            itemView.setOnClickListener(this);
        }

        /**
         * An onClick method for the MyViewHolder class.
         * @param view The view.
         */
        @Override
        public void onClick(View view) {
            itemListener.onItemClicked(getBindingAdapterPosition());
        }
    }
}

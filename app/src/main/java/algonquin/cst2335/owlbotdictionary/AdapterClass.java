package algonquin.cst2335.owlbotdictionary;


import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.List;
public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {

    private Context mContext;
    private List<WordModelClass> mData;
    private SelectListener listener;

    public AdapterClass(Context mContext, List<WordModelClass> mData, SelectListener listener) {
        this.mContext = mContext;
        this.mData = mData;
        this.listener =listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v =inflater.inflate(R.layout.itemr_word, parent, false);
        return new MyViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position) {
        holder.definition.setText(mData.get(position).getDefinition());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView definition;
        SelectListener itemListener;
        public MyViewHolder(@NonNull View itemView, SelectListener itemListener){
            super(itemView);
            definition =  itemView.findViewById(R.id.definition_txt);
            this.itemListener = itemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemListener.onItemClicked(getBindingAdapterPosition());
        }
    }
}

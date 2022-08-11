package algonquin.cst2335.owlbotdictionary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DbAdapterClass extends RecyclerView.Adapter<DbAdapterClass.MyViewHolder>{

    private Context mContext;
    private List<WordModelDbClass> mDataW;
    private List<WordModelDbClass> mDataP;
    private List<WordModelDbClass> mDataD;
    private SelectListener listener;

    public DbAdapterClass(Context mContext, List<WordModelDbClass> mDataW,List<WordModelDbClass> mDataP,List<WordModelDbClass> mDataD, SelectListener listener) {
        this.mContext = mContext;
        this.mDataW = mDataW;
        this.mDataP = mDataP;
        this.mDataD = mDataD;
        this.listener =listener;
    }

    @NonNull
    @Override
    public DbAdapterClass.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v =inflater.inflate(R.layout.db_items, parent, false);
        return new DbAdapterClass.MyViewHolder(v, listener);
    }


    @Override
    public void onBindViewHolder(@NonNull DbAdapterClass.MyViewHolder holder, int position) {
        holder.definition.setText(mDataD.get(position).getDefinition());
        holder.word.setText(mDataW.get(position).getWord());
        holder.pro.setText(mDataP.get(position).getPronunciation());
    }

    @Override
    public int getItemCount() {
        return  mDataD.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView definition;
        TextView word;
        TextView pro;
        SelectListener itemListener;
        public MyViewHolder(@NonNull View itemView, SelectListener itemListener){
            super(itemView);
            definition =  itemView.findViewById(R.id.definition_txt_db);
            word = itemView.findViewById(R.id.word_db);
            pro = itemView.findViewById(R.id.pronunciation_db);
            this.itemListener = itemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemListener.onItemClicked(getBindingAdapterPosition());
        }
    }
}

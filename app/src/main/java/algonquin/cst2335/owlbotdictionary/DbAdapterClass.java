package algonquin.cst2335.owlbotdictionary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DbAdapterClass extends RecyclerView.Adapter<DbAdapterClass.MyViewHolder>{

    Context context;
    ArrayList wordListDbWord,wordListDbPro,wordListDbDef;


    DbAdapterClass(Context context, ArrayList wordListDbWord, ArrayList wordListDbDef, ArrayList wordListDbPro){
        this.context =context;
        this.wordListDbDef = wordListDbDef;
        this.wordListDbPro = wordListDbPro;
        this.wordListDbWord =wordListDbWord;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.db_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.word_db.setText(String.valueOf(wordListDbWord.get(10)));
        holder.pronunciation_db.setText(String.valueOf(wordListDbPro.get(10)));
        holder.definition_txt_db.setText(String.valueOf(wordListDbDef.get(10)));

    }

    @Override
    public int getItemCount() {
        return wordListDbWord.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView word_db, pronunciation_db,definition_txt_db;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            word_db = itemView.findViewById(R.id.word_db);
            pronunciation_db = itemView.findViewById(R.id.pronunciation_db);
            definition_txt_db = itemView.findViewById(R.id.definition_txt_db);
        }
    }
}

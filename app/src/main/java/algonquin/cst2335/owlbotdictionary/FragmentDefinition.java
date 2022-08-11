package algonquin.cst2335.owlbotdictionary;

import android.content.Context;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentDefinition extends Fragment {

    private String definition;
    private String word;
    private String pronunciation;
    private TextView definitionText;
    SelectListener selectListener;
    DataBase db_fav;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_definition, container, false);
        Button close_btn = view.findViewById(R.id.close_fragment);
        Button fav_btn = view.findViewById(R.id.add_favorites);
        TextView wordToShow = view.findViewById(R.id.searched_word);
        definitionText = view.findViewById(R.id.definition_String_viewer);


        Bundle data = getArguments();

        if (data!=null){
            definition = data.getString("Definition");
            word = data.getString("Word");
            pronunciation = data.getString("Pronunciation");
        }
        wordToShow.setText(word);
        definitionText.setText(definition);

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFragment(new FragmentDefinition());
            }
        });


        fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wordCheck = word;
                String definitionCheck = definition;
                String pronunciationCheck = pronunciation;
                selectListener.saveVarValues(word,definition,pronunciation);
                //db_fav.insert(word, definition, pronunciation);
                Toast.makeText(getContext(),"Added to favorites", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SelectListener){
            selectListener = (SelectListener)context;
        }else{
            throw new RuntimeException(context.toString() + "Must implement interface");
        }
    }

    private void closeFragment(FragmentDefinition fragmentDefinition) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentDefinition fragment = (FragmentDefinition) fragmentManager.findFragmentById(R.id.fragment_frame);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();

    }
}
package algonquin.cst2335.owlbotdictionary;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.nio.charset.MalformedInputException;

/**
 * A class for the detail fragment.
 */
public class DetailFragment extends Fragment {

    private Bundle dataFromActivity;
    private String word, define, pronunciation;
    private AppCompatActivity parentActivity;
    private TextView tvWord, tvDefine, tvPronunciation;

    /**
     * The no-arg constructor for the detail fragment class.
     */
    public DetailFragment(){

    }

    /**
     * A method to call once the view is created.
     * @param inflater The layout inflater.
     * @param container The view group container.
     * @param savedInstanceState The bundle saved instance state.
     * @return An instance of the view.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dataFromActivity = getArguments();
        word = dataFromActivity.getString(MainSearch.WORD);
        define = dataFromActivity.getString(MainSearch.DEFINE);
        pronunciation = dataFromActivity.getString(MainSearch.PRONUNCIATION);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        tvWord = (TextView)view.findViewById(R.id.tvWord);
        tvWord.setText(word);

        tvDefine = (TextView)view.findViewById(R.id.tvDefinition);
        tvDefine.setText(define);

        tvPronunciation = (TextView)view.findViewById(R.id.tvPronunciation);
        tvPronunciation.setText(pronunciation);

        return view;
    }

    /**
     * A method that is called when attached to a view.
     * @param context The context.
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        parentActivity = (AppCompatActivity)context;
    }
}
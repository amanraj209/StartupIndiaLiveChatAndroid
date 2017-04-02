package com.example.android.startupindialivechat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.AsyncTask;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.startupindialivechat.model.ChatMessage;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ai.api.AIListener;
import ai.api.AIServiceException;
import ai.api.android.AIConfiguration;
import ai.api.android.AIDataService;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Result;

public class ChatActivity extends AppCompatActivity  {
    private Button voiceButton, textButton;
    private EditText editText;
    private TextView resultTextView;
    private AIService aiService;
    public static final String TAG = "ChatActivity";

    private ListView listView;
    private List<ChatMessage> chatMessages;
    private ArrayAdapter<ChatMessage> adapter;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private AIRequest aiRequest;
    private AIDataService aiDataService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        editText = (EditText) findViewById(R.id.et_message);
        voiceButton = (Button) findViewById(R.id.btn_voice);
        textButton = (Button) findViewById(R.id.btn_text);
        listView= (ListView) findViewById(R.id.messageListView);
        chatMessages=new ArrayList<>();

        adapter = new MessageAdapter(this, R.layout.item_chat_left, chatMessages);
        listView.setAdapter(adapter);


        final AIConfiguration textconfig = new AIConfiguration("3e9ac947953143df90c3c7fcebed6c62",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);

        aiDataService = new AIDataService(getApplicationContext(), textconfig);


        aiRequest = new AIRequest();

//
//        final AIConfiguration config;
//        config = new AIConfiguration("3e9ac947953143df90c3c7fcebed6c62",
//                AIConfiguration.SupportedLanguages.English,
//                AIConfiguration.RecognitionEngine.System);
//
//
//        aiService = AIService.getService(this, config);
//        aiService.setListener(this);

        voiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                aiService.startListening();
                promptSpeechInput();
            }
        });


//for text


        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query=editText.getText().toString();
               addChild(query);

            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    textButton.setEnabled(true);
                } else {
                    textButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

//    @Override
//    public void onResult(AIResponse response) {
//        final Result result = response.getResult();
//        final String speech = result.getFulfillment().getSpeech();
//        Log.i(TAG, "Speech: " + speech);
////        resultTextView.setText(speech);
//
//    }
//
//    @Override
//    public void onError(AIError error) {
//        resultTextView.setText(error.toString());
//    }
//
//    @Override
//    public void onAudioLevel(float level) {
//
//    }
//
//    @Override
//    public void onListeningStarted() {
//
//    }
//
//    @Override
//    public void onListeningCanceled() {
//
//    }
//
//    @Override
//    public void onListeningFinished() {
//
//    }
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS,2000);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS,2000);

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    addChild(result.get(0));
                }
                break;
            }

        }
    }
    protected void addChild(String query){
        ChatMessage chatMessage = new ChatMessage(query, true);
        chatMessages.add(chatMessage);
        adapter.notifyDataSetChanged();
        editText.setText("");

        aiRequest.setQuery(query);
        new AsyncTask<AIRequest, Void, AIResponse>() {
            @Override
            protected AIResponse doInBackground(AIRequest... requests) {
                final AIRequest request = requests[0];
                try {
                    final AIResponse response = aiDataService.request(aiRequest);
                    return response;
                } catch (AIServiceException e) {
                }
                return null;
            }

            @Override
            protected void onPostExecute(AIResponse aiResponse) {
                if (aiResponse != null) {
                    // process aiResponse here
                    final Result result = aiResponse.getResult();
                    final String speech = result.getFulfillment().getSpeech();
                    Log.d(TAG, "onPostExecute: " + speech);
                    ChatMessage chatMessage = new ChatMessage(speech, false);
                    chatMessages.add(chatMessage);
                    adapter.notifyDataSetChanged();
                }
            }
        }.execute(aiRequest);

    }
}

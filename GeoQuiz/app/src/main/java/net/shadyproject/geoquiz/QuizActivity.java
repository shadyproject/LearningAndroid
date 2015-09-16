package net.shadyproject.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";

    private Button _trueButton;
    private Button _falseButton;
    private Button _nextButton;

    private TextView _questionTextView;

    private Question[] _questions = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private int _currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");

        setContentView(R.layout.activity_quiz);

        _trueButton = (Button)findViewById(R.id.true_button);
        _trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        _falseButton = (Button)findViewById(R.id.false_button);
        _falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        _nextButton = (Button)findViewById(R.id.next_button);
        _nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                _currentQuestionIndex = (_currentQuestionIndex  + 1 ) % _questions.length;
                updateQuestion();
            }
        });

        _questionTextView = (TextView)findViewById(R.id.question_text_view);
        updateQuestion();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateQuestion() {
        int question = _questions[_currentQuestionIndex].getTextResourceId();
        _questionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = _questions[_currentQuestionIndex].getIsAnswerTrue();
        int msgResId = 0;
        if (userPressedTrue == answerIsTrue) {
            msgResId = R.string.correct_toast;
        } else {
            msgResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, msgResId, Toast.LENGTH_SHORT).show();
    }
}

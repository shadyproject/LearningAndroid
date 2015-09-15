package net.shadyproject.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button _trueButton;
    private Button _falseButton;
    private ImageButton _nextButton;
    private ImageButton _prevButton;

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

        _nextButton = (ImageButton)findViewById(R.id.next_button);
        _nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showNextQuestion();
            }
        });

        _prevButton = (ImageButton)findViewById(R.id.prev_button);
        _prevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showPreviousQuestion();
            }
        });

        _questionTextView = (TextView)findViewById(R.id.question_text_view);
        _questionTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showNextQuestion();
            }
        });
        updateQuestion();
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

    private void showNextQuestion() {
        incrementQuestionIndex();
        updateQuestion();
    }

    private void showPreviousQuestion() {
        decrementQuestionIndex();
        updateQuestion();
    }

    private void incrementQuestionIndex() {
        _currentQuestionIndex = (_currentQuestionIndex  + 1 ) % _questions.length;
    }

    private void decrementQuestionIndex() {
        int newIndex = _currentQuestionIndex - 1;
        _currentQuestionIndex = (newIndex < 0 ? _questions.length - 1 : newIndex);
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

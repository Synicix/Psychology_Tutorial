package com.synicix.psychologytutorial;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Quiz extends ActionBarActivity {

    ArrayList<String> questions, answers, hints;
    ArrayList hintPics;
    TextView questionNum, questionTextView, hintText;
    Button submit;
    ImageButton next, previous, hint;
    EditText answerEditText;
    Toast toast;
    int index;
    MediaPlayer correct, incorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        getSupportActionBar().hide();


        defineWidgets();
        setOnClickListeners();
        setQuestions();
        setAnswers();
        setHints();
        setHintsPics();

        questionTextView.setText(questions.get(0));
        index = 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        toast.cancel();
    }

    public void defineWidgets()
    {
        questionNum = (TextView) findViewById(R.id.questionNumberTextView);
        questionTextView = (TextView) findViewById(R.id.questionTextView);

        next = (ImageButton) findViewById(R.id.nextButton);
        previous = (ImageButton) findViewById(R.id.previousButton);
        hint = (ImageButton) findViewById(R.id.hintButton);
        submit = (Button) findViewById(R.id.submitButton);

        answerEditText = (EditText) findViewById(R.id.answerEditText);

        questions = new ArrayList<String>();
        answers = new ArrayList<String>();
        hints = new ArrayList<String>();
        hintPics = new ArrayList();

        toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);

        correct = MediaPlayer.create(this,R.raw.correct);
        incorrect = MediaPlayer.create(this,R.raw.incorrect);
    }

    public void setOnClickListeners()
    {
        next.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0)
            {
                if(index == questions.size() - 1)
                {
                    index = 0;
                    questionNum.setText("Question " + (index + 1));
                    questionTextView.setText(questions.get(index));

                }
                else
                {
                    index++;
                    questionNum.setText("Question " + (index + 1));
                    questionTextView.setText(questions.get(index));
                }
                toast.cancel();
                answerEditText.setText("");
            }
        });

        previous.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0)
            {
                if(index == 0)
                {
                    index = questions.size() - 1;
                    questionNum.setText("Question " + (index + 1));
                    questionTextView.setText(questions.get(index));

                }
                else
                {
                    index--;
                    questionNum.setText("Question " + (index + 1));
                    questionTextView.setText(questions.get(index));
                }
                toast.cancel();
                answerEditText.setText("");
            }
        });

        hint.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                toast = Toast.makeText(getApplicationContext(), hints.get(index), Toast.LENGTH_LONG);
                toast.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                if(answerEditText.getText().toString().equals(answers.get(index)))
                {
                    toast = Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT);
                    toast.show();
                    correct.start();
                }
                else
                {
                    toast = Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT);
                    toast.show();
                    incorrect.start();
                }

            }
        });

    }

    public void setQuestions()
    {
        questions.add("Who is considered to be the founder of psychology?");
        questions.add("What year was psychology first established? ");
        questions.add("Where was psychology founded? (City, Country))");
        questions.add("What are the two original schools of psychology");
        questions.add("What perspective did Abraham Maslow belongs too?");
        questions.add("What psychological perspective focuses on observable behavior?)");
        questions.add("Which perspective is the newest in terms of time?");
        questions.add("Sigmund Freud is most associated with what perspective of psychology?");
        questions.add("B.F. Skinner is most associated with what perspective of psychology?");
        questions.add("John B. Watson is the founder of what psychology perspective?");
    }

    public void setAnswers()
    {
        answers.add("Wilhelm Wundt");
        answers.add("1879");
        answers.add("Leipzig, Germany");
        answers.add("Structuralism and Functionalism");
        answers.add("Humanism");
        answers.add("Evolutionary");
        answers.add("Humanism");
        answers.add("Behaviorism");
        answers.add("Psychodynamic");
        answers.add("Behaviorism");

    }

    public void setHints()
    {
        hints.add("His initials is W.W.");
        hints.add("The year is 18xx");
        hints.add("It is located in Europe");
        hints.add("S_______ and F________");
        hints.add("Human_____");
        hints.add("\"Observable Behavior\"");
        hints.add("\"Evolution\"");
        hints.add("Freud created psychoanalysis");
        hints.add("B.F. Skinner focused heavily on subjects' behaviors");
        hints.add("Waston focus extensively on behavior");
    }

    public void setHintsPics()
    {

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
}

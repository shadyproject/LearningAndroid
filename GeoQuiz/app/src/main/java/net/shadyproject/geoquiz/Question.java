package net.shadyproject.geoquiz;

/**
 * Created by csmartin on 9/6/15.
 */
public class Question {
    private int _textResourceId;
    private boolean _isAnswerTrue;

    public Question(int resourceId, boolean isAnswerTrue) {
        _textResourceId = resourceId;
        _isAnswerTrue = isAnswerTrue;
    }

    public int getTextResourceId() {
        return _textResourceId;
    }

    public void setTextResourceId(int id) {
        _textResourceId = id;
    }

    public boolean getIsAnswerTrue() {
        return _isAnswerTrue;
    }

    public void setIsAnswerTrue(boolean truthValue) {
        _isAnswerTrue = truthValue;
    }
}

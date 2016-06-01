package cn.lb.utils.text;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Administrator on 2016-06-01.
 */
public class TextInputWatcher implements TextWatcher {
    private TextListener listener;
    private EditText eText;

    public TextInputWatcher(EditText eText) {
        this.eText = eText;
    }

    public TextInputWatcher setTextInputListener(TextListener listener){
        this.listener = listener;
        initListener();
        return this;
    }

    private void initListener(){
        if (null != eText && null != listener) {
            listener.txtChangeListener(eText, eText.getEditableText().length() == 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (null != listener) {
            listener.txtChangeListener(eText, null == s || s.length() < 1);
        }
    }
}

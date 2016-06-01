package cn.lb.utils.text;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Administrator on 2016-06-01.
 */
public class EditTextStatusBoundUtils implements TextListener{
    private static final String TAG = "EditTextStatusBound";
    private View mListenerView;
    private ArrayList<EditText> mObvEdits;
    private StatusChangeListner mListener;

    private boolean mCurrentStatus;

    public void setStatusListner(StatusChangeListner listner) {
        mListener = listner;
    }

    public EditTextStatusBoundUtils(@NonNull View view,EditText... edit) {
        this.mListenerView = view;
        mObvEdits = new ArrayList<>();
        if (null != edit) {
            initObvStatus();
        }
    }

    private void initObvStatus() {
        for (EditText et : mObvEdits) {
            addEditText(et);
        }
    }

    public void removeEditText(EditText edit) {
        //XXX
    }

    public void addEditText(EditText edit) {
        if (mObvEdits.indexOf(edit) >= 0) {
            return;
        }
        mObvEdits.add(edit);

        TextInputWatcher watcher = new TextInputWatcher(edit);
        watcher.setTextInputListener(this);

        edit.addTextChangedListener(watcher);
    }

    @Override
    public void txtChangeListener(EditText editText, boolean status) {
        Log.d(TAG, "edit "+editText.hashCode()+", status="+status);

        if (mCurrentStatus != status) {
            if (null != mListener) {
                mListener.statusChange(mListenerView, status);
            }
        }
        mCurrentStatus = hasEmptyEdit();
    }

    private boolean hasEmptyEdit() {
        for (EditText et:mObvEdits) {
            if (et.getEditableText().length() == 0) {
                return true;
            }
        }
        return false;
    }

    public static interface StatusChangeListner {
        void statusChange(View v, boolean unable);
    }
}

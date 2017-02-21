package dog.abcd.lib.alert;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;

import dog.abcd.lib.R;

/**
 * <b>Title</b><br>
 * Description
 *
 * @author Michael Lee<br>
 *         <b> create at </b>2017/1/17 下午 16:38
 * @Company RZQC
 * @Mender Michael Lee<br>
 * <b> change at </b>2017/1/17 下午 16:38
 */
public abstract class AstiAlertBase {

    private Context context;
    private String TAG;
    private AlertDialog dlg;
    private Window window;

    protected abstract View createView();

    protected abstract boolean cancelable();

    public void show(String TAG) {
        this.TAG = TAG;
        dismiss();
        try {
            dlg = new AlertDialog.Builder(context).create();
            dlg.show();
            window = dlg.getWindow();
            dlg.setCancelable(cancelable());
            window.setContentView(createView());
            window.setBackgroundDrawableResource(R.color.alpha);
            AstiAlertManager.getInstance().add(this);
        } catch (Exception e) {

        }
    }

    /**
     * 取消显示
     */
    public void dismiss() {
        try {
            AstiAlertManager.getInstance().remove(this);
            dlg.dismiss();
        } catch (Exception e) {

        }
    }

    /**
     * 取消显示
     */
    public void cancel() {
        try {
            AstiAlertManager.getInstance().remove(this);
            dlg.cancel();
        } catch (Exception e) {

        }
    }

    public String getTAG() {
        return TAG;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * 获取窗口
     *
     * @return
     */
    public Window getWindow() {
        return window;
    }

    /**
     * @return 获取弹出窗口
     */
    public AlertDialog getDialog() {
        return dlg;
    }

    /**
     * 设置取消的回调
     *
     * @param onCancelListener
     */
    public void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        dlg.setOnCancelListener(onCancelListener);
    }

    /**
     * 设置隐藏时的回调
     *
     * @param onDismissListener
     */
    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        dlg.setOnDismissListener(onDismissListener);
    }

}

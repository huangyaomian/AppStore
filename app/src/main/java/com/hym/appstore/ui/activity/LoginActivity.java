package com.hym.appstore.ui.activity;


import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.hym.appstore.R;
import com.hym.appstore.bean.LoginBean;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.presenter.LoginPresenter;
import com.hym.appstore.presenter.contract.LoginContract;
import com.jakewharton.rxbinding4.InitialValueObservable;
import com.jakewharton.rxbinding4.view.RxView;
import com.jakewharton.rxbinding4.widget.RxTextView;


import butterknife.BindView;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Unit;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.txt_phone_edit)
    EditText txtPhoneEdit;
    @BindView(R.id.view_phone_wrapper)
    TextInputLayout viewPhoneWrapper;
    @BindView(R.id.txt_password_edit)
    EditText txtPasswordEdit;
    @BindView(R.id.view_password_wrapper)
    TextInputLayout viewPasswordWrapper;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.txt_register)
    TextView txtRegister;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_login;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {
        InitialValueObservable<CharSequence> obPhone = RxTextView.textChanges(txtPhoneEdit);
        InitialValueObservable<CharSequence> obPassword = RxTextView.textChanges(txtPasswordEdit);

        InitialValueObservable.combineLatest(obPhone, obPassword, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence charSequence, CharSequence charSequence2) throws Throwable {
                return isPhoneValid(obPhone.toString()) && isPsdValid(obPassword.toString());
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Throwable {
                btnLogin.setEnabled(aBoolean);
            }
        });

        RxView.clicks(btnLogin).subscribe(new Consumer<Unit>() {
            @Override
            public void accept(Unit unit) throws Throwable {
                mPresenter.login(txtPhoneEdit.toString().trim(),txtPasswordEdit.toString().trim());
            }
        });
    }

    @Override
    public void initEvent() {

    }

    private boolean isPhoneValid(String phone){
        return phone.length() == 11 && phone.startsWith("1");
    }

    private boolean isPsdValid(String password){
        return password.length() >= 6;
    }

    @Override
    public void checkPhoneError() {
        viewPhoneWrapper.setError("手机号码输入错误");
    }

    @Override
    public void checkPhoneSuccess() {
        viewPhoneWrapper.setError("手机号码输入错误");
    }

    @Override
    public void loginSuccess(LoginBean bean) {
        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void dismissLoading() {

    }
}

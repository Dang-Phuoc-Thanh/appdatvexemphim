// Generated by view binder compiler. Do not edit!
package com.example.appxemphim.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appxemphim.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentThanhToanBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final ImageView back;

  @NonNull
  public final Button buttonThanhToan;

  @NonNull
  public final FrameLayout frameLayout;

  @NonNull
  public final TextView selectPttt;

  @NonNull
  public final EditText selectVoucher;

  @NonNull
  public final EditText txtEmail;

  @NonNull
  public final EditText txtName;

  @NonNull
  public final EditText txtPhone;

  private FragmentThanhToanBinding(@NonNull FrameLayout rootView, @NonNull ImageView back,
      @NonNull Button buttonThanhToan, @NonNull FrameLayout frameLayout,
      @NonNull TextView selectPttt, @NonNull EditText selectVoucher, @NonNull EditText txtEmail,
      @NonNull EditText txtName, @NonNull EditText txtPhone) {
    this.rootView = rootView;
    this.back = back;
    this.buttonThanhToan = buttonThanhToan;
    this.frameLayout = frameLayout;
    this.selectPttt = selectPttt;
    this.selectVoucher = selectVoucher;
    this.txtEmail = txtEmail;
    this.txtName = txtName;
    this.txtPhone = txtPhone;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentThanhToanBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentThanhToanBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_thanh_toan, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentThanhToanBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.back;
      ImageView back = ViewBindings.findChildViewById(rootView, id);
      if (back == null) {
        break missingId;
      }

      id = R.id.button_ThanhToan;
      Button buttonThanhToan = ViewBindings.findChildViewById(rootView, id);
      if (buttonThanhToan == null) {
        break missingId;
      }

      FrameLayout frameLayout = (FrameLayout) rootView;

      id = R.id.select_pttt;
      TextView selectPttt = ViewBindings.findChildViewById(rootView, id);
      if (selectPttt == null) {
        break missingId;
      }

      id = R.id.select_voucher;
      EditText selectVoucher = ViewBindings.findChildViewById(rootView, id);
      if (selectVoucher == null) {
        break missingId;
      }

      id = R.id.txt_email;
      EditText txtEmail = ViewBindings.findChildViewById(rootView, id);
      if (txtEmail == null) {
        break missingId;
      }

      id = R.id.txt_name;
      EditText txtName = ViewBindings.findChildViewById(rootView, id);
      if (txtName == null) {
        break missingId;
      }

      id = R.id.txt_phone;
      EditText txtPhone = ViewBindings.findChildViewById(rootView, id);
      if (txtPhone == null) {
        break missingId;
      }

      return new FragmentThanhToanBinding((FrameLayout) rootView, back, buttonThanhToan,
          frameLayout, selectPttt, selectVoucher, txtEmail, txtName, txtPhone);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

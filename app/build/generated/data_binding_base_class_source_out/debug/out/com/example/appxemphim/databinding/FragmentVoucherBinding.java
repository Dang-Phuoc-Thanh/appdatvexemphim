// Generated by view binder compiler. Do not edit!
package com.example.appxemphim.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appxemphim.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentVoucherBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView Payment;

  @NonNull
  public final TextView addUudai;

  @NonNull
  public final TextView addVoucher;

  @NonNull
  public final TextView lichsu;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final RelativeLayout main;

  private FragmentVoucherBinding(@NonNull RelativeLayout rootView, @NonNull TextView Payment,
      @NonNull TextView addUudai, @NonNull TextView addVoucher, @NonNull TextView lichsu,
      @NonNull LinearLayout linearLayout, @NonNull RelativeLayout main) {
    this.rootView = rootView;
    this.Payment = Payment;
    this.addUudai = addUudai;
    this.addVoucher = addVoucher;
    this.lichsu = lichsu;
    this.linearLayout = linearLayout;
    this.main = main;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentVoucherBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentVoucherBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_voucher, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentVoucherBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Payment;
      TextView Payment = ViewBindings.findChildViewById(rootView, id);
      if (Payment == null) {
        break missingId;
      }

      id = R.id.add_uudai;
      TextView addUudai = ViewBindings.findChildViewById(rootView, id);
      if (addUudai == null) {
        break missingId;
      }

      id = R.id.add_voucher;
      TextView addVoucher = ViewBindings.findChildViewById(rootView, id);
      if (addVoucher == null) {
        break missingId;
      }

      id = R.id.lichsu;
      TextView lichsu = ViewBindings.findChildViewById(rootView, id);
      if (lichsu == null) {
        break missingId;
      }

      id = R.id.linear_layout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      RelativeLayout main = (RelativeLayout) rootView;

      return new FragmentVoucherBinding((RelativeLayout) rootView, Payment, addUudai, addVoucher,
          lichsu, linearLayout, main);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

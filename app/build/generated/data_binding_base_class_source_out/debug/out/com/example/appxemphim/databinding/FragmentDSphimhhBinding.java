// Generated by view binder compiler. Do not edit!
package com.example.appxemphim.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appxemphim.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentDSphimhhBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout FrameDSPhim;

  @NonNull
  public final ImageView icQuaylai;

  @NonNull
  public final ListView listview;

  @NonNull
  public final TextView textView3;

  private FragmentDSphimhhBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout FrameDSPhim, @NonNull ImageView icQuaylai,
      @NonNull ListView listview, @NonNull TextView textView3) {
    this.rootView = rootView;
    this.FrameDSPhim = FrameDSPhim;
    this.icQuaylai = icQuaylai;
    this.listview = listview;
    this.textView3 = textView3;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentDSphimhhBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentDSphimhhBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_d_sphimhh, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentDSphimhhBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout FrameDSPhim = (ConstraintLayout) rootView;

      id = R.id.ic_quaylai;
      ImageView icQuaylai = ViewBindings.findChildViewById(rootView, id);
      if (icQuaylai == null) {
        break missingId;
      }

      id = R.id.listview;
      ListView listview = ViewBindings.findChildViewById(rootView, id);
      if (listview == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      return new FragmentDSphimhhBinding((ConstraintLayout) rootView, FrameDSPhim, icQuaylai,
          listview, textView3);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
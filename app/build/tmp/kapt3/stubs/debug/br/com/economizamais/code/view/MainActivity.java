package br.com.economizamais.code.view;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\rB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0015J\u0012\u0010\t\u001a\u00020\u0006*\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lbr/com/economizamais/code/view/MainActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "mOnNavigationItemSelectedListener", "Landroid/support/design/widget/BottomNavigationView$OnNavigationItemSelectedListener;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "addOnItemClickListener", "Landroid/support/v7/widget/RecyclerView;", "onClickListener", "Lbr/com/economizamais/code/view/MainActivity$OnItemClickListener;", "OnItemClickListener", "app_debug"})
public final class MainActivity extends android.support.v7.app.AppCompatActivity {
    
    /**
     * NAVBAR 
     */
    private final android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = null;
    private java.util.HashMap _$_findViewCache;
    
    /**
     * ONCREATE 
     */
    @android.support.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.JELLY_BEAN_MR1)
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public final void addOnItemClickListener(@org.jetbrains.annotations.NotNull()
    android.support.v7.widget.RecyclerView $receiver, @org.jetbrains.annotations.NotNull()
    br.com.economizamais.code.view.MainActivity.OnItemClickListener onClickListener) {
    }
    
    public MainActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lbr/com/economizamais/code/view/MainActivity$OnItemClickListener;", "", "onItemClicked", "", "position", "", "view", "Landroid/view/View;", "app_debug"})
    public static abstract interface OnItemClickListener {
        
        public abstract void onItemClicked(int position, @org.jetbrains.annotations.NotNull()
        android.view.View view);
    }
}
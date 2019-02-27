package br.com.economizamais.code.view;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0012\u0010\u0013\u001a\u00020\u0010*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n\u00a8\u0006\u0018"}, d2 = {"Lbr/com/economizamais/code/view/DetalhesLojaActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "PHONE_NUMBER", "", "listaNomeMarca", "", "getListaNomeMarca", "()Ljava/util/List;", "setListaNomeMarca", "(Ljava/util/List;)V", "listaProdutosTemp", "Lbr/com/economizamais/code/model/entities/Produto;", "getListaProdutosTemp", "setListaProdutosTemp", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "addOnItemClickListener", "Landroid/support/v7/widget/RecyclerView;", "onClickListener", "Lbr/com/economizamais/code/view/DetalhesLojaActivity$OnItemClickListener;", "OnItemClickListener", "app_debug"})
public final class DetalhesLojaActivity extends android.support.v7.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private java.util.List<br.com.economizamais.code.model.entities.Produto> listaProdutosTemp;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<java.lang.String> listaNomeMarca;
    private java.lang.String PHONE_NUMBER;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<br.com.economizamais.code.model.entities.Produto> getListaProdutosTemp() {
        return null;
    }
    
    public final void setListaProdutosTemp(@org.jetbrains.annotations.NotNull()
    java.util.List<br.com.economizamais.code.model.entities.Produto> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getListaNomeMarca() {
        return null;
    }
    
    public final void setListaNomeMarca(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public final void addOnItemClickListener(@org.jetbrains.annotations.NotNull()
    android.support.v7.widget.RecyclerView $receiver, @org.jetbrains.annotations.NotNull()
    br.com.economizamais.code.view.DetalhesLojaActivity.OnItemClickListener onClickListener) {
    }
    
    public DetalhesLojaActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lbr/com/economizamais/code/view/DetalhesLojaActivity$OnItemClickListener;", "", "onItemClicked", "", "position", "", "view", "Landroid/view/View;", "app_debug"})
    public static abstract interface OnItemClickListener {
        
        public abstract void onItemClicked(int position, @org.jetbrains.annotations.NotNull()
        android.view.View view);
    }
}
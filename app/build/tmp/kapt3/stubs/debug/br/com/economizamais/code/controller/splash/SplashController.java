package br.com.economizamais.code.controller.splash;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\rJ&\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aJ(\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0007J&\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aJ\u000e\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!J \u0010\"\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0007J\u000e\u0010#\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010$\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!J\u000e\u0010%\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u0014\u0010\f\u001a\u00020\rX\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006&"}, d2 = {"Lbr/com/economizamais/code/controller/splash/SplashController;", "", "()V", "contadorLojas", "", "getContadorLojas", "()I", "setContadorLojas", "(I)V", "contadorProdutos", "getContadorProdutos", "setContadorProdutos", "enderecoConexao", "", "getEnderecoConexao", "()Ljava/lang/String;", "dataBaseExist", "", "contextoWrapper", "Landroid/content/ContextWrapper;", "dbName", "getLojasServidor", "", "contexto", "Landroid/content/Context;", "latitude", "", "longitude", "getMinhaLocalizacao", "getProdutosServidor", "goToAppDetailsSettings", "hideView", "view", "Landroid/view/View;", "obterLocalizacao", "requestMinhaLocalizacao", "showHide", "showView", "app_debug"})
public final class SplashController {
    
    /**
     * CONEXÃO COM SERVIDOR 
     */
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String enderecoConexao = "https://economizamais.herokuapp.com/";
    private int contadorLojas;
    private int contadorProdutos;
    
    /**
     * PERMISSÕES 
     */
    @android.annotation.SuppressLint(value = {"NewApi"})
    public final void getMinhaLocalizacao(@org.jetbrains.annotations.NotNull()
    android.content.Context contexto, @org.jetbrains.annotations.NotNull()
    android.content.ContextWrapper contextoWrapper, double latitude, double longitude) {
    }
    
    public final void requestMinhaLocalizacao(@org.jetbrains.annotations.NotNull()
    android.content.Context contexto) {
    }
    
    public final void goToAppDetailsSettings(@org.jetbrains.annotations.NotNull()
    android.content.Context contexto) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEnderecoConexao() {
        return null;
    }
    
    public final int getContadorLojas() {
        return 0;
    }
    
    public final void setContadorLojas(int p0) {
    }
    
    public final void getLojasServidor(@org.jetbrains.annotations.NotNull()
    android.content.Context contexto, @org.jetbrains.annotations.NotNull()
    android.content.ContextWrapper contextoWrapper, double latitude, double longitude) {
    }
    
    public final int getContadorProdutos() {
        return 0;
    }
    
    public final void setContadorProdutos(int p0) {
    }
    
    public final void getProdutosServidor(@org.jetbrains.annotations.NotNull()
    android.content.Context contexto, @org.jetbrains.annotations.NotNull()
    android.content.ContextWrapper contextoWrapper, double latitude, double longitude) {
    }
    
    /**
     * LOCALIZAÇÃO E ABERTURA DA MAIN 
     */
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    public final void obterLocalizacao(@org.jetbrains.annotations.NotNull()
    android.content.Context contexto, double latitude, double longitude) {
    }
    
    /**
     * BANCO DE DADOS 
     */
    public final boolean dataBaseExist(@org.jetbrains.annotations.NotNull()
    android.content.ContextWrapper contextoWrapper, @org.jetbrains.annotations.NotNull()
    java.lang.String dbName) {
        return false;
    }
    
    /**
     * CONTROLE DE VIEW 
     */
    public final void showView(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void hideView(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final void showHide(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public SplashController() {
        super();
    }
}
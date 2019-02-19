package br.com.economizamais.code.controller.main;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002JB\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fJB\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00072\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fJ(\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u0006J6\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u0006J\u000e\u0010\u0019\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u001a\u00a8\u0006\u001b"}, d2 = {"Lbr/com/economizamais/code/controller/main/MainController;", "", "()V", "cliqueProduto", "", "listaProdutos", "", "Lbr/com/economizamais/code/model/entities/Produto;", "listaLojas", "Lbr/com/economizamais/code/model/entities/Loja;", "produtoClicado", "latitude", "", "longitude", "contexto", "Landroid/content/Context;", "enviarDetalhes", "produto", "produtos", "lojas", "filtrarLojasDistancia", "listaOriginal", "listaFiltrada", "filtrarProdutosLojas", "listaFiltro", "recuperaId", "", "app_debug"})
public final class MainController {
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<br.com.economizamais.code.model.entities.Loja> filtrarLojasDistancia(@org.jetbrains.annotations.NotNull()
    java.util.List<br.com.economizamais.code.model.entities.Loja> listaOriginal, @org.jetbrains.annotations.NotNull()
    java.util.List<br.com.economizamais.code.model.entities.Loja> listaFiltrada) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<br.com.economizamais.code.model.entities.Produto> filtrarProdutosLojas(@org.jetbrains.annotations.NotNull()
    java.util.List<br.com.economizamais.code.model.entities.Produto> listaOriginal, @org.jetbrains.annotations.NotNull()
    java.util.List<br.com.economizamais.code.model.entities.Produto> listaFiltrada, @org.jetbrains.annotations.NotNull()
    java.util.List<br.com.economizamais.code.model.entities.Loja> listaFiltro) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final br.com.economizamais.code.model.entities.Produto recuperaId(@org.jetbrains.annotations.NotNull()
    java.lang.String produtoClicado) {
        return null;
    }
    
    public final void cliqueProduto(@org.jetbrains.annotations.NotNull()
    java.util.List<br.com.economizamais.code.model.entities.Produto> listaProdutos, @org.jetbrains.annotations.NotNull()
    java.util.List<br.com.economizamais.code.model.entities.Loja> listaLojas, @org.jetbrains.annotations.NotNull()
    br.com.economizamais.code.model.entities.Produto produtoClicado, double latitude, double longitude, @org.jetbrains.annotations.NotNull()
    android.content.Context contexto) {
    }
    
    public final void enviarDetalhes(@org.jetbrains.annotations.NotNull()
    android.content.Context contexto, @org.jetbrains.annotations.NotNull()
    br.com.economizamais.code.model.entities.Produto produto, @org.jetbrains.annotations.NotNull()
    java.util.List<br.com.economizamais.code.model.entities.Produto> produtos, @org.jetbrains.annotations.NotNull()
    java.util.List<br.com.economizamais.code.model.entities.Loja> lojas, double latitude, double longitude) {
    }
    
    public MainController() {
        super();
    }
}
package br.com.economizamais.code.model.dao;

import java.lang.System;

@android.arch.persistence.room.Dao()
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\b\u0010\u0004\u001a\u00020\u0005H\'J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\'J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\bH\'\u00a8\u0006\u000b"}, d2 = {"Lbr/com/economizamais/code/model/dao/ProdutoDao;", "", "deleteAllProduto", "", "findLastProdutoId", "", "getAllProduto", "", "Lbr/com/economizamais/code/model/entities/Produto;", "insertProduto", "produto", "app_debug"})
public abstract interface ProdutoDao {
    
    /**
     * Query 
     */
    @android.arch.persistence.room.Insert()
    public abstract void insertProduto(@org.jetbrains.annotations.NotNull()
    br.com.economizamais.code.model.entities.Produto produto);
    
    @android.arch.persistence.room.Query(value = "SELECT MAX(id) FROM Produto")
    public abstract long findLastProdutoId();
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * from Produto ORDER BY id ASC")
    public abstract java.util.List<br.com.economizamais.code.model.entities.Produto> getAllProduto();
    
    @android.arch.persistence.room.Query(value = "DELETE from Produto")
    public abstract void deleteAllProduto();
}
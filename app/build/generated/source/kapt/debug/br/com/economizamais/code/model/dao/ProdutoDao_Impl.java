package br.com.economizamais.code.model.dao;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import br.com.economizamais.code.model.entities.Produto;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class ProdutoDao_Impl implements ProdutoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfProduto;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllProduto;

  public ProdutoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProduto = new EntityInsertionAdapter<Produto>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Produto`(`id`,`nome`,`image`,`descricao`,`detalhe`,`preco`,`marca`,`validade`,`idLoja`,`loja`,`estado`,`cidade`,`latitude`,`longitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Produto value) {
        stmt.bindLong(1, value.getId());
        if (value.getNome() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNome());
        }
        if (value.getImage() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getImage());
        }
        if (value.getDescricao() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescricao());
        }
        final int _tmp;
        _tmp = value.getDetalhe() ? 1 : 0;
        stmt.bindLong(5, _tmp);
        stmt.bindDouble(6, value.getPreco());
        if (value.getMarca() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getMarca());
        }
        if (value.getValidade() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getValidade());
        }
        stmt.bindLong(9, value.getIdLoja());
        if (value.getLoja() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getLoja());
        }
        if (value.getEstado() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getEstado());
        }
        if (value.getCidade() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getCidade());
        }
        stmt.bindDouble(13, value.getLatitude());
        stmt.bindDouble(14, value.getLongitude());
      }
    };
    this.__preparedStmtOfDeleteAllProduto = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE from Produto";
        return _query;
      }
    };
  }

  @Override
  public void insertProduto(Produto produto) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfProduto.insert(produto);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllProduto() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllProduto.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllProduto.release(_stmt);
    }
  }

  @Override
  public long findLastProdutoId() {
    final String _sql = "SELECT MAX(id) FROM Produto";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final long _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getLong(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Produto> getAllProduto() {
    final String _sql = "SELECT * from Produto ORDER BY id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfNome = _cursor.getColumnIndexOrThrow("nome");
      final int _cursorIndexOfImage = _cursor.getColumnIndexOrThrow("image");
      final int _cursorIndexOfDescricao = _cursor.getColumnIndexOrThrow("descricao");
      final int _cursorIndexOfDetalhe = _cursor.getColumnIndexOrThrow("detalhe");
      final int _cursorIndexOfPreco = _cursor.getColumnIndexOrThrow("preco");
      final int _cursorIndexOfMarca = _cursor.getColumnIndexOrThrow("marca");
      final int _cursorIndexOfValidade = _cursor.getColumnIndexOrThrow("validade");
      final int _cursorIndexOfIdLoja = _cursor.getColumnIndexOrThrow("idLoja");
      final int _cursorIndexOfLoja = _cursor.getColumnIndexOrThrow("loja");
      final int _cursorIndexOfEstado = _cursor.getColumnIndexOrThrow("estado");
      final int _cursorIndexOfCidade = _cursor.getColumnIndexOrThrow("cidade");
      final int _cursorIndexOfLatitude = _cursor.getColumnIndexOrThrow("latitude");
      final int _cursorIndexOfLongitude = _cursor.getColumnIndexOrThrow("longitude");
      final List<Produto> _result = new ArrayList<Produto>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Produto _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpNome;
        _tmpNome = _cursor.getString(_cursorIndexOfNome);
        final String _tmpImage;
        _tmpImage = _cursor.getString(_cursorIndexOfImage);
        final String _tmpDescricao;
        _tmpDescricao = _cursor.getString(_cursorIndexOfDescricao);
        final boolean _tmpDetalhe;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfDetalhe);
        _tmpDetalhe = _tmp != 0;
        final double _tmpPreco;
        _tmpPreco = _cursor.getDouble(_cursorIndexOfPreco);
        final String _tmpMarca;
        _tmpMarca = _cursor.getString(_cursorIndexOfMarca);
        final String _tmpValidade;
        _tmpValidade = _cursor.getString(_cursorIndexOfValidade);
        final int _tmpIdLoja;
        _tmpIdLoja = _cursor.getInt(_cursorIndexOfIdLoja);
        final String _tmpLoja;
        _tmpLoja = _cursor.getString(_cursorIndexOfLoja);
        final String _tmpEstado;
        _tmpEstado = _cursor.getString(_cursorIndexOfEstado);
        final String _tmpCidade;
        _tmpCidade = _cursor.getString(_cursorIndexOfCidade);
        final double _tmpLatitude;
        _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
        final double _tmpLongitude;
        _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
        _item = new Produto(_tmpId,_tmpNome,_tmpImage,_tmpDescricao,_tmpDetalhe,_tmpPreco,_tmpMarca,_tmpValidade,_tmpIdLoja,_tmpLoja,_tmpEstado,_tmpCidade,_tmpLatitude,_tmpLongitude);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}

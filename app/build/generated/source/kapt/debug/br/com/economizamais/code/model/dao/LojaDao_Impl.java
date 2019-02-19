package br.com.economizamais.code.model.dao;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import br.com.economizamais.code.model.entities.Loja;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class LojaDao_Impl implements LojaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfLoja;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllLoja;

  public LojaDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLoja = new EntityInsertionAdapter<Loja>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Loja`(`id`,`razaoSocial`,`eslogan`,`telefone`,`email`,`endereco`,`cnpj`,`image`,`bairro`,`cidade`,`uf`,`cep`,`latitude`,`longitude`,`distancia`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Loja value) {
        stmt.bindLong(1, value.getId());
        if (value.getRazaoSocial() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getRazaoSocial());
        }
        if (value.getEslogan() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getEslogan());
        }
        if (value.getTelefone() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTelefone());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getEmail());
        }
        if (value.getEndereco() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEndereco());
        }
        if (value.getCnpj() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getCnpj());
        }
        if (value.getImage() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getImage());
        }
        if (value.getBairro() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getBairro());
        }
        if (value.getCidade() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getCidade());
        }
        if (value.getUf() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getUf());
        }
        if (value.getCep() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getCep());
        }
        stmt.bindDouble(13, value.getLatitude());
        stmt.bindDouble(14, value.getLongitude());
        stmt.bindDouble(15, value.getDistancia());
      }
    };
    this.__preparedStmtOfDeleteAllLoja = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE from Loja";
        return _query;
      }
    };
  }

  @Override
  public void insertLoja(Loja loja) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfLoja.insert(loja);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllLoja() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllLoja.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllLoja.release(_stmt);
    }
  }

  @Override
  public long findLastLojaId() {
    final String _sql = "SELECT MAX(id) FROM Loja";
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
  public List<Loja> getAllLoja() {
    final String _sql = "SELECT * from Loja ORDER BY id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfRazaoSocial = _cursor.getColumnIndexOrThrow("razaoSocial");
      final int _cursorIndexOfEslogan = _cursor.getColumnIndexOrThrow("eslogan");
      final int _cursorIndexOfTelefone = _cursor.getColumnIndexOrThrow("telefone");
      final int _cursorIndexOfEmail = _cursor.getColumnIndexOrThrow("email");
      final int _cursorIndexOfEndereco = _cursor.getColumnIndexOrThrow("endereco");
      final int _cursorIndexOfCnpj = _cursor.getColumnIndexOrThrow("cnpj");
      final int _cursorIndexOfImage = _cursor.getColumnIndexOrThrow("image");
      final int _cursorIndexOfBairro = _cursor.getColumnIndexOrThrow("bairro");
      final int _cursorIndexOfCidade = _cursor.getColumnIndexOrThrow("cidade");
      final int _cursorIndexOfUf = _cursor.getColumnIndexOrThrow("uf");
      final int _cursorIndexOfCep = _cursor.getColumnIndexOrThrow("cep");
      final int _cursorIndexOfLatitude = _cursor.getColumnIndexOrThrow("latitude");
      final int _cursorIndexOfLongitude = _cursor.getColumnIndexOrThrow("longitude");
      final int _cursorIndexOfDistancia = _cursor.getColumnIndexOrThrow("distancia");
      final List<Loja> _result = new ArrayList<Loja>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Loja _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpRazaoSocial;
        _tmpRazaoSocial = _cursor.getString(_cursorIndexOfRazaoSocial);
        final String _tmpEslogan;
        _tmpEslogan = _cursor.getString(_cursorIndexOfEslogan);
        final String _tmpTelefone;
        _tmpTelefone = _cursor.getString(_cursorIndexOfTelefone);
        final String _tmpEmail;
        _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        final String _tmpEndereco;
        _tmpEndereco = _cursor.getString(_cursorIndexOfEndereco);
        final String _tmpCnpj;
        _tmpCnpj = _cursor.getString(_cursorIndexOfCnpj);
        final String _tmpImage;
        _tmpImage = _cursor.getString(_cursorIndexOfImage);
        final String _tmpBairro;
        _tmpBairro = _cursor.getString(_cursorIndexOfBairro);
        final String _tmpCidade;
        _tmpCidade = _cursor.getString(_cursorIndexOfCidade);
        final String _tmpUf;
        _tmpUf = _cursor.getString(_cursorIndexOfUf);
        final String _tmpCep;
        _tmpCep = _cursor.getString(_cursorIndexOfCep);
        final double _tmpLatitude;
        _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
        final double _tmpLongitude;
        _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
        final double _tmpDistancia;
        _tmpDistancia = _cursor.getDouble(_cursorIndexOfDistancia);
        _item = new Loja(_tmpId,_tmpRazaoSocial,_tmpEslogan,_tmpTelefone,_tmpEmail,_tmpEndereco,_tmpCnpj,_tmpImage,_tmpBairro,_tmpCidade,_tmpUf,_tmpCep,_tmpLatitude,_tmpLongitude,_tmpDistancia);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}

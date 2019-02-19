package br.com.economizamais.code.controller.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import br.com.economizamais.code.model.dao.ProdutoDao;
import br.com.economizamais.code.model.dao.ProdutoDao_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class ProdutoDatabase_Impl extends ProdutoDatabase {
  private volatile ProdutoDao _produtoDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Produto` (`id` INTEGER NOT NULL, `nome` TEXT NOT NULL, `image` TEXT NOT NULL, `descricao` TEXT NOT NULL, `detalhe` INTEGER NOT NULL, `preco` REAL NOT NULL, `marca` TEXT NOT NULL, `validade` TEXT NOT NULL, `idLoja` INTEGER NOT NULL, `loja` TEXT NOT NULL, `estado` TEXT NOT NULL, `cidade` TEXT NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"89c96ef94b8fcdcd1f042cda24b6b1be\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Produto`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsProduto = new HashMap<String, TableInfo.Column>(14);
        _columnsProduto.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsProduto.put("nome", new TableInfo.Column("nome", "TEXT", true, 0));
        _columnsProduto.put("image", new TableInfo.Column("image", "TEXT", true, 0));
        _columnsProduto.put("descricao", new TableInfo.Column("descricao", "TEXT", true, 0));
        _columnsProduto.put("detalhe", new TableInfo.Column("detalhe", "INTEGER", true, 0));
        _columnsProduto.put("preco", new TableInfo.Column("preco", "REAL", true, 0));
        _columnsProduto.put("marca", new TableInfo.Column("marca", "TEXT", true, 0));
        _columnsProduto.put("validade", new TableInfo.Column("validade", "TEXT", true, 0));
        _columnsProduto.put("idLoja", new TableInfo.Column("idLoja", "INTEGER", true, 0));
        _columnsProduto.put("loja", new TableInfo.Column("loja", "TEXT", true, 0));
        _columnsProduto.put("estado", new TableInfo.Column("estado", "TEXT", true, 0));
        _columnsProduto.put("cidade", new TableInfo.Column("cidade", "TEXT", true, 0));
        _columnsProduto.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0));
        _columnsProduto.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProduto = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProduto = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProduto = new TableInfo("Produto", _columnsProduto, _foreignKeysProduto, _indicesProduto);
        final TableInfo _existingProduto = TableInfo.read(_db, "Produto");
        if (! _infoProduto.equals(_existingProduto)) {
          throw new IllegalStateException("Migration didn't properly handle Produto(br.com.economizamais.code.model.entities.Produto).\n"
                  + " Expected:\n" + _infoProduto + "\n"
                  + " Found:\n" + _existingProduto);
        }
      }
    }, "89c96ef94b8fcdcd1f042cda24b6b1be", "6954f66565da6eceb0c7298537d23ddf");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Produto");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Produto`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public ProdutoDao ProdutoDao() {
    if (_produtoDao != null) {
      return _produtoDao;
    } else {
      synchronized(this) {
        if(_produtoDao == null) {
          _produtoDao = new ProdutoDao_Impl(this);
        }
        return _produtoDao;
      }
    }
  }
}

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
import br.com.economizamais.code.model.dao.LojaDao;
import br.com.economizamais.code.model.dao.LojaDao_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class LojaDatabase_Impl extends LojaDatabase {
  private volatile LojaDao _lojaDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Loja` (`id` INTEGER NOT NULL, `razaoSocial` TEXT NOT NULL, `eslogan` TEXT NOT NULL, `telefone` TEXT NOT NULL, `email` TEXT NOT NULL, `endereco` TEXT NOT NULL, `cnpj` TEXT NOT NULL, `image` TEXT NOT NULL, `bairro` TEXT NOT NULL, `cidade` TEXT NOT NULL, `uf` TEXT NOT NULL, `cep` TEXT NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `distancia` REAL NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"bdbe7ac83174c8ccf90ab76744c7cb1a\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Loja`");
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
        final HashMap<String, TableInfo.Column> _columnsLoja = new HashMap<String, TableInfo.Column>(15);
        _columnsLoja.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsLoja.put("razaoSocial", new TableInfo.Column("razaoSocial", "TEXT", true, 0));
        _columnsLoja.put("eslogan", new TableInfo.Column("eslogan", "TEXT", true, 0));
        _columnsLoja.put("telefone", new TableInfo.Column("telefone", "TEXT", true, 0));
        _columnsLoja.put("email", new TableInfo.Column("email", "TEXT", true, 0));
        _columnsLoja.put("endereco", new TableInfo.Column("endereco", "TEXT", true, 0));
        _columnsLoja.put("cnpj", new TableInfo.Column("cnpj", "TEXT", true, 0));
        _columnsLoja.put("image", new TableInfo.Column("image", "TEXT", true, 0));
        _columnsLoja.put("bairro", new TableInfo.Column("bairro", "TEXT", true, 0));
        _columnsLoja.put("cidade", new TableInfo.Column("cidade", "TEXT", true, 0));
        _columnsLoja.put("uf", new TableInfo.Column("uf", "TEXT", true, 0));
        _columnsLoja.put("cep", new TableInfo.Column("cep", "TEXT", true, 0));
        _columnsLoja.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0));
        _columnsLoja.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0));
        _columnsLoja.put("distancia", new TableInfo.Column("distancia", "REAL", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLoja = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLoja = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLoja = new TableInfo("Loja", _columnsLoja, _foreignKeysLoja, _indicesLoja);
        final TableInfo _existingLoja = TableInfo.read(_db, "Loja");
        if (! _infoLoja.equals(_existingLoja)) {
          throw new IllegalStateException("Migration didn't properly handle Loja(br.com.economizamais.code.model.entities.Loja).\n"
                  + " Expected:\n" + _infoLoja + "\n"
                  + " Found:\n" + _existingLoja);
        }
      }
    }, "bdbe7ac83174c8ccf90ab76744c7cb1a", "745202fc7257b4e11bbd0c46e2cf1faa");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Loja");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Loja`");
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
  public LojaDao LojaDao() {
    if (_lojaDao != null) {
      return _lojaDao;
    } else {
      synchronized(this) {
        if(_lojaDao == null) {
          _lojaDao = new LojaDao_Impl(this);
        }
        return _lojaDao;
      }
    }
  }
}

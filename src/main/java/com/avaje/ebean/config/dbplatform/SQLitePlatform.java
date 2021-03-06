package com.avaje.ebean.config.dbplatform;

import com.avaje.ebean.dbmigration.ddlgeneration.platform.SQLiteDdl;

import java.sql.Types;

public class SQLitePlatform extends DatabasePlatform {

  public SQLitePlatform() {
    super();
    this.name = "sqlite";
    this.platformDdl = new SQLiteDdl(this);

    this.dbIdentity.setIdType(IdType.IDENTITY);
    this.dbIdentity.setSupportsGetGeneratedKeys(false);
    this.dbIdentity.setSupportsSequence(false);
    this.dbIdentity.setSelectLastInsertedIdTemplate("select last_insert_rowid()");

    this.booleanDbType = Types.INTEGER;

    dbTypeMap.put(DbType.BIT, new DbPlatformType("int default 0"));
    dbTypeMap.put(DbType.BOOLEAN, new DbPlatformType("int default 0"));
    dbTypeMap.put(DbType.BIGINT, new DbPlatformType("integer"));
    dbTypeMap.put(DbType.SMALLINT, new DbPlatformType("integer"));

  }

}

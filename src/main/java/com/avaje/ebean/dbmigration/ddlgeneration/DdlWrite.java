package com.avaje.ebean.dbmigration.ddlgeneration;

import com.avaje.ebean.dbmigration.ddlgeneration.platform.BaseDdlBuffer;
import com.avaje.ebean.dbmigration.model.MConfiguration;
import com.avaje.ebean.dbmigration.model.MTable;
import com.avaje.ebean.dbmigration.model.ModelContainer;

/**
 * Write context holding the buffers for both apply and rollback DDL.
 */
public class DdlWrite {

  private final ModelContainer currentModel;

  private final DdlBuffer applyDropDependencies;

  private final DdlBuffer apply;

  private final DdlBuffer applyForeignKeys;

  private final DdlBuffer applyHistory;

  private final DdlBuffer dropAllForeignKeys;

  private final DdlBuffer dropAll;

  /**
   * Create without any configuration or current model (no history support).
   */
  public DdlWrite() {
    this(new MConfiguration(), new ModelContainer());
  }

  /**
   * Create with a configuration.
   */
  public DdlWrite(MConfiguration configuration, ModelContainer currentModel) {
    this.currentModel = currentModel;
    this.applyDropDependencies = new BaseDdlBuffer(configuration);
    this.apply = new BaseDdlBuffer(configuration);
    this.applyForeignKeys = new BaseDdlBuffer(configuration);
    this.applyHistory = new BaseDdlBuffer(configuration);
    this.dropAllForeignKeys = new BaseDdlBuffer(configuration);
    this.dropAll = new BaseDdlBuffer(configuration);
  }

  /**
   * Return the Table information from the current model.
   * <p>
   * This is typically required for the history support (used to determine the list of columns
   * included in the history when creating or recreating the associated trigger/stored procedure).
   * </p>
   */
  public MTable getTable(String tableName) {
    return currentModel.getTable(tableName);
  }

  /**
   * Return true if the apply buffers are all empty.
   */
  public boolean isApplyEmpty() {
    return apply.getBuffer().isEmpty()
        && applyForeignKeys.getBuffer().isEmpty()
        && applyHistory.getBuffer().isEmpty()
        && applyDropDependencies.getBuffer().isEmpty();
  }

  /**
   * Return the buffer that APPLY DDL is written to.
   */
  public DdlBuffer apply() {
    return apply;
  }

  /**
   * Return the buffer that executes early to drop dependencies like views etc.
   */
  public DdlBuffer applyDropDependencies() {
    return applyDropDependencies;
  }

  /**
   * Return the buffer that APPLY DDL is written to for foreign keys and their associated indexes.
   * <p>
   * Statements added to this buffer are executed after all the normal apply statements and
   * typically 'add foreign key' is added to this buffer.
   */
  public DdlBuffer applyForeignKeys() {
    return applyForeignKeys;
  }

  /**
   * Return the buffer that apply history DDL is written to.
   */
  public DdlBuffer applyHistory() {
    return applyHistory;
  }

  /**
   * Return the buffer used for the 'drop all DDL' for dropping foreign keys and associated indexes.
   */
  public DdlBuffer dropAllForeignKeys() {
    return dropAllForeignKeys;
  }

  /**
   * Return the buffer used for the 'drop all DDL' to drop tables, views and history triggers etc.
   */
  public DdlBuffer dropAll() {
    return dropAll;
  }

}

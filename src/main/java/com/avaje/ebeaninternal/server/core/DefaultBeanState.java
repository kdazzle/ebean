package com.avaje.ebeaninternal.server.core;

import com.avaje.ebean.BeanState;
import com.avaje.ebean.ValuePair;
import com.avaje.ebean.bean.EntityBean;
import com.avaje.ebean.bean.EntityBeanIntercept;

import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.Set;

/**
 * Default implementation of BeanState.
 */
public class DefaultBeanState implements BeanState {

  private final EntityBean entityBean;

  private final EntityBeanIntercept intercept;

  public DefaultBeanState(EntityBean entityBean) {
    this.entityBean = entityBean;
    this.intercept = entityBean._ebean_getIntercept();
  }

  public void setPropertyLoaded(String propertyName, boolean loaded) {
    intercept.setPropertyLoaded(propertyName, loaded);
  }

  public boolean isReference() {
    return intercept.isReference();
  }

  public boolean isNew() {
    return intercept.isNew();
  }

  public boolean isNewOrDirty() {
    return intercept.isNewOrDirty();
  }

  public boolean isDirty() {
    return intercept.isDirty();
  }

  public Set<String> getLoadedProps() {
    return intercept.getLoadedPropertyNames();
  }

  public Set<String> getChangedProps() {
    return intercept.getDirtyPropertyNames();
  }

  public Map<String, ValuePair> getDirtyValues() {
    return intercept.getDirtyValues();
  }

  public boolean isReadOnly() {
    return intercept.isReadOnly();
  }

  public void setReadOnly(boolean readOnly) {
    intercept.setReadOnly(readOnly);
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    entityBean.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
    entityBean.removePropertyChangeListener(listener);
  }

  public void setLoaded() {
    intercept.setLoaded();
  }

  @Override
  public void setDisableLazyLoad(boolean disableLazyLoading) {
    intercept.setDisableLazyLoad(disableLazyLoading);
  }

  @Override
  public boolean isDisableLazyLoad() {
    return intercept.isDisableLazyLoad();
  }

  @Override
  public void resetForInsert() {
    intercept.setNew();
  }
}

/**
 * Logback: the reliable, generic, fast and flexible logging framework.
 * Copyright (C) 1999-2015, QOS.ch. All rights reserved.
 *
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 *
 *   or (per the licensee's choosing)
 *
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
 */
package ch.qos.logback.classic.db.names;

/**
 * Source of table and column names used in SQL queries generated by {@link ch.qos.logback.classic.db.DBAppender}
 *
 * Implement this interface to override default table and/or column names used by {@link ch.qos.logback.classic.db.DBAppender}.
 *
 * @author Tomasz Nurkiewicz
 * @author Ceki Gulcu
 * @since 0.9.19
 * @see ch.qos.logback.classic.db.names.DefaultDBNameResolver
 * @see ch.qos.logback.classic.db.names.SimpleDBNameResolver
 */
public interface DBNameResolver {

  <N extends Enum<?>> String getTableName(N tableName);

  <N extends Enum<?>> String getColumnName(N columnName);
}
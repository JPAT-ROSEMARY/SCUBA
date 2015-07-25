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
package ch.qos.logback.classic;

import ch.qos.logback.core.util.CoreTestConstants;

public class ClassicTestConstants {
  static final public String ISO_REGEX = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3}";
  //pool-1-thread-47
  static final public String NAKED_MAIN_REGEX = "([mM]ain|pool-\\d-)([Tt]hread)?(-\\d{1,3})?";


  static final public String MAIN_REGEX = "\\[" + NAKED_MAIN_REGEX + "\\]";
  static final public String INPUT_PREFIX = "src/test/input/";
  static final public String JORAN_INPUT_PREFIX = INPUT_PREFIX + "joran/";
  static final public String ISSUES_PREFIX =   ClassicTestConstants.JORAN_INPUT_PREFIX+"issues/";
  static final public String GAFFER_INPUT_PREFIX = INPUT_PREFIX + "gaffer/";
  static final public String OUTPUT_DIR_PREFIX= CoreTestConstants.OUTPUT_DIR_PREFIX;
}

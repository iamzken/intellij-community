/*
 * Copyright 2000-2014 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.intellij.util.containers;

import com.intellij.util.DeprecatedMethodException;
import com.intellij.util.Function;

/**
 * Use {@link ConcurrentFactoryMap#createWeakMap(Function)} instead
 * TODO to remove in IDEA 2018
 */
@Deprecated
public abstract class ConcurrentWeakFactoryMap<K,V> extends ConcurrentFactoryMap<K,V> {
  /**
   * Use {@link ConcurrentFactoryMap#createWeakMap(Function)} instead
   * TODO to remove in IDEA 2018
   */
  @Deprecated
  public ConcurrentWeakFactoryMap() {
    DeprecatedMethodException.report("Use com.intellij.util.containers.ConcurrentFactoryMap.createWeakMap instead");
  }
}
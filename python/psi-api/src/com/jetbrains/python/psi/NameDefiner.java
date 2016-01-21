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
package com.jetbrains.python.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * PSI element that (re)defines names in following namespace, e.g. as assignment statement does.
 *
 * @author dcheryasov
 */
public interface NameDefiner extends PsiElement {
  /**
   * @return an iterator that iterates over defined names, in order of definition.
   * Complex targets count, too: "(y, x[1]) = (1, 2)" return both "y" and "x[1]".
   */
  @NotNull
  Iterable<PyElement> iterateNames();

  /**
   * @param name an unqualified name.
   * @return an element which is defined under that name in this instance, or null. 
   */
  @Nullable
  PsiElement getElementNamed(String name);
}

/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.css;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * Settings for customizing the serialization of CSS properties etc.
 * 
 * @author Philip Helger
 */
public interface ICSSWriterSettings
{
  /**
   * @return The CSS version to be used. May not be <code>null</code>.
   */
  @Nonnull
  ECSSVersion getVersion ();

  /**
   * @return <code>true</code> if all unnecessary whitespaces should be ignored
   *         when writing.
   */
  boolean isOptimizedOutput ();

  /**
   * @return <code>true</code> if all unnecessary elements (like empty style
   *         declarations) should be removed. This will than potentially lead to
   *         CSS that is not equal to the original CSS!
   */
  boolean isRemoveUnnecessaryCode ();

  /**
   * Get the indentation for an arbitrary number of levels. This can be used to
   * customize the indentation strategy like using tabs or spaces, how many
   * spaces etc.
   * 
   * @param nCount
   *        The number of indentations desired. Always &ge; 0.
   * @return The string to be used for indentation. May not be <code>null</code>
   *         but may be empty.
   */
  @Nonnull
  String getIndent (@Nonnegative int nCount);

  /**
   * @return <code>true</code> if all URL values should be quoted,
   *         <code>false</code> if URL quoting should only be applied if
   *         absolutely necessary.
   */
  boolean isQuoteURLs ();

  /**
   * @return <code>true</code> if @namespace rules should be written,
   *         <code>false</code> if not
   */
  boolean isWriteNamespaceRules ();

  /**
   * @return <code>true</code> if @font-face rules should be written,
   *         <code>false</code> if not
   */
  boolean isWriteFontFaceRules ();

  /**
   * @return <code>true</code> if @keyframes rules should be written,
   *         <code>false</code> if not
   */
  boolean isWriteKeyframesRules ();

  /**
   * @return <code>true</code> if @media rules should be written,
   *         <code>false</code> if not
   */
  boolean isWriteMediaRules ();

  /**
   * @return <code>true</code> if @page rules should be written,
   *         <code>false</code> if not
   */
  boolean isWritePageRules ();

  /**
   * @return <code>true</code> if @viewport rules should be written,
   *         <code>false</code> if not
   */
  boolean isWriteViewportRules ();

  /**
   * @return <code>true</code> if @supports rules should be written,
   *         <code>false</code> if not
   */
  boolean isWriteSupportsRules ();

  /**
   * @return <code>true</code> if unknown @ rules should be written,
   *         <code>false</code> if not
   */
  boolean isWriteUnknownRules ();

  /**
   * Check if the passed object matches the version requirements defined be this
   * settings.
   * 
   * @param aCSSObject
   *        The object to be checked.
   * @throws IllegalStateException
   *         In case the version does not match
   * @see #getVersion()
   */
  void checkVersionRequirements (@Nonnull ICSSVersionAware aCSSObject) throws IllegalStateException;
}

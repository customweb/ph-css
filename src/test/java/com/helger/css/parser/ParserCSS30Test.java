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
package com.helger.css.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import com.helger.commons.charset.CCharset;
import com.helger.commons.io.stream.NonBlockingStringReader;
import com.helger.css.AbstractCSS30TestCase;
import com.helger.css.ECSSVersion;
import com.helger.css.decl.CascadingStyleSheet;
import com.helger.css.decl.ICSSTopLevelRule;
import com.helger.css.handler.CSSHandler;
import com.helger.css.reader.CSSReader;
import com.helger.css.reader.errorhandler.LoggingCSSParseErrorHandler;
import com.helger.css.writer.CSSWriter;
import com.helger.css.writer.CSSWriterSettings;

/**
 * Test class for class {@link ParserCSS30}.
 *
 * @author Philip Helger
 */
public final class ParserCSS30Test extends AbstractCSS30TestCase
{
  @Test
  public void test1 () throws ParseException
  {
    final ParserCSS30TokenManager aTokenHdl = new ParserCSS30TokenManager (new CSSCharStream (new NonBlockingStringReader (CSS1)));
    aTokenHdl.setDebugStream (System.out);
    final ParserCSS30 aParser = new ParserCSS30 (aTokenHdl);
    aParser.disable_tracing ();
    final CSSNode aNode = aParser.styleSheet ();
    assertNotNull (aNode);
  }

  @Test
  public void test2 () throws ParseException
  {
    final ParserCSS30TokenManager aTokenHdl = new ParserCSS30TokenManager (new CSSCharStream (new NonBlockingStringReader (CSS2)));
    aTokenHdl.setDebugStream (System.out);
    final ParserCSS30 aParser = new ParserCSS30 (aTokenHdl);
    aParser.disable_tracing ();
    final CSSNode aNode = aParser.styleSheet ();
    assertNotNull (aNode);

    final CascadingStyleSheet aCSS = CSSHandler.readCascadingStyleSheetFromNode (ECSSVersion.CSS30, aNode);
    assertNotNull (aCSS);

    for (final ICSSTopLevelRule aTopLevelRule : aCSS.getAllFontFaceRules ())
      assertTrue (aCSS.removeRule (aTopLevelRule).isChanged ());
  }

  @Test
  public void testPageMemberRules () throws ParseException, IOException
  {

    CSSReader.setDefaultParseErrorHandler (new LoggingCSSParseErrorHandler ());
    
    File aFile = new File ("src/test/resources/testfiles/css30/good/pagemembers.css");
    final CascadingStyleSheet aCSS = CSSReader.readFromFile (aFile, CCharset.CHARSET_UTF_8_OBJ, ECSSVersion.CSS30);
    assertNotNull (aCSS);

    CSSWriter cssWriter = new CSSWriter (new CSSWriterSettings (ECSSVersion.CSS30));
    cssWriter.setWriteHeaderText (false);
    String output = cssWriter.getCSSAsString (aCSS);
    
    FileInputStream fin =  new FileInputStream("src/test/resources/testfiles/css30/good/pagemembers_result.css");
    BufferedReader myInput = new BufferedReader(new InputStreamReader(fin));
    StringBuilder sb = new StringBuilder();
    String line;

    
    while ((line = myInput.readLine()) != null) {  
               sb.append(line + "\n");
    }
    myInput.close();
    
    assertEquals(output, sb.toString ());
  }
}

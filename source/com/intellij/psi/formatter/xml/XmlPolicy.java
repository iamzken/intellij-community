package com.intellij.psi.formatter.xml;

import com.intellij.newCodeFormatting.Wrap;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.impl.source.tree.ElementType;
import com.intellij.psi.impl.source.jsp.jspXml.JspXmlRootTag;

public class XmlPolicy implements XmlFormattingPolicy{
  private final CodeStyleSettings mySettings;
  private final IElementType myXmlTag;

  public XmlPolicy(final CodeStyleSettings settings) {
    mySettings = settings;
    myXmlTag = ElementType.XML_TAG;
  }

  public XmlPolicy(final CodeStyleSettings settings, final IElementType xmlTag) {
    mySettings = settings;
    myXmlTag = xmlTag;
  }

  public boolean indentChildrenOf(final XmlTag parentTag) {
    return !(parentTag instanceof JspXmlRootTag);
  }

  public boolean insertLineBreakBeforeTag(final XmlTag xmlTag) {
    return false;
  }

  public boolean removeLineBreakBeforeTag(final XmlTag xmlTag) {
    return false;
  }

  public int getWrappingTypeForTagEnd(final XmlTag xmlTag) {
    return xmlTag.getSubTags().length > 0 ? Wrap.ALWAYS
           : Wrap.NORMAL;
  }

  public int getWrappingTypeForTagBegin() {
    return Wrap.ALWAYS;
  }

  public boolean isTextElement(XmlTag tag) {
    return false;
  }

  public boolean keepWhiteSpacesInsideTag(final XmlTag tag) {
    return false;
  }

  public int getTextWrap() {
    return mySettings.XML_TEXT_WRAP;
  }

  public int getAttributesWrap() {
    return mySettings.XML_ATTRIBUTE_WRAP;
  }

  public boolean getShouldAlignAttributes() {
    return mySettings.XML_ALIGN_ATTRIBUTES;
  }
  public boolean getShouldAlignText() {
    return mySettings.XML_ALIGN_TEXT;
  }

  public boolean getShouldKeepWhiteSpaces() {
    return mySettings.XML_KEEP_WHITESPACES;
  }

  public boolean getShouldAddSpaceAroundEqualityInAttribute() {
    return mySettings.XML_SPACE_AROUND_EQUALITY_IN_ATTRINUTE;
  }

  public boolean getShouldAddSpaceAroundTagName() {
    return mySettings.XML_SPACE_AROUND_TAG_NAME;
  }

  public int getKeepBlankLines() {
    return mySettings.XML_KEEP_BLANK_LINES;
  }

  public boolean getShouldKeepLineBreaks() {
    return mySettings.XML_KEEP_LINE_BREAKS;
  }

  public CodeStyleSettings getSettings() {
    return mySettings;
  }

  public IElementType getTagType() {
    return myXmlTag;
  }
}

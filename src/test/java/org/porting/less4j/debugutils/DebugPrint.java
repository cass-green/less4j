package org.porting.less4j.debugutils;

import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.Token;
import org.porting.less4j.core.parser.HiddenTokenAwareTree;
import org.porting.less4j.core.parser.LessLexer;
import org.porting.less4j.utils.PrintUtils;

public class DebugPrint {

  private static boolean printIndexes = true;
  private static boolean printComments = false;

  public static void printTokenStream(String expression) {
    LessLexer createLexer = createLexer(expression);
    Token token = createLexer.nextToken();
    int count = 0;
    while (token.getType() != LessLexer.EOF) {
      System.out.println(toString(count++, token));
      token = createLexer.nextToken();
    }
  }

  private static LessLexer createLexer(String expression) {
    ANTLRStringStream input = new ANTLRStringStream(expression);
    LessLexer lexer = new LessLexer(input);
    return lexer;
  }

  public static String toString(int count, Token token) {
    return "(" + count + ") " + PrintUtils.toName(token.getType()) + " " + toOneLine(token.getText());
  }

  private static String toOneLine(String text) {
    return text.replace("\n", "");
  }

  public static void print(HiddenTokenAwareTree ast) {
    print(ast, 0);
  }

  private static void print(HiddenTokenAwareTree tree, int level) {
    indent(level);
    printSingleNode(tree);

    // print all children
    if (tree.getChildren() != null)
      for (Object ie : tree.getChildren()) {
        print((HiddenTokenAwareTree) ie, level + 1);
      }
  }

  public static void indent(int level) {
    for (int i = 0; i < level; i++)
      System.out.print("--");
  }

  public static void printSingleNode(HiddenTokenAwareTree tree) {
    String base = " " + tree.getType() + " " + tree.getText();
    if (printIndexes) {
      String optionalsuffix = " " + tree.getTokenStartIndex() + "-" + tree.getTokenStopIndex() + " " + (tree.getToken() == null ? "" : tree.getToken().getTokenIndex());
      base = base + optionalsuffix;
    }
    if (printComments) {
      String optionalsuffix = " PC:" + printComments(tree.getPreceding()) + " FC:" + printComments(tree.getFollowing());
      base = base + optionalsuffix;
    }
    System.out.println(base);
  }

  public static String printComments(List<Token> list) {
    String result = "" + list.size();
    for (Token token : list) {
      if (token.getType()==LessLexer.COMMENT)
        result+=token.getText();
    }
    return result;
  }

}

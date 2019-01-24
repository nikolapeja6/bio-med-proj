package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;
import org.apache.log4j.*;

%%

%{
	private Symbol new_symbol(int type){
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	private Symbol new_symbol(int type, Object value){
		return new Symbol(type, yyline+1, yycolumn, value);
	}
	
%}

%cup
%line
%column


%xstate COMMENT
%xstate COMMAND


%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" "				{ }
<COMMAND> " "	{ }
"\b"			{ }
<COMMAND> "\b"	{ }
"\t"			{ }
<COMMAND> "\t"	{ }
"\r\n"			{ }
"\f"			{ }
<COMMAND> "\f"	{ }

 
<COMMAND>  "+"		{yybegin(COMMAND); return new_symbol(sym.PLUS, yytext());}
<COMMAND>  "-"		{yybegin(COMMAND); return new_symbol(sym.MINUS, yytext());}
<COMMAND>  "*"		{yybegin(COMMAND); return new_symbol(sym.MULTUPLY, yytext());}
<COMMAND>  "/"		{yybegin(COMMAND); return new_symbol(sym.DIV, yytext());}
<COMMAND>  ":="		{yybegin(COMMAND); return new_symbol(sym.ASSIGN, yytext());}
<COMMAND>  "=="		{yybegin(COMMAND); return new_symbol(sym.EQUAL, yytext());}
<COMMAND>  "!="		{yybegin(COMMAND); return new_symbol(sym.NOT_EQUAL, yytext());}
<COMMAND>  ">"		{yybegin(COMMAND); return new_symbol(sym.GREATER, yytext());}
<COMMAND>  "<"		{yybegin(COMMAND); return new_symbol(sym.LESS, yytext());}
<COMMAND>  ">="		{yybegin(COMMAND); return new_symbol(sym.GREATER_OR_EQUEL, yytext());}
<COMMAND>  "<="		{yybegin(COMMAND); return new_symbol(sym.LESS_OR_EQUEL, yytext());}
<COMMAND>  "AND"	{yybegin(COMMAND); return new_symbol(sym.AND, yytext());}
<COMMAND>  "OR"		{yybegin(COMMAND); return new_symbol(sym.OR, yytext());}
<COMMAND>  ","		{yybegin(COMMAND); return new_symbol(sym.COMMA, yytext());}
<COMMAND>  ";"		{yybegin(COMMAND); return new_symbol(sym.SEMICOLON, yytext());}
<COMMAND>  "."		{yybegin(COMMAND); return new_symbol(sym.DOT, yytext());}
<COMMAND>  "("		{yybegin(COMMAND); return new_symbol(sym.OPEN_PARENTHESES, yytext());}
<COMMAND>  ")"		{yybegin(COMMAND); return new_symbol(sym.CLOSE_PARENTHESES, yytext());}
"!"					{yybegin(COMMAND); return new_symbol(sym.EXCLAMATION, yytext());}

<COMMAND>  "true"|"false" 					{yybegin(COMMAND); return new_symbol(sym.BOOL, yytext());}
 ([a-z]|[A-Z])([a-z|A-Z|0-9|_])* 			{yybegin(COMMAND); return new_symbol(sym.IDENT, yytext());}
<COMMAND> ([a-z]|[A-Z])([a-z|A-Z|0-9|_])* 			{yybegin(COMMAND); return new_symbol(sym.IDENT, yytext());}

<COMMAND>  [0-9]+\.[0-9]+ | [0-9]+				{yybegin(COMMAND); return new_symbol(sym.NUMBER, yytext());}
 

<COMMAND> "\r\n"		{ yybegin(YYINITIAL); return new_symbol(sym.RULE_END, "\\n");}

"//"				{yybegin(COMMENT);}
<COMMENT> . 		{yybegin(COMMENT);}
<COMMENT> "\r\n" 	{yybegin(YYINITIAL);}



. {Logger.getLogger(getClass()).error("Lexical error on line "+(yyline + 1)+" ("+(yycolumn+1)+"): ("+yytext()+").");}


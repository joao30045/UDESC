{
module Lex where

import Token
}

%wrapper "basic"

$digit = [0-9]
$alpha = [a-zA-Z]
@double = $digit+(\.$digit+)
@int = $digit+
@literal = \"[^\"]*\"
@id = ($alpha | \_) ($alpha | $digit | \_)*

tokens :-

  <0> $white+ ;
  <0> @double                {\s -> TDoubleConst (read s)}
  <0> @int                   {\s -> TIntConst (read s)}
  <0> @literal               {\s -> TLiteral (init (tail s))}
  <0> "int"                  {\s -> TInt}
  <0> "string"               {\s -> TString}
  <0> "double"               {\s -> TDouble}
  <0> "void"                 {\s -> TVoid}
  <0> "return"               {\s -> TReturn}
  <0> "if"                   {\s -> TIf}
  <0> "else"                 {\s -> TElse}
  <0> "while"                {\s -> TWhile}
  <0> "print"                {\s -> TPrint}
  <0> "read"                 {\s -> TRead}
  <0> "="                    {\s -> TAssign}
  <0> "{"                    {\s -> TLBrace}
  <0> "}"                    {\s -> TRBrace}
  <0> ","                    {\s -> TComma}
  <0> ";"                    {\s -> TSemi}
  <0> "+"                    {\s -> ADD}
  <0> "-"                    {\s -> SUB}
  <0> "*"                    {\s -> MUL}
  <0> "/"                    {\s -> DIV}
  <0> "("                    {\s -> LPAR}
  <0> ")"                    {\s -> RPAR}
  <0> "<"                    {\s -> LT_}
  <0> ">"                    {\s -> GT_}
  <0> "<="                   {\s -> LEQ}
  <0> ">="                   {\s -> GEQ}
  <0> "=="                   {\s -> EQ_}
  <0> "/="                   {\s -> NEQ}
  <0> "&&"                   {\s -> AND}
  <0> "||"                   {\s -> OR}
  <0> "!"                    {\s -> NOT}
  <0> @id                    {\s -> TId s}

{
-- As ações têm tipo :: String -> Token

testLex = do s <- getLine
             print (alexScanTokens s)
}

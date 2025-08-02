{
module Parser where

import Token
import qualified Lex as L
import qualified AST as A 

}

%name calc
%tokentype { Token }
%error { parseError }
%token 
  'int'       {TInt}
  'string'    {TString}
  'double'    {TDouble}
  'void'      {TVoid}
  'return'    {TReturn}
  'if'        {TIf}
  'else'      {TElse}
  'while'     {TWhile}
  'print'     {TPrint}
  'read'      {TRead}
  '='         {TAssign}
  '{'         {TLBrace}
  '}'         {TRBrace}
  ','         {TComma}
  ';'         {TSemi}
  id          {TId $$}
  intConst    {TIntConst $$}
  doubleConst {TDoubleConst $$}
  literal     {TLiteral $$}
  '+'         {ADD}
  '-'         {SUB}
  '*'         {MUL}
  '/'         {DIV}
  '('         {LPAR}
  ')'         {RPAR}
  '<'         {LT_}
  '>'         {GT_}
  '<='        {LEQ}
  '>='        {GEQ}
  '=='        {EQ_}
  '/='        {NEQ}
  '&&'        {AND}
  '||'        {OR}
  '!'         {NOT}

%%

Programa : ListaFuncoes BlocoPrincipal                        {A.Prog (map fst $1) (map A.c $1) (fst $2) (snd $2) }
         | BlocoPrincipal                                     {A.Prog[] [] (fst $1) (snd $1)}

ListaFuncoes : ListaFuncoes Funcao                            {$1 ++ [$2]}
             | Funcao                                         {[$1]}

Funcao : TipoRetorno id '(' DeclParametros ')' BlocoPrincipal {($2 A.:->: ($4, $1), $6)}
       | TipoRetorno id '(' ')' BlocoPrincipal                {($2 A.:->: ([], $1), $5)}

TipoRetorno : Tipo                                            {$1}
            | 'void'                                          {A.TVoid}

DeclParametros : DeclParametros ',' Parametro                 {$1 ++ [$3]}
               | Parametro                                    {[$1]}

Parametro : Tipo id                                           {($2 A.:#: ($1, 0))}

BlocoPrincipal : '{' Declaracoes ListaCmd '}'                 {($2, $3)}
               | '{' ListaCmd '}'                             {([], $2)}

Declaracoes : Declaracoes Declaracao                          {$1 ++ $2}
            | Declaracao                                      {$1}

Declaracao : Tipo ListaId ';'                                 {map (\j -> (j A.:#: ($1, 0))) $2}
           
Tipo : 'int'                                                  {A.TInt}
     | 'string'                                               {A.TString}
     | 'double'                                               {A.TDouble}

ListaId : ListaId ',' id                                      {$1 ++ [$3]}
        | id                                                  {[$1]}

Bloco : '{' ListaCmd '}'                                      {$2}

ListaCmd : ListaCmd Comando                                   {$1 ++ [$2]}
         | Comando                                            {[$1]}

Comando : CmdSe                                               {$1}
        | CmdEnquanto                                         {$1}
        | CmdAtrib                                            {$1}
        | CmdEscrita                                          {$1}
        | CmdLeitura                                          {$1}
        | ChamadaProc                                         {$1}
        | Retorno                                             {$1}

Retorno : 'return' Expr ';'                                   {A.Ret (Just $2)}
        | 'return' literal ';'                                {A.Ret (Just (A.Lit $2))} 
        | 'return' ';'                                        {A.Ret (Nothing)}

CmdSe : 'if' '(' LogExpr ')' Bloco                            {A.If $3 $5 []}
      | 'if' '(' LogExpr ')' Bloco 'else' Bloco               {A.If $3 $5 $7}

CmdEnquanto : 'while' '(' LogExpr ')' Bloco                   {A.While $3 $5}

CmdAtrib : id '=' Expr ';'                                    {A.Atrib $1 $3}
         | id '=' literal ';'                                 {A.Atrib $1 (A.Lit $3)}

CmdEscrita : 'print' '(' Expr ')' ';'                         {A.Imp $3}
           | 'print' '(' literal ')' ';'                      {A.Imp (A.Lit $3)}

CmdLeitura : 'read' '(' id ')' ';'                            {A.Leitura $3}

ChamadaProc : ChamadaFuncao ';'                               {A.Proc (fst $1) (snd $1)}

ChamadaFuncao : id '(' ListaParametros ')'                    {($1, $3)}
              | id '(' ')'                                    {($1, [])}

ListaParametros : ListaParametros ',' Expr                    {$1 ++ [$3]}
                | ListaParametros ',' literal                 {$1 ++ [A.Lit $3]}
                | Expr                                        {[$1]}
                | literal                                     {[(A.Lit $1)]}

LogExpr : LogExpr '&&' LogTerm                                {A.And $1 $3}
        | LogExpr '||' LogTerm                                {A.Or $1 $3}
        | LogTerm                                             {$1}

LogTerm : '!' LogFactor                                       {A.Not $2}
        | LogFactor                                           {$1}

LogFactor : '(' LogExpr ')'                                   {$2}
          | RelExpr                                           {A.Rel $1}

RelExpr : Expr '<' Expr                                       {A.Rlt $1 $3}
        | Expr '>' Expr                                       {A.Rgt $1 $3}
        | Expr '<=' Expr                                      {A.Rle $1 $3}
        | Expr '>=' Expr                                      {A.Rge $1 $3}
        | Expr '==' Expr                                      {A.Req $1 $3}
        | Expr '/=' Expr                                      {A.Rdif $1 $3}

Expr  : Expr '+' Term                                         {A.Add $1 $3}
      | Expr '-' Term                                         {A.Sub $1 $3}
      | Term                                                  {$1}

Term  : Term '*' Factor                                       {A.Mul $1 $3}
      | Term '/' Factor                                       {A.Div $1 $3}
      | Factor                                                {$1}

Factor : '(' 'int' ')' Factor                                 {A.DoubleInt $4}
       | doubleConst                                          {A.Const (A.CDouble $1)}
       | intConst                                             {A.Const (A.CInt $1)}
       | id                                                   {A.IdVar $1}
       | '(' Expr ')'                                         {$2}
       | ChamadaFuncao                                        {A.Chamada (fst $1) (snd $1)}

{
parseError :: [Token] -> a
parseError s = error ("Parse error:" ++ show s)

makeAST code = calc (L.alexScanTokens code)

main :: IO ()
main = do
    putStrLn "Digite o caminho do arquivo com o código:"
    path <- getLine
    code <- readFile path
    writeFile "AST.txt" (show $ makeAST code)
}
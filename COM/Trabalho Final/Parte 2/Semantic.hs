module Semantic where
import AST
import Monad
import Parser
import GHC.Exts.Heap (GenClosure(fun))


formatM :: Show a => M a -> String
formatM (MS (s, a)) = "Mensagens:\n" ++ s ++ "\nAST: " ++ show a

emitError :: Show a => String -> a -> M ()
emitError msg line = 
  MS ("Erro : " ++ msg ++ " em:\n\t" ++ show line ++ "\n\n", ())

emitWarning :: Show a => String -> a -> M ()
emitWarning msg line =
  MS ("Advertência : " ++ msg ++ " em:\n\t" ++ show line++ "\n\n", ())

lookupVar :: Id -> [Var] -> Maybe Tipo
lookupVar id [] = Nothing
lookupVar id ((idd :#: (varType, _)) : xs)
  | id == idd = Just varType
  | otherwise = lookupVar id xs

lookupFuncao :: Id -> [Funcao] -> Maybe Funcao
lookupFuncao id [] = Nothing
lookupFuncao id (fun@(idd :->: (_, _)) : xs)
  | id == idd = Just fun
  | otherwise = lookupFuncao id xs

exprTypeCheck :: [Funcao] -> [Var] -> Expr -> M (Tipo, Expr)
exprTypeCheck listaFuncoes _ (Const (CInt n))     = pure (TInt, Const (CInt n))
exprTypeCheck listaFuncoes _ (Const (CDouble n))  = pure (TDouble, Const (CDouble n))
exprTypeCheck listaFuncoes _ (Lit s)  = pure (TString, Lit s)

exprTypeCheck listaFuncoes vars (IdVar id) = 
  case lookupVar id vars of
    Just varType -> pure(varType, IdVar id)
    Nothing -> do 
      emitError ("variável " ++ show id ++ " não declarada") (IdVar id)
      return (TVoid, IdVar id)

exprTypeCheck listaFuncoes vars (Neg e)  = do
  (t, new_e) <- exprTypeCheck listaFuncoes vars e
  if (t == TString)
    then do
      emitError "String não pode ser negativa" e
      return (TVoid, e)
  else pure(t, new_e)

exprTypeCheck listaFuncoes vars (IntDouble e) = do
  (t, new_e) <- exprTypeCheck listaFuncoes vars e
  if t == TInt 
    then pure (TDouble, IntDouble new_e)
    else do
      emitError "Não dá para converter non-int em double" e
      return (t, new_e)

exprTypeCheck listaFuncoes vars (DoubleInt e) = do
  (t, new_e) <- exprTypeCheck listaFuncoes vars e
  if t == TDouble 
    then pure (TInt, DoubleInt new_e)
    else do
      emitError "Não dá para converter non-double em int" e
      return (t, new_e)

exprTypeCheck listaFuncoes vars (Add e1 e2) = checkExpr listaFuncoes vars e1 e2 Add
exprTypeCheck listaFuncoes vars (Sub e1 e2) = checkExpr listaFuncoes vars e1 e2 Sub
exprTypeCheck listaFuncoes vars (Mul e1 e2) = checkExpr listaFuncoes vars e1 e2 Mul
exprTypeCheck listaFuncoes vars (Div e1 e2) = checkExpr listaFuncoes vars e1 e2 Div
exprTypeCheck listaFuncoes vars (Chamada id exprList) = 
  case lookupFuncao id listaFuncoes of
    Just funcao -> do
      let (_ :->: (_, returnType)) = funcao
      new_exprList <- checkFuncaoChamada listaFuncoes vars funcao funcao exprList
      return (returnType, Chamada id new_exprList)
    Nothing -> do
      emitError ("Função" ++ show id ++ " não declarada") (Chamada id exprList)
      return (TVoid, Chamada id exprList)

exprTypeCheck _ _ expr = do 
  emitError "Checagem de tipo não implementada para essa expressão" expr
  return (TVoid, expr)

checkExpr :: [Funcao] -> [Var] -> Expr -> Expr -> (Expr -> Expr -> Expr) -> M (Tipo, Expr)
checkExpr listaFuncoes vars e1 e2 operation = do
  (t1, new_e1) <- exprTypeCheck listaFuncoes vars e1
  (t2, new_e2) <- exprTypeCheck listaFuncoes vars e2
  let op = operation e1 e2
  let new_op = operation new_e1 new_e2
  case (t1, t2) of
    (TString, _) -> do 
      emitError ("String não funciona com " ++ show t2) op
      return (TString, new_op)
    (_, TString) -> do 
      emitError ("Não funciona " ++ show t1 ++ " com string") op
      return (TString, new_op)
    (TInt, TInt) -> pure (TInt, new_op)
    (TDouble, TDouble) -> pure (TDouble, new_op)
    (TInt, TDouble) -> do 
      emitWarning "Coerção de int para double" op
      return (TDouble, operation (IntDouble new_e1) new_e2)
    (TDouble, TInt) -> do
      emitWarning "Coerção de int para double" op
      return (TDouble, operation new_e1 (IntDouble new_e2))
    _ -> do 
      emitError "Tipos não suportados" op
      return (TVoid, new_op)

exprRTypeCheck :: [Funcao] -> [Var] -> ExprR -> M ExprR
exprRTypeCheck listaFuncoes vars (Req e1 e2)  = checkExprR listaFuncoes vars e1 e2 Req
exprRTypeCheck listaFuncoes vars (Rdif e1 e2) = checkExprR listaFuncoes vars e1 e2 Rdif
exprRTypeCheck listaFuncoes vars (Rlt e1 e2)  = checkExprR listaFuncoes vars e1 e2 Rlt
exprRTypeCheck listaFuncoes vars (Rgt e1 e2)  = checkExprR listaFuncoes vars e1 e2 Rgt
exprRTypeCheck listaFuncoes vars (Rle e1 e2)  = checkExprR listaFuncoes vars e1 e2 Rle
exprRTypeCheck listaFuncoes vars (Rge e1 e2)  = checkExprR listaFuncoes vars e1 e2 Rge

checkExprR :: [Funcao] -> [Var] -> Expr -> Expr -> (Expr -> Expr -> ExprR) -> M ExprR
checkExprR listaFuncoes vars e1 e2 operation = do
  (t1, new_e1) <- exprTypeCheck listaFuncoes vars e1
  (t2, new_e2) <- exprTypeCheck listaFuncoes vars e2
  let op = operation e1 e2
  let new_op = operation new_e1 new_e2
  case (t1, t2) of
    (TString, TString) -> pure new_op
    (TString, _) -> do 
      emitError ("Não dá para comparar string com " ++ show t2) op
      return new_op
    (_, TString) -> do
      emitError ("Não dá para comparar " ++ show t1 ++ " com string") op
      return new_op
    (TInt, TInt) -> pure new_op
    (TDouble, TDouble) -> pure new_op
    (TInt, TDouble) -> do 
      emitWarning "Coerção de int para double" op
      return (operation (IntDouble new_e1) new_e2)
    (TDouble, TInt) -> do 
      emitWarning "Coerção de int para double" op
      return (operation new_e1 (IntDouble new_e2))
    _ -> do 
      emitError "Tipos não suportados" op
      return new_op
  
exprLTypeCheck :: [Funcao] -> [Var] -> ExprL -> M ExprL
exprLTypeCheck listaFuncoes vars (And e1 e2) = checkExprL listaFuncoes vars e1 e2 And
exprLTypeCheck listaFuncoes vars (Or e1 e2)  = checkExprL listaFuncoes vars e1 e2 Or
exprLTypeCheck listaFuncoes vars (Not e1)    = exprLTypeCheck listaFuncoes vars e1
exprLTypeCheck listaFuncoes vars (Rel e1)    = pure Rel <*> exprRTypeCheck listaFuncoes vars e1

checkExprL :: [Funcao] -> [Var] -> ExprL -> ExprL -> (ExprL -> ExprL -> b) -> M b
checkExprL listaFuncoes vars e1 e2 operation = do
  new_e1 <- exprLTypeCheck listaFuncoes vars e1
  new_e2 <- exprLTypeCheck listaFuncoes vars e2
  let new_op = operation new_e1 new_e2
  pure new_op

checkBlockVars :: [Var] -> [Var] -> M [Var]
checkBlockVars [] _ = pure []
checkBlockVars (var : xs) varList = do
  tail <- checkBlockVars xs varList
  if varFreq var varList > 1
    then do
      emitError ("Variável " ++ show var ++ " declarada múltiplas vezes") varList
      pure (var : tail)
  else
    pure (var : tail)


checkBlock :: [Funcao] -> Maybe Funcao -> [Var] -> Bloco -> M Bloco
checkBlock listaFuncoes function vars (cmd : block) = do
  new_cmd <- checkCommand listaFuncoes function vars cmd
  new_block <- checkBlock listaFuncoes function vars block
  pure (new_cmd : new_block)

checkBlock _ function vars [] = pure []

checkCommand :: [Funcao] -> Maybe Funcao -> [Var] -> Comando -> M Comando

checkCommand listaFuncoes funcao vars (If exprL b1 b2) = do
  new_e <- exprLTypeCheck listaFuncoes vars exprL
  new_b1 <- checkBlock listaFuncoes funcao vars b1
  new_b2 <- checkBlock listaFuncoes funcao vars b2
  pure(If new_e new_b1 new_b2)

checkCommand listaFuncoes funcao vars (While exprL b) = do
  new_e <- exprLTypeCheck listaFuncoes vars exprL
  new_b <- checkBlock listaFuncoes funcao vars b
  pure(While new_e new_b)

checkCommand listaFuncoes _ vars (Imp expr) = do
  (_, new_e) <- exprTypeCheck listaFuncoes vars expr
  pure(Imp new_e)


checkCommand listaFuncoes _ vars (Atrib var expr) = do
  (eType, new_expr) <- exprTypeCheck listaFuncoes vars expr
  let cmd = Atrib var expr
  let new_cmd = Atrib var new_expr
  case lookupVar var vars of
    Just varType -> do
      case (varType, eType) of
        (TString, TString) -> pure new_cmd
        (TString, _) -> do
          emitError ("Não foi possível atribuir " ++ show eType ++ " para string") cmd
          return new_cmd
        (_, TString) -> do
          emitError ("Não foi possível atribuir string para " ++ show eType) cmd
          return new_cmd
        (TInt, TInt) -> pure new_cmd
        (TDouble, TDouble) -> pure new_cmd
        (TInt, TDouble) -> do
          emitWarning "Coerção de double para int" cmd
          return (Atrib var (DoubleInt expr))
        (TDouble, TInt) -> do
          emitWarning "Coerção de int para double" cmd
          return (Atrib var (IntDouble expr))
        _ -> do
          emitError "Tipos não suportados" cmd
          return new_cmd
    Nothing -> do
      emitError ("Variável " ++ show var ++ " não declarada") new_cmd
      return cmd

checkCommand _ _ vars (Leitura var) = do
  let cmd = Leitura var
  case lookupVar var vars of
    Just varType -> pure cmd
    Nothing -> do
      emitError ("Variável " ++ show var ++ " não declarada") cmd
      return cmd

checkCommand listaFuncoes funcao vars (Ret maybeExpr) = do
  let returnType = case funcao of
        Just (id :->: (_, retType)) -> retType
        Nothing -> TVoid
  let cmd = Ret maybeExpr
  case maybeExpr of
    Just e -> do
      (eType, new_e) <- exprTypeCheck listaFuncoes vars e
      case (eType, returnType) of
        (TString, TString) -> pure (Ret (Just new_e))
        (TString, _) -> do
          emitError (show eType ++ " não compatível com tipo de retorno esperado " ++ show returnType) cmd
          return (Ret (Just new_e))
        (_, TString) -> do
          emitError (show eType ++ " não compatível com tipo de retorno esperado " ++ show returnType) cmd
          return (Ret (Just new_e))
        (TInt, TInt) -> pure (Ret (Just new_e))
        (TDouble, TDouble) -> pure (Ret (Just new_e))
        (TInt, TDouble) -> do
          emitWarning "Coerção de double para int" cmd
          return (Ret (Just (DoubleInt new_e)))
        (TDouble, TInt) -> do
          emitWarning "Coerção de int para double" cmd
          return (Ret (Just (IntDouble new_e)))
        _ -> do
          emitError "Tipos não suportados" cmd
          return (Ret (Just new_e))
    Nothing ->
      case returnType of
        TVoid -> pure cmd
        _ -> do
          emitError "Non-void funções esperam uma expressão de retorno" cmd
          pure cmd

checkCommand listaFuncoes funcao vars (Proc id exprList) =
  case lookupFuncao id listaFuncoes of
    Just fun -> do
      new_exprList <- checkFuncaoChamada listaFuncoes vars fun fun exprList
      return (Proc id new_exprList)
    Nothing -> do
      emitError ("Função " ++ show id ++ "não declarada") (Proc id exprList)
      return (Proc id exprList)

checkFuncaoChamada :: [Funcao] -> [Var] -> Funcao -> Funcao -> [Expr] -> M [Expr]

checkFuncaoChamada listaFuncoes vars originalFuncao (_ :->: ([], _)) [] = pure []

checkFuncaoChamada listaFuncoes vars originalFuncao funcao exprList = do
  let (funId :->: (funcaoVarList, returnType)) = funcao
  if length funcaoVarList < length exprList 
    then do
      emitError ("Muitos argumentos (" ++ show exprList ++ ") na função") originalFuncao
      return exprList
  else if length funcaoVarList > length exprList
    then do
      emitError ("Poucos argumentos (" ++ show exprList ++ ") na função") originalFuncao
      return exprList
  else do
    let (funcaoVar : rest) = funcaoVarList
    let (id :#: (varType, _mem)) = funcaoVar
    let (expr : exprTail) = exprList
    let nxtFuncoes = funId :->: (rest, returnType)
    (eType, new_expr) <- exprTypeCheck listaFuncoes vars expr
    newTail <- checkFuncaoChamada listaFuncoes vars originalFuncao nxtFuncoes exprTail
    case (varType, eType) of
      (TString, TString) -> pure (new_expr : newTail)
      (TString, _) -> do
        emitError ("Não dá para coercer" ++ show eType ++ "para string") originalFuncao
        return (new_expr : newTail)
      (_, TString) -> do
        emitError ("Não dá para coercer string em " ++ show eType) originalFuncao
        return (new_expr : newTail)
      (TInt, TInt) -> pure (new_expr : newTail)
      (TDouble, TDouble) -> pure (new_expr : newTail)
      (TInt, TDouble) -> do
        emitWarning "Coerção de double para int" originalFuncao
        return (DoubleInt new_expr : newTail)
      (TDouble, TInt) -> do
        emitWarning "Coerção de int para double" originalFuncao
        return (IntDouble new_expr : newTail)
      _ -> do
        emitError "Tipos não suportados" originalFuncao
        return (new_expr : newTail)

funcaoFreq :: Funcao -> [Funcao] -> Int
funcaoFreq funcao [] = 0
funcaoFreq funcao@(id :->: (_, _)) ((id2 :->: (_, _)) : xs) 
  | id == id2 = 1 + funcaoFreq funcao xs
  | otherwise = funcaoFreq funcao xs

varFreq :: Var -> [Var] -> Int
varFreq var [] = 0
varFreq var@(id :#: (_, _)) ((id2 :#: (_, _)) : xs) 
  | id == id2 = 1 + varFreq var xs
  | otherwise = varFreq var xs

checkFuncao :: [Funcao] -> Funcao -> (Id, [Var], Bloco) -> M (Funcao, (Id, [Var], Bloco))
checkFuncao listaFuncoes funcao (id, vars, block) = do
  new_block <- checkBlock listaFuncoes (Just funcao) vars block
  if funcaoFreq funcao listaFuncoes > 1
    then do
      emitError ("Função " ++ show funcao ++ " declarada várias vezes") listaFuncoes
      pure (funcao, (id, vars, new_block))
  else 
      pure (funcao, (id, vars, new_block))


checkListaFuncoes :: [Funcao] -> [Funcao] -> [(Id, [Var], Bloco)] -> M ([Funcao], [(Id, [Var], Bloco)])
checkListaFuncoes _ [] [] = pure ([], [])
checkListaFuncoes listaFuncoes (funcao : funcaoTail) ((id, vars, block) : rest) = do
  newVars <- checkBlockVars vars vars
  (new_Funcoes, new_FuncoesBody) <- checkFuncao listaFuncoes funcao (id, newVars, block)
  (tail_ListaFuncoes, tail_funcaoBodyList) <- checkListaFuncoes listaFuncoes funcaoTail rest
  pure (new_Funcoes : tail_ListaFuncoes, new_FuncoesBody : tail_funcaoBodyList)

checkProgram :: Programa -> M Programa
checkProgram (Prog listaFuncoes funcaoBodyList mainBlockVars mainBlock) = do
  newMainBlockVars <- checkBlockVars mainBlockVars mainBlockVars
  (new_ListaFuncoes, new_funcaoBodyList) <- checkListaFuncoes listaFuncoes listaFuncoes funcaoBodyList
  new_mainBlock <- checkBlock listaFuncoes Nothing mainBlockVars mainBlock
  pure (Prog new_ListaFuncoes new_funcaoBodyList mainBlockVars new_mainBlock)

compile :: IO ()
compile = do
  putStrLn "Digite o nome do arquivo:"
  fileName <- getLine
  code <- readFile fileName
  writeFile "saida.txt" (formatM $ checkProgram $ makeAST code)
  putStrLn "Saída gravada no arquivo 'saida.txt'"
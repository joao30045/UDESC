Lista busca_todos( Lista l, void *info, int (*compara)(void*,void*) ){
	Elemento *aux = l.cabeca;
	int cont = 0;
	Lista indices;
	inicializa_lista(&indices, sizeof(int));
	while(aux != NULL){
		if(compara(info, aux->info) == 0)
			insere_fim(&indices, &cont);
		cont++;
		aux = aux->proximo;
	}
	return indices;
}
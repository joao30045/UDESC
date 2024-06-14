//a)
void mostra_invertido(Lista l, void (*mostra)(void*)){
	if(lista_vazia(l))
		printf("Lista vazia!/n");
	else{
		Pilha p1;
		inicializa_pilha(&p1, l.qtd, sizeof(void*));
		Elemento *aux = l.cabeca;
		while(aux != NULL){
			empilha(&p1, &aux->info);
			aux = aux->proximo;
		}
		while(!pilha_vazia(p1)){
			void *temp;
			desempilha(&p1, &temp);  
			mostra(temp);
		}
		desaloca_pilha(&p1);
	}
}

//b)
void mostra_invertido(Lista l, void (*mostra)(void*)){
	if(lista_vazia(l))
		printf("Lista vazia!/n");
	else
		percorre(l.cabeca, mostra);
}

void percorre(Elemento *p, void (*mostra)(void*)){
	if(p != NULL){
		percorre(p->proximo, mostra);
		mostra(p->info);
	}
}
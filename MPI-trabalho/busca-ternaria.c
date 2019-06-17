#include <stdio.h> 
#include <stdlib.h>
 
int busca(int inicio, int fim, int num, int *vet);
 
int main(void)
{
 
    int inicio, fim;
    int num;
    int vet[10]={2,3,6,9,12,16,18,25,36,40};
    int indice;
    printf("Digite o valor de que deseja procurar no vetor: ");
    scanf("%d",&num);
 
    inicio=0;
    fim=9;
  indice = busca(inicio,fim,num,vet);
if(indice!=-1)
printf("\nValor %d encontrado no vetor.\n",vet[indice]);
    else 
printf("\nO valor %d  nao existe no vetor.\n", num);
  getchar();
    getchar();
    return(0);
}
int  busca(int inicio, int fim, int num, int *vet)
   {
int meio;
printf("\nvalor do inicio e: %d\n", inicio);
        printf("\nvalor do fim e: %d\n", fim);
 
while (inicio <= fim)
       {
meio = (inicio + fim) /2;
printf("\nvalor do inicio e: %d\n", inicio);
printf("\nvalor do fim e: %d\n", fim);
printf("\nvalor meio e: %d\n", meio);
if (vet[meio] == num)//o numero que estou procurando é = num do meio?
return meio;
 
else 
 
if (vet[meio] > num)//o numero que estou procurando é maior num do meio?
 
fim = meio -1;
 
else
 
//if(nome[meio] < num)//o numero que estou procurando é menor num do meio?
 
inicio = meio + 1;
  }
return -1;
}
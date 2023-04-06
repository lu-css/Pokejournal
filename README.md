# Pesquisa sobre: PokéAPI

<div align="center">
<img src="img/Pokemon.png" height="250px">
</div>

### 3°A Etim - Desenvolvimento de Sistemas
> Débora Liberato Ribeiro ╰(*°▽°*)╯<br>
> Pedro Luís Anghievisck ( •_•)>⌐■-■

Editor de Texto: https://stackedit.io/app#

```java
public class InformacoesGerais{
  public final String Documentacao = "https://pokeapi.co/docs/v2"; 
  public final String API = "https://pokeapi.co/api/v2/";
  public final String Autenticacao = "Não tem";
}
```


<h1>Principais Métodos:</h1>
|  Nome | Identificação | Tipo |           Descrição           |
|:-----:|:-------------:|:----:|:-----------------------------:|
|Pokémon|    pokemon    |  GET | Mostra as informacões gerais do pokémon.<br>Provavelmente o principal método do projeto, já que uma Pokédex<br>precisa das informações gerais acima de tudo|
|Berries|     berry     |  GET | Retorna as frutinhas do jogo,<br>não será usada no aplicativo  |
|BerryFirmnesses|berry-firmness| GET | Diz a consistência que a frutinha tem|
| BerryFlavors | berry-flavor| GET | Descreve se o Pokémon sofre dano ou benefício ao comer a fruta com base na sua espécie| 
|Contest Types| contest-type| GET | Os tipos de concurso são categorias que os juízes usam para avaliar a condição de um Pokémon em concursos de Pokémon|



*Atributos:*

# Pesquisa sobre: PokéAPI
### 3°A Etim - Desenvolvimento de Sistemas
> Débora Liberato Ribeiro <br>
> Pedro Luís Anghievisck ( •_•)>⌐■-■

Editor de Texto: https://stackedit.io/app#

```java
public class InformacoesGerais{
  public final String Documentacao = "https://pokeapi.co/docs/v2"; 
  public final String API = "https://pokeapi.co/api/v2/";
  public final String Autenticacao = "Não tem";
}
```

*Métodos:*
|  Nome | Identificação | Tipo |           Descrição           |
|:-----:|:-------------:|:----:|:-----------------------------:|
|Berries|     berry     |  GET | Retorna as frutinhas do jogo,<br>não será usada no aplicativo  |
|Pokémon|    pokemon    |  GET | Mostra as informacões gerais do pokémon.<br>Provavelmente o principal método do projeto, já que uma Pokédex<br>precisa das informações gerais acima de tudo|

*Atributos:*
|     Nome     | Identificação | Dicionário de Dados: |           Descrição          |
|:------------:|:-------------:|:--------------------:|:----------------------------:|
| ID (Pokémon) |       id      |        integer       | Número do pokémon na pokédex |
|Nome (Pokémon)|      nome     |        string        |        Nome do Pokémon       |

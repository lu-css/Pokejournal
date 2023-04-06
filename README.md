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

<details open="pokemon">
  <summary><b>POKÉMON:</summary></b>
  
  
  - [<b>Identificação:</b> pokemon](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b>  Mostra as informacões gerais do pokémon. Provavelmente o principal método do projeto, já que uma Pokédex<br>precisa das informações gerais acima de tudo](#2)
  - [<b>Parametros:</b> _name_ ou _id_](#3)

</details>

<details open="barries">
  <summary><b>BARRY</summary></b>
  
 1. Berries
  - [<b>Identificação:</b> berry](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Retorna as frutinhas do jogo](#2)
  
  2. Berry Firmnesses
  - [<b>Identificação:</b> berry-firmness](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Diz a consistência que a frutinha tem.  ](#2)
  
  3. Berry Flavorss
  - [<b>Identificação:</b> berry-flavor](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Descreve se o Pokémon sofre dano ou benefício ao comer a fruta com base na sua espécie. ](#2)
</details>

<details open="ContestType"> 
<summary><b>CONTEST TIPE:</summary></b>

  1. Contest Type
  - [<b>Identificação:</b> contest-type](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> São categorias em que as condições do Pokémon é avaliado nos concursos. ](#2)
  
  2. Contest Effects
  - [<b>Identificação:</b> contest-effec](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Efeitos de movimentos usados em competições. ](#2)
  
   3. Super Contest Effect
  - [<b>Identificação:</b> super-contest-effect](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Efeitos de movimentos usados em super competições. ](#2)

</details>

<details open="Encounters">
 <summary><b>ENCOUNTERS:</summary></b>
  
   1. Encounter Methods
   - [<b>Identificação:</b> encounter-method ](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Métodos pelos quais o jogador pode encontrar Pokémon na natureza, por exemplo, caminhar na grama alta. ](#2)
 
   2. Encounter Conditions
  - [<b>Identificação:</b> encounter-condition](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Condições que afetam qual pokémon pode aparecer na natureza, por exemplo, dia ou noite. ](#2)
  
   3. Encounter Condition Values
  - [<b>Identificação:</b> encounter-condition-value](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Os valores de condição de encontro são os vários estados que uma condição de encontro pode ter, ou seja, a hora do dia pode ser dia ou noite. ](#2)
  
</details>


<details open="Evolution">
 <summary><b>ENCOUNTERS:</summary></b>
  
  1. Evolution Chains
  - [<b>Identificação:</b> evolution-chain](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Cadeias de evolução são essencialmente árvores genealógicas. Eles começam com o estágio mais baixo dentro de uma família e detalham as condições de evolução para cada um, bem como os Pokémon para os quais eles podem evoluir na hierarquia.  ](#2)
  
  
   2. Evolution Triggers 
  - [<b>Identificação:</b> evolution-trigger](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Gatilhos de evolução são os eventos e condições que fazem com que um Pokémon evolua.](#2)
  
</details>






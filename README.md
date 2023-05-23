# Pesquisa sobre: PokéAPI

<div align="center">
<img src="img/Pokemon.png" height="250px">
</div>

### 3°A Etim - Desenvolvimento de Sistemas
> Débora Liberato Ribeiro ╰(*°▽°*)╯<br>
> Pedro Luís Anghievisck ( •_•)>⌐■-■

```java
public class InfoGeral{
  public final String Documentacao = "https://pokeapi.co/docs/v2"; 
  public final String API = "https://pokeapi.co/api/v2/";
  public final String Autenticacao = "Não tem";
}
```

# <div align="center">_Principais Métodos:_</div>

<details open="pokemon">
  <summary><b>POKÉMON: SERÁ A UTILIZADA</summary></b>
  
  
 1. Abilities  
  - [<b>Identificação:</b> ability](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> As habilidades fornecem efeitos passivos para Pokémon em batalha ou no mundo superior. Os Pokémon têm várias habilidades possíveis, mas podem ter apenas uma habilidade por vez. ](#2)

    
 2. Characteristicas  
  - [<b>Identificação:</b> characteristic](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> As características indicam qual estatística contém o IV mais alto de um Pokémon. A Característica de um Pokémon é determinada pelo restante de seu IV mais alto dividido por 5 (gene_modulo)](#2)

    
 3. Egg Groups  
  - [<b>Identificação:</b> egg-group](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Egg Groups são categorias que determinam quais Pokémon são capazes de cruzar. Os Pokémon podem pertencer a um ou dois Grupos de Ovos.](#2)
  
    
 4. Genders  
  - [<b>Identificação:</b> gender](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Os gêneros foram introduzidos na Geração II para fins de criação de Pokémon, mas também podem resultar em diferenças visuais ou até mesmo em diferentes linhas evolutivas.  ](#2)

    
 5. Growth Rates  
  - [<b>Identificação:</b> growth-rate](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> As taxas de crescimento são a velocidade com que os Pokémon ganham níveis através da experiência ](#2)

    
 6. Natures  
  - [<b>Identificação:</b> nature](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> As naturezas influenciam como as estatísticas de um Pokémon crescem.](#2)

    
 7. Pokeathlon Stats  
  - [<b>Identificação:</b> pokeathlon-stat](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Pokeathlon Stats são atributos diferentes do desempenho de um Pokémon em Pokéathlons. Nos Pokéathlons, as competições acontecem em diferentes percursos; um para cada uma das diferentes estatísticas do Pokéathlon.](#2)
 
 8. Pokémon  
  - [<b>Identificação:</b> pokemon](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b>  Mostra as informacões gerais do pokémon. Provavelmente o principal método do projeto, já que uma Pokédex<br>precisa das informações gerais acima de tudo, SERÁ A UTILIZADA](#2)
  - [<b>Parametros:</b> _name_ ou _id_](#3)
    
 9. Pokemon Location Areas  
  - [<b>Identificação:</b> pokemon](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> As áreas de localização de Pokémon são áreas onde os Pokémon podem ser encontrados.](#2)
    
 10. Pokemon Colors
  - [<b>Identificação:</b> pokeathlon-stat](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Cores usadas para classificar Pokémon em um Pokédex. A cor listada no Pokédex geralmente é a cor mais aparente ou que cobre o corpo de cada Pokémon. Nenhuma categoria laranja existe; Pokémon que são principalmente laranjas são listados como vermelhos ou marrons.](#2)
    
11. Pokemon Forms  
  - [<b>Identificação:</b> pokemon-form](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Alguns Pokémon podem aparecer em uma das várias formas visualmente diferentes. Essas diferenças são puramente cosméticas. Para variações dentro de uma espécie de Pokémon, que diferem em mais do que apenas visuais, a entidade 'Pokémon' é usada para representar tal variedade. ](#2)

    
12. Pokemon Habitats 
  - [<b>Identificação:</b> pokemon-habitat](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Habitats são geralmente terrenos diferentes em que os Pokémon podem ser encontrados, mas também podem ser áreas designadas para Pokémon raros ou lendários.](#2)

    
13. Pokemon Shapes  
  - [<b>Identificação:</b> pokemon-shape](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Formas usadas para classificar Pokémon em um Pokédex.](#2)
 
    
14. Pokemon Species  
  - [<b>Identificação:</b> pokemon-species](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Uma espécie de Pokémon forma a base para pelo menos um Pokémon. Os atributos de uma espécie de Pokémon são compartilhados por todas as variedades de Pokémon dentro da espécie. Um bom exemplo é Wormadam; Wormadam é a espécie que pode ser encontrada em três variedades diferentes, Wormadam-Trash, Wormadam-Sandy e Wormadam-Plant.](#2)

    
15. Stats  
  - [<b>Identificação:</b> stat](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> As estatísticas determinam certos aspectos das batalhas. Cada Pokémon tem um valor para cada stat que cresce à medida que ganha níveis e pode ser alterado momentaneamente por efeitos nas batalhas. ](#2)
 
    
16. Types 
  - [<b>Identificação:</b> type](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Tipos são propriedades de Pokémon e seus movimentos. Cada tipo tem três propriedades: contra quais tipos de Pokémon é supereficaz, contra quais tipos de Pokémon não é muito eficaz e contra quais tipos de Pokémon é completamente ineficaz.](#2)

</details>

<details open="barries">
  <summary><b>BERRY:</summary></b>
  
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
<summary><b>CONTEST TYPE:</summary></b>

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
 <summary><b>EVOLUTION:</summary></b>
  
  1. Evolution Chains
  - [<b>Identificação:</b> evolution-chain](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Cadeias de evolução são essencialmente árvores genealógicas. Eles começam com o estágio mais baixo dentro de uma família e detalham as condições de evolução para cada um, bem como os Pokémon para os quais eles podem evoluir na hierarquia.  ](#2)
  
  
   2. Evolution Triggers 
  - [<b>Identificação:</b> evolution-trigger](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Gatilhos de evolução são os eventos e condições que fazem com que um Pokémon evolua.](#2)
  
</details>

<details open="Games">
 <summary><b>GAMES:</summary></b>
  
 1. Generations 
  - [<b>Identificação:</b> generation](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Uma geração é um agrupamento dos jogos Pokémon que os separa com base no Pokémon que eles incluem. A cada geração, um novo conjunto de Pokémon, movimentos, habilidades e tipos que não existiam na geração anterior são lançados. ](#2)
  
 2. Pokedexes 
  - [<b>Identificação:</b> pokedex](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b>  Um Pokédex é um dispositivo de enciclopédia eletrônica portátil; aquele que é capaz de gravar e reter informações dos vários Pokémon em uma determinada região, com exceção do índice nacional e alguns índices menores relacionados a partes de uma região.](#2)
  
 3. Version 
  - [<b>Identificação:</b> version](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b>  Versões dos jogos, por exemplo, Vermelho, Azul ou Amarelo.](#2)
  
 4. Version Groups 
  - [<b>Identificação:</b> version-group](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b>  Os grupos de versões categorizam versões altamente semelhantes dos jogos.](#2)
  
</details>

<details open="Item">
 <summary><b>ITEM:</summary></b>
  
 1.Item 
  - [<b>Identificação:</b> item](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Um item é um objeto nos jogos que o jogador pode pegar, guardar na bolsa e usar de alguma maneira. Eles têm vários usos, incluindo cura, aumento de energia, ajuda na captura de Pokémon ou acesso a uma nova área. ](#2)
  
 2.Item Attributes 
  - [<b>Identificação:</b> item-attribute](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Os atributos do item definem aspectos particulares dos itens, por exemplo, "utilizável em batalha" ou "consumível". ](#2)
  
 3.Item Categories 
  - [<b>Identificação:</b> item-categoryItem](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b>  As categorias de itens determinam onde os itens serão colocados na bolsa do jogador.](#2)
  
 4.Item Fling Effects 
  - [<b>Identificação:</b> item-fling-effect](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b>  Os vários efeitos do movimento "Fling" quando usado com itens diferentes.](#2)
  
 5.Item Pockets 
  - [<b>Identificação:</b> item-pocket](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b>  Bolsos dentro da bolsa dos jogadores usados para armazenar itens por categoria.](#2) 
</details>

<details open="Locations">
 <summary><b>LOCATIONS:</summary></b>
  
 1. Locations 
  - [<b>Identificação:</b> location](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b>  Locais que podem ser visitados dentro dos jogos. Os locais constituem porções consideráveis de regiões, como cidades ou rotas.](#2)

 2. Location Areas 
  - [<b>Identificação:</b> location-area](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Áreas de localização são seções de áreas, como pisos de um prédio ou caverna. Cada área tem seu próprio conjunto de possíveis encontros Pokémon. ](#2)
  
 3. Pal Park Areas 
  - [<b>Identificação:</b> pal-park-area](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b>  Áreas usadas para agrupar encontros de Pokémon no Pal Park. Eles são como habitats específicos de Pal Park .](#2)
  
 4. Regions  
  - [<b>Identificação:</b> region](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Uma região é uma área organizada do mundo Pokémon. Na maioria das vezes, a principal diferença entre as regiões são as espécies de Pokémon que podem ser encontradas dentro delas ](#2)
</details>


<details open="Move">
 <summary><b>MOVE:</summary></b>
  
1.Move
  - [<b>Identificação:</b> move](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b>  Os movimentos são as habilidades dos Pokémon em batalha. Na batalha, um Pokémon usa um movimento a cada turno. Alguns movimentos (incluindo aqueles aprendidos por Hidden Machine) também podem ser usados fora da batalha, geralmente com o propósito de remover obstáculos ou explorar novas áreas.](#2)
  
2.Move Ailments 
  - [<b>Identificação:</b> move-ailment](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b>  Move Ailments são condições de status causadas por movimentos usados durante a batalha.](#2)
  
3.Move Battle Styles 
  - [<b>Identificação:</b> move-battle-style](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b>  Estilos de movimentos quando usados no Battle Palace.](#2)
  
4.Move Categories
  - [<b>Identificação:</b> move-category](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b> Categorias muito gerais que agrupam vagamente efeitos de movimento.](#2)
  
5.Move Damage Classes 
  - [<b>Identificação:</b> move-damage-class](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b>  Os movimentos de classe de dano podem ter, por exemplo, físico, especial ou não prejudicial.](#2)
  
6.Move Learn Methods 
  - [<b>Identificação:</b> move-learn-method](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b>  Métodos pelos quais os Pokémon podem aprender movimentos.](#2)
  
 7.Move Targets
  - [<b>Identificação:</b> move-target](#identificação)
  - [<b>Tipo:</b> GET](#1)
  - [<b>Decrição:</b>  Os movimentos dos alvos podem ser direcionados durante a batalha. Os alvos podem ser Pokémon, ambientes ou até mesmo outros movimentos.](#2)
</details>

# <div align="center">_Atributos:_</div>

|            Nome           |       Identificador      |                                                                    Descrição                                                                    |   Tipo  |
|:-------------------------:|:------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------------------:|:-------:|
|        ID (Pokémon)       |            id            |                                Usado para pegar o número do Pokémon na Pokédex. Pode ser usado como um parâmetro.                               | integer |
|       Nome (Pokémon)      |           name           |                                   Retorna o nome do pokémon, junto do ID, é um dos dois possíveis parâmetros.                                   |  string |
|   Experiência (Pokémon)   |      base_experience     |                                               Exibe a experiência obtida ao derrotar esse Pokémon.                                              | integer |
|      Altura (Pokémon)     |          height          |                                                   Retorna a altura do POkémon, em decímetros.                                                   | integer |
|  Espécie Padrão (Pokémon) |        is_default        |                                                   Define um Pokémon padrão para cada espécie.                                                   | boolean |
|      Order (Pokémon)      |           order          |                        Mostra a ordem de classificação. Quase que regional, exceto as famílias, que são agrupadas juntas.                       | integer |
|       Peso (Pokémon)      |          weight          |                                                    Retorna O peso do Pokémon, em hectogramas.                                                   | integer |
|   Habilidades (Pokémon)   |         abilities        |                                           Retorna uma lista de habilidades que este Pokémon pode ter.                                           |   list  |
|      Formas (Pokémon)     |           forms          |                                              Mostra uma lista de formas que o Pokémon pode assumir.                                             |   list  |
|      Indice (Pokémon)     |       game_indices       |                                 Retorna uma lista de índices de jogos relevantes para itens Pokémon por geração.                                |   list  |
| Possíveis Itens (Pokémon) |        held_items        |                                  Exibe uma lista de itens que o Pokémon pode estar segurando quando encontrado.                                 |   list  |
|      Áreas (Pokémon)      | location_area_encounters | Retorna um link para uma lista com localizações, assim como detalhes de encontros com outros treinadores, pertencentes a uma versão específica. |  string |
|    Movimentos (Pokémon)   |           moves          |              Recupera uma lista de movimentos com métodos de aprendizado e detalhes de nível, pertencente a uma versão específica.              |   list  |
|    Histórico (Pokémon)    |        past_types        |                           Retorna uma lista detalhada de todos os tipos que aquele Pokémon tinha em antigas gerações.                           |   list  |
|       Foto (Pokémon)      |          sprites         |                                  Mostra um conjunto de "sprites" usados para representar este Pokémon no jogo.                                  |   list  |
|     Espécie (Pokémon)     |          species         |                                                    Exibe a espécie a qual o Pokémon pertence.                                                   |   list  |
|      Status (Pokémon)     |           stats          |                                                Recupera uma lista dos status base desse Pokémon.                                                |   list  |
|     Tipagem (Pokémon)     |           types          |                                           Mostra uma detalhada lista contendo a tipagem desse Pokémon.              
|   list  |
--------------------------------------------------------------------------------------------
# Descrição detalhada da Aplicação Pokémon
Nosso aplicativo utilizará apenas o método Pokémon que é um método geral da classe de métodos Pokémon. A função dele é mostrar as informações gerais do Pokémon sendo essencial para nossa Pokédex pois retorna tudo que é necessário como os atributos de tipagem e fraqueza. Temos a tela de carregamento que é uma “Splash Screen” onde já existe familiaridade no desenvolvimento. Pulamos então para a próxima Activity (janela) que se iniciará logo após o carregamento, os Pokémons que aparecerão na página principal serão os da primeira geração composta por 151, contudo nessa distribuição de Pokémons a evolução de uma mesma espécie será ocultada podendo ser acessada na página de detalhamento do respectivo Pokémon, tornando assim os dados mais diversificados. Nesta tela existirá ainda, a possibilidade de pesquisar de uma forma mais rápida determinado Pokémon, com uma barra de pesquisa onde o usuário poderá informar o nome ou o número registrado na Pokédex. Claro que se não for encontrado um erro retornará como resposta “Pokémon não encontrado”. Como teremos dados já carregados na tela inicial, vamos ter a necessidade de conectar com o banco de dados que trará tais informações na nossa aplicação. Existe ainda na primeira tela a possibilidade de filtrar os Pokémons pelos atributos assim tornando mais fácil a busca do usuário. A outra tela é a de dados do Pokémon, onde após o usuário clicar em algum da tela inicial ou procurando (pela barra de pesquisa ou filtro), obterá as informações variadas sobre aquele Pokémon, assim como também a possibilidade de acessar suas outras formas de evolução, todos esses dados estão dentro da API, apenas vamos ter o trabalho de design e de a implementar. Planejamos criar uma opção onde o usuário favorita o Pokémon, baixando toda a linha evolutiva. Para isto utilizaremos uma condição em que analisaremos a quantidade de Pokémons diferentes que vamos receber e os dados serão direcionados para a respectiva tabela já criada no banco de dados.

# Protótipo
<img src="img/Prototipo.png">

# Mapa de Navegação
<img src="img/MapaDeNavegacao.jpeg">

# Diagrama de Classe
<img src="img/DiagramaDeClasse.png">

# Diagrama do Banco de Dados
<img src="img/DiagramaBanco.png">

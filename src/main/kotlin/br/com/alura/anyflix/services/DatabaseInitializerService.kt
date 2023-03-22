package br.com.alura.anyflix.services

import br.com.alura.anyflix.models.Movie
import br.com.alura.anyflix.repositories.MovieRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.random.Random

@Service
class DatabaseInitializerService(
    private val repository: MovieRepository
) {

    @Value("\${movies.amount}")
    private var amount: Int? = null

    private val logger: Logger = LoggerFactory.getLogger(
        DatabaseInitializerService::class.java
    )

    fun saveMovies() {
        val total = repository.count().toInt()
        if (total > 0) {
            logger.info("Total of movies saved: $total")
        }
        val amountOfMoviesThatNeedToBeSave = (amount ?: 20) - total
        logger.info("Saving $amountOfMoviesThatNeedToBeSave movies")
        val year = LocalDateTime.now().year
        repeat(amountOfMoviesThatNeedToBeSave) {
            val index = Random.nextInt(0, mappedMovies.size - 1)
            val movie = mappedMovies
                .entries
                .shuffled()
                .elementAt(index)
            val title = movie.key
            val plot = movie.value
            repository.save(
                Movie(
                    title = title,
                    image = "https://picsum.photos/${Random.nextInt(1280, 2560)}/${Random.nextInt(720, 1440)}",
                    year = Random.nextInt(year - 100, year),
                    plot = plot
                )
            )
        }
    }

}

private val mappedMovies = mapOf(
    "O Segredo da Serra" to "um grupo de amigos parte em uma expedição na serra em busca de um tesouro perdido, mas acabam descobrindo um segredo antigo e perigoso que coloca suas vidas em risco.",
    "O Poder do Mar" to "um jovem surfista descobre que sua prancha tem poderes mágicos que o ajudam a enfrentar desafios dentro e fora do mar.",
    "Cidade em Caos" to "Quando um terremoto atinge uma cidade grande, um grupo de estranhos se une para sobreviver às consequências devastadoras e encontrar um caminho para casa.",
    "A Casa dos Segredos" to "uma família se muda para uma casa nova, mas logo descobre que o lugar guarda segredos sombrios que ameaçam suas vidas.",
    "O Mistério do Lago" to "quando um corpo é encontrado no fundo do lago da cidade, um detetive e sua equipe são chamados para investigar e descobrem uma rede de segredos e mentiras que envolvem toda a comunidade.",
    "A Caixa Mágica" to "um cientista cria uma caixa mágica que pode transformar objetos em outras coisas, mas quando a caixa é roubada, ele precisa recuperá-la antes que caia nas mãos erradas.",
    "Inferno na Terra" to "um grupo de sobreviventes luta para sobreviver em um mundo pós-apocalíptico infestado por criaturas mutantes.",
    "O Tesouro Perdido" to "um grupo de arqueólogos parte em uma expedição para encontrar um tesouro perdido há séculos, mas enfrentam perigos e desafios pelo caminho.",
    "A Jornada do Herói" to "um jovem comum descobre que é o escolhido para salvar o mundo de uma ameaça terrível e parte em uma jornada perigosa para enfrentar seus medos e salvar a todos.",
    "A Cidade dos Anjos" to "em uma cidade onde todos os habitantes são anjos, uma jovem humana precisa lutar para sobreviver e encontrar seu lugar no mundo.",
    "O Enigma da Esfinge" to "um grupo de cientistas tenta desvendar o enigma da esfinge egípcia, mas logo descobrem que há forças sobrenaturais em jogo que podem mudar o curso da história.",
    "O Jardim Secreto" to "uma menina órfã descobre um jardim secreto encantado e precisa lutar para mantê-lo vivo, enquanto lida com seus próprios problemas familiares e emocionais.",
    "O Legado da Família" to "após a morte do patriarca da família, seus filhos precisam lidar com segredos e conflitos familiares enquanto lutam para manter o legado da família vivo.",
    "A Cidade das Sombras" to "em uma cidade onde nunca há luz do sol, um grupo de amigos descobre um mistério sobrenatural que envolve criaturas das sombras.",
    "Além do Horizonte" to "Um grupo de aventureiros viaja para o espaço profundo em busca de um novo planeta para colonizar, mas descobre algo além de sua imaginação.",
    "A Ilha do Tesouro" to "um jovem marinheiro parte em uma aventura em busca do lendário tesouro escondido em uma ilha remota, mas precisa enfrentar perigos e desafios pelo caminho.",
    "O Guardião dos Sonhos" to "um jovem descobre que tem o poder de entrar nos sonhos das pessoas e ajuda-las a superar seus medos e traumas, mas precisa lidar com seus próprios problemas emocionais.",
    "Fúria do Oceano" to "Uma equipe de mergulhadores enfrenta uma tempestade feroz enquanto tenta encontrar um tesouro afundado no fundo do mar.",
    "O Labirinto da Mente" to "uma cientista desenvolve uma tecnologia que permite entrar na mente das pessoas, mas quando um experimento dá errado, ela precisa lutar para sair do labirinto da mente.",
    "A Era dos Dragões" to "em um mundo fantástico, uma jovem guerreira lidera uma equipe em uma missão para encontrar e domar os últimos dragões vivos.",
    "Trama Obscura" to "um escritor famoso é acusado de um crime que não cometeu e luta para limpar seu nome enquanto investiga quem está tentando incriminá-lo.",
    "O Portal do Tempo" to "um grupo de viajantes do tempo precisa enfrentar desafios e conflitos para impedir que uma força maligna destrua o mundo como o conhecemos.",
    "A Fúria do Oceano" to "quando uma tempestade violenta atinge a cidade costeira, um grupo de sobreviventes luta para manter-se vivo enquanto enfrenta a fúria do oceano.",
    "Mundos Paralelos" to "um cientista brilhante descobre um portal para um mundo paralelo e se envolve em uma guerra entre os dois mundos.",
    "A Cidade das Lendas" to "em uma cidade mística, um grupo de jovens exploradores descobre segredos antigos e lendas fascinantes que mudam suas vidas para sempre.",
    "Coração Partido" to "um jovem casal enfrenta dificuldades em seu relacionamento quando um segredo sombrio do passado é revelado.",
    "O Refúgio" to "um casal busca refúgio em uma casa isolada após um apocalipse zumbi, mas descobrem que nem tudo é o que parece quando outros sobreviventes começam a aparecer.",
    "O Segredo das Cartas" to "após receber cartas misteriosas, um jovem descobre um segredo sombrio de sua família e precisa lutar para desvendar a verdade antes que seja tarde demais.",
    "Vingança Mortal" to "um ex-soldado busca vingança contra a gangue que matou sua família, mas precisa enfrentar seus próprios demônios interiores para completar sua missão.",
    "A Cidade do Futuro" to "em um futuro distante, uma jovem cientista desenvolve tecnologias revolucionárias que mudam a vida das pessoas, mas quando uma força maléfica ameaça a cidade, ela precisa lutar para proteger seus ideais.",
    "O Legado Perdido" to "um grupo de exploradores parte em busca de um tesouro lendário que pode mudar a história, mas precisa enfrentar perigos e mistérios ocultos ao longo do caminho.",
    "A Última Missão" to "após anos de serviço militar, um veterano é convocado para uma última missão que pode mudar o curso da guerra, mas precisa lidar com os traumas do passado e seus próprios demônios internos.",
    "Cidade da Vingança" to "um ex-policial busca vingança contra uma gangue que matou sua família e assumiu o controle da cidade.",
    "A Conspiração" to "em um mundo dominado por grandes corporações, um grupo de hackers descobre uma conspiração que pode mudar o destino da humanidade, mas precisa lutar contra a vigilância e os inimigos poderosos que os perseguem.",
    "A Herdeira" to "após a morte de seus pais, uma jovem herda uma fortuna e descobre segredos sombrios sobre sua família que ameaçam sua vida e a de seus entes queridos.",
    "Mistério no Farol" to "uma equipe de investigadores é chamada para investigar eventos estranhos em um farol isolado, mas o que eles descobrem é muito mais aterrorizante do que imaginavam.",
    "O Refúgio da Montanha" to "um casal decide se isolar em uma cabana na montanha após uma catástrofe global, mas descobrem que há mais perigos no local do que imaginavam.",
    "A Lenda do Dragão" to "em um mundo de fantasia, um jovem guerreiro parte em busca do dragão lendário que pode conceder seus desejos, mas precisa enfrentar desafios e monstros no caminho.",
    "Naufrágio" to "um grupo de estranhos fica preso em uma ilha deserta após um acidente de avião e precisa lutar para sobreviver.",
    "O Dom das Fadas" to "uma jovem descobre que tem o dom de se comunicar com fadas e precisa lutar para proteger seu mundo mágico de uma força maligna que ameaça destruí-lo.",
    "O Mistério da Floresta" to "um grupo de amigos decide passar as férias em uma cabana na floresta, mas logo descobrem que há um mistério oculto no local que pode mudar suas vidas para sempre.",
    "Desaparecido" to "uma mãe desesperada procura por seu filho desaparecido, mas descobre que o caso é mais complicado do que ela pensava.",
    "O Jogo da Morte" to "um grupo de jogadores é convidado para um jogo misterioso onde a única regra é sobreviver.",
    "O Segredo das Sombras" to "um detetive particular investiga um assassinato em uma cidade pequena e descobre um segredo sombrio que todos querem manter escondido.",
    "Coração de Gelo" to "Uma mulher de negócios bem-sucedida se envolve em um romance proibido com um homem misterioso, mas suas mentiras ameaçam separá-los para sempre.",
    "Mundo Invertido" to "Um adolescente descobre que pode viajar para um mundo paralelo onde ele é um herói e deve enfrentar seus próprios demônios para salvar o mundo.",
    "Vida Dupla" to "Um homem aparentemente comum é na verdade um assassino profissional, mas quando ele se apaixona por uma de suas vítimas, começa a questionar sua própria moralidade.",
    "Destino Incerto" to "Dois estranhos se encontram em uma viagem de trem, mas quando o trem descarrila no meio do nada, eles devem lutar juntos para sobreviver.",
    "O Preço da Ambição" to "Um jovem empresário se envolve em esquemas corruptos para alcançar o sucesso, mas quando ele é pego, deve escolher entre a liberdade ou a lealdade.",
    "Herança Maldita" to "Quando um jovem herda uma antiga propriedade da família, ele descobre um segredo obscuro que ameaça sua vida e sua sanidade.",
    "Rumo ao Desconhecido" to "Uma equipe de astronautas embarca em uma missão de exploração em um planeta desconhecido, mas quando um deles é infectado por um vírus misterioso, a missão se torna uma corrida contra o tempo para salvá-lo.",
    "A Origem" to "Um ladrão de sonhos é contratado para implantar uma ideia na mente de um CEO rival.",
    "Silêncio" to "Dois padres jesuítas viajam ao Japão para encontrar seu mentor desaparecido e espalhar a fé cristã, mas enfrentam perseguição e violência.",
    "Alma Perdida" to "Uma estudante universitária desaparece misteriosamente e seus amigos começam a receber mensagens estranhas, levando-os a descobrir um segredo sombrio.",
    "Parasita" to "Uma família pobre se infiltra em uma casa rica, mas seus planos são ameaçados quando descobrem um segredo mortal.",
    "O Poderoso Chefão" to "Um chefe de família da máfia tenta proteger seus interesses e sua família enquanto luta contra rivais e traições.",
    "Vingadores: Ultimato" to "Os heróis da Marvel se unem para desfazer o dano causado por um vilão poderoso.",
    "O Iluminado" to "Um escritor em crise aceita um emprego como zelador em um hotel isolado nas montanhas, mas coisas estranhas começam a acontecer quando ele e sua família se mudam.",
    "Laranja Mecânica" to "Um jovem criminoso é preso e submetido a um tratamento experimental para curá-lo de sua violência, mas as coisas não saem como planejado.",
    "Clube da Luta" to "Um homem entediado e insatisfeito com sua vida conhece um estranho carismático que o leva a participar de um clube de luta secreto.",
    "Pulp Fiction: Tempo de Violência" to "Várias histórias interligadas sobre gangsters, boxeadores, assassinos e outros personagens em Los Angeles.",
    "A Queda" to "Um executivo de sucesso vê sua vida desmoronar quando é acusado de um crime que não cometeu.",
    "O Despertar" to "Uma jovem escritora herda uma casa antiga de sua avó e descobre um segredo que a leva a investigar a morte misteriosa de sua família.",
    "Fogo Cruzado" to "Um detetive experiente e um novato desajeitado se unem para desvendar uma série de assassinatos brutais em uma cidade violenta.",
    "Sem Limites" to "Um escritor desesperado por inspiração experimenta uma droga experimental que o leva a novos níveis de inteligência e poder, mas a um alto custo.",
    "A Última Esperança" to "Uma equipe de astronautas é enviada em uma missão de resgate para salvar uma colônia em Marte que está à beira da extinção.",
    "O Mistério da Ilha" to "Um grupo de turistas é deixado em uma ilha remota para um passeio, mas logo descobrem que há algo sinistro acontecendo lá.",
    "O Grande Golpe" to "Um grupo de amigos planeja o roubo perfeito em um cassino de Las Vegas, mas as coisas dão errado quando um deles começa a ter segundas intenções.",
    "A Nova Ordem" to "Um ditador implacável toma o controle de um país e começa a impor suas próprias regras, levando a um levante violento.",
    "O Mistério do Museu" to "Uma série de roubos em museus em todo o mundo é ligada a um especialista em arte misterioso e genial.",
    "A Vingança" to "Uma mulher luta para se vingar do homem que arruinou sua vida e matou sua família.",
    "Ecos da Morte" to "Uma jovem viúva começa a ouvir vozes estranhas em sua casa, levando-a a investigar a morte misteriosa de seu marido.",
    "Traição Fatal" to "Um advogado bem-sucedido se envolve em um caso perigoso e se vê enredado em uma teia de mentiras e assassinatos.",
    "Alta Tensão" to "Uma jovem viaja para o campo para visitar sua família, mas as coisas dão errado quando um assassino começa a matar as pessoas ao seu redor.",
    "Assassinato no Expresso do Oriente" to "Um detetive belga é chamado para investigar um assassinato em um trem de luxo, mas descobre que todos os passageiros têm um motivo para matar a vítima.",
    "O Fantasma da Ópera" to "Um cantor de ópera começa a receber ameaças de um misterioso fantasma que habita o teatro onde ele se apresenta.",
    "O Enigma do Horizonte" to "Uma equipe de cientistas embarca em uma missão para explorar um buraco negro recém-descoberto, mas descobre um terror além da imaginação.",
    "O Chamado do Vazio" to "Uma mulher traumatizada por um incidente em seu passado começa a ser perseguida por visões assustadoras que a levam a questionar sua sanidade.",
    "O Legado" to "Uma família herda uma mansão antiga de um parente distante e descobre um segredo sombrio que a liga ao passado assustador da propriedade.",
    "A Última Jornada" to "Um grupo de sobreviventes luta para chegar a um refúgio seguro após um desastre catastrófico deixar o mundo em ruínas.",
    "A Maldição do Livro" to "Um estudante universitário encontra um livro antigo em uma biblioteca e descobre que ele está amaldiçoado, levando-o a uma jornada perigosa para desvendar seus segredos.",
    "Fuga do Inferno" to "Um grupo de prisioneiros planeja uma fuga ousada de uma prisão de segurança máxima.",
    "O Aprendiz de Assassino" to "Um jovem desesperado se junta a uma organização secreta de assassinos para vingar a morte de sua família.",
    "Sobreviventes do Fim do Mundo" to "Um grupo de sobreviventes luta para sobreviver em um mundo pós-apocalíptico.",
    "O Último Ritual" to "Um antropólogo é enviado para investigar um ritual antigo em uma tribo isolada, mas descobre algo muito mais sinistro.",
    "A Caçada Humana" to "Um grupo de caçadores ricos caça humanos em uma ilha remota, mas logo descobrem que as coisas não são o que parecem.",
    "O Segredo da Cidade Perdida" to "Um arqueólogo obstinado procura uma cidade lendária que se acredita estar escondida no meio da selva.",
    "A Ilha da Morte" to "Um grupo de amigos visita uma ilha remota para um retiro, mas logo descobrem que algo sinistro está acontecendo lá.",
    "O Pacto dos Lobos" to "Uma aldeia francesa é aterrorizada por uma besta misteriosa, e um cavaleiro e um cientista são enviados para caçá-la.",
    "A Herança Macabra" to "Uma jovem herda uma mansão antiga de um parente distante, mas descobre que há um preço a pagar pela riqueza da família.",
    "O Colecionador de Memórias" to "Um homem descobre que pode roubar as memórias de outras pessoas, mas logo percebe que seus poderes têm um preço terrível.",
    "Condenados à Morte" to "Um grupo de detentos é forçado a lutar em uma arena mortal para entretenimento do público.",
    "Desaparecidos" to "Um detetive particular é contratado para investigar o desaparecimento de uma jovem, mas acaba descobrindo uma conspiração maior.",
    "O Preço do Poder" to "Um jovem ambicioso sobe ao poder em um império de negócios, mas logo descobre que o preço do sucesso é alto demais.",
    "Rastro de Vingança" to "Um xerife em busca de vingança segue o rastro de um criminoso perigoso que matou sua família.",
    "Mundo Caído" to "Um grupo de sobreviventes luta para sobreviver em um mundo pós-apocalíptico dominado por zumbis.",
    "O Invasor" to "Um homem solitário é atormentado por visões de um invasor misterioso em sua casa.",
    "O Assassino Silencioso" to "Um assassino implacável é contratado para matar um alvo, mas acaba descobrindo um segredo que muda tudo.",
    "A Herdeira da Maldição" to "Uma jovem herda uma mansão assombrada de sua avó, mas logo descobre que a maldição da família ainda vive.",
    "Perigo nas Montanhas" to "Um grupo de amigos vai acampar nas montanhas, mas logo descobrem que algo sinistro está à espreita na natureza.",
    "A Voz do Espírito" to "Uma jovem ouve a voz de um espírito assombrado em sua casa e deve descobrir como acalmá-lo antes que seja tarde demais."
)

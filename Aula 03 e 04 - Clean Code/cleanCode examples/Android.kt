interface INetworkService {
    fun fetchData(): String
}

class NetworkService : INetworkService {
    override fun fetchData(): String {
        // fetch data from network
        return "Data from network"
    }
}

class UseNetworkService(val networkService: INetworkService) {
    fun useNetworkService() {
        val data = networkService.fetchData()
        println(data)
    }
}

class UseNetworkService() {
    private lateinit val networkService: INetworkServicel
    fun setNetworkService(networkService: INetworkService) {
        this.networkService = networkService
    }
    fun useNetworkService() {
        val data = networkService.fetchData()
        println(data)
    }
}
class UseNetworkService() {
    fun useNetworkService(networkService: INetworkService) {
        val data = networkService.fetchData()
        println(data)
    }
}


class DependencyInjector {
    val networkService = NetworkService()
    fun provideNetworkService(): INetworkService {
        return NetworkService()
    }
}

class MainActivity : AppCompatActivity() {
    private lateinit var networkService: INetworkService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // inject dependencies
        val dependencyInjector = DependencyInjector()
        networkService = dependencyInjector.provideNetworkService()
        // use the networkService
        val data = networkService.fetchData()
        println(data)
    }
}


// Ruim
fun printInfo(u: User) {
    println("Nome: ${u.name}, Idade: ${u.age}")
}





// Bom
fun printUserInfo(user: User) {
    println("Nome: ${user.name}, Idade: ${user.age}")
}


// Ruim
fun processUserData(data: List<User>) {
    for (user in data) {
        if (user.isActive) {
            val userDetails = fetchUserDetails(user.id)
            val formattedDetails = formatUserDetails(userDetails)
            saveFormattedDetailsToFile(formattedDetails)
        }
    }
    // Mais código relacionado ao processamento de dados...
}
//Não modificar o valor de uma variável
int valor = 42;

// Bom
fun processActiveUsersData(data: List<User>) {
    for (user in data) {
        if (user.isActive) {
            processActiveUser(user)
        }
    }
}

fun processActiveUser(user: User) {
    val userDetails = fetchUserDetails(user.id)
    val formattedDetails = formatUserDetails(userDetails)
    saveFormattedDetailsToFile(formattedDetails)
}

fetchUserDetails(user.id)
                .formatUserDetails()
                .saveFormattedDetailsToFile()



//Ruim
data class Endereco(val rua: String)

class Pessoa(val endereco: Endereco)

fun imprimirRua(pessoa: Pessoa) {
    println(pessoa.endereco.rua)
}

// Bom
class Endereco(val rua: String)

class Pessoa(private val endereco: Endereco) {
    fun getRua(): String {
        return endereco.rua
    }
}

fun imprimirRua(pessoa: Pessoa) {
    println(pessoa.getRua())
}

class Conta {
    var saldo: Double = 0.0
    fun depositar(valor: Double) {
        saldo += valor
    }

    fun sacar(valor: Double) {
        saldo -= valor
    }
}


var conta?: Conta = null
conta.saldo = 100.0

val conta = Conta()
conta.saldo = 100.0
conta.depositar(50.0)
conta.sacar(30.0)


class Conta(private var saldo: Double) {
    fun getSaldo(): Double {
        return saldo
    }
    fun depositar(valor: Double) {
        saldo += valor
    }
    fun sacar(valor: Double) {
        if (valor <= saldo) {
            saldo -= valor
        } else {
            println("Saldo insuficiente")
        }
    }
}
val conta = Conta(100.0)
conta.depositar(50.0)
conta.sacar(30.0)



// Ruim
fun dividir(a: Int, b: Int): Int {
    if (b == 0) {
        return -1
    }
    return a / b
}

val resultado = dividir(10, 0)
if (resultado == -1) {
    println("Erro: divisão por zero")
}

fun dividir(a: Int, b: Int): Int {
    if (b == 0) {
        throw IllegalArgumentException("Divisor não pode ser zero")
    }
    return a / b
}

try {
    val resultado = dividir(10, 0)
} catch (e: IllegalArgumentException) {
    println(e.message)
}


// Ruim
class MeuServico(val bibliotecaExterna: BibliotecaExterna) {
    fun fazerAlgo() {
        bibliotecaExterna.metodoExterno()
    }
}


// Bom
interface ServicoExterno {
    fun metodoExterno()
}
class Adaptador(val bibliotecaExterna: BibliotecaExterna) : ServicoExterno {
    override fun metodoExterno() {
        bibliotecaExterna.metodoExterno()
    }
}
class MeuServico(val servicoExterno: ServicoExterno) {
    fun fazerAlgo() {
        servicoExterno.metodoExterno()
    }
}


// Ruim
fun obterDadosExterno(): DadosExterno {
    val bibliotecaExterna = BibliotecaExterna()
    return bibliotecaExterna.obterDados()
}

// Bom
fun obterDadosExterno(): MeusDados {
    val bibliotecaExterna = BibliotecaExterna()
    val dadosExterno = bibliotecaExterna.obterDados()
    return MeusDados(dadosExterno)
}


// Ruim
fun processarDados(dados: String) {
    // ...
}

val dados = obterDadosDeEntrada()
processarDados(dados)

// Bom
fun processarDados(dados: String) {
    if (dados.isBlank()) {
        throw IllegalArgumentException("Dados não podem ser vazios")
    }
    // ...
}

val dados = obterDadosDeEntrada()
try {
    processarDados(dados)
} catch (e: IllegalArgumentException) {
    println(e.message)
}


// Ruim
class MeuServico(val bibliotecaExterna: BibliotecaExterna) {
    fun enviarDados(dados: MeusDados) {
        val dadosExterno = DadosExterno(dados.valor)
        bibliotecaExterna.enviarDados(dadosExterno)
    }
}


// Bom
class AdaptadorDeDados {
    fun converterMeusDadosParaDadosExterno(dados: MeusDados): DadosExterno {
        return DadosExterno(dados.valor)
    }
}
class MeuServico(val bibliotecaExterna: BibliotecaExterna, val adaptadorDeDados: AdaptadorDeDados) {
    fun enviarDados(dados: MeusDados) {
        val dadosExterno = adaptadorDeDados.converterMeusDadosParaDadosExterno(dados)
        bibliotecaExterna.enviarDados(dadosExterno)
    }
}

// Ruim
class C {
    fun m(d: Int) {
        // ...
    }
}
// Bom
class Calculadora {
    fun somar(numero: Int) {
        // ...
    }
}

// Ruim
class Pessoa {
    var nome: String = ""
    var idade: Int = 0
    fun salvar() {
        // ...
    }
    fun imprimir() {
        // ...
    }
}

// Bom
class Pessoa(var nome: String, var idade: Int)

class Repositorio {
    fun salvar(pessoa: Pessoa) {
        // ...
    }
}
class Impressora {
    fun imprimir(pessoa: Pessoa) {
        // ...
    }
}

// Ruim
class Pessoa(var nome: String, var idade: Int, var endereco: String, var telefone: String, var email: String)

class Pessoa(var nome: String, var idade: Int, var contato: Contato)
class Contato(var endereco: String, var telefone: String, var email: String)
class Empresa(var nome: String, var contato: Contato)

// Ruim
class Pessoa {
    var nome: String = ""
    var idade: Int = 0
}

val pessoa = Pessoa()
pessoa.nome = "João"
pessoa.idade = 30

//Bom
class Pessoa(private var nome: String, private var idade: Int) {
    fun getNome(): String {
        return nome
    }

    fun getIdade(): Int {
        return idade
    }
}

val pessoa = Pessoa("João", 30)
val nome = pessoa.getNome()
val idade = pessoa.getIdade()


// Ruim
class Calculadora {
    fun somar(a: Int, b: Int): Int {
        return a + b
    }
    fun subtrair(a: Int, b: Int): Int {
        return a - b
    }
    fun multiplicar(a: Int, b: Int): Int {
        return a * b
    }
    fun dividir(a: Int, b: Int): Int {
        return a / b
    }
}

//Bom
class Calculadora {
    fun somar(a: Int, b: Int): Int {
        return a + b
    }
    fun subtrair(a: Int, b: Int): Int {
        return a - b
    }
}
class Matematica {
    fun multiplicar(a: Int, b: Int): Int {
        return a * b
    }

    fun dividir(a: Int, b: Int): Int {
        return a / b
    }
}




// Ruim
class MeuServico {
    val repositorio = Repositorio()
    
    fun fazerAlgo() {
        val dados = repositorio.obterDados()
        // ...
    }
}

val meuServico = MeuServico()
meuServico.fazerAlgo()


// Bom
class MeuServico(val repositorio: Repositorio) {
    fun fazerAlgo() {
        val dados = repositorio.obterDados()
        // ...
    }
}

val repositorio = Repositorio()
val meuServico = MeuServico(repositorio)
meuServico.fazerAlgo()


// Ruim
class MeuServico(val repositorio: Repositorio) {
    fun fazerAlgo() {
        val dados = repositorio.obterDados()
        // ...
    }
}

val repositorio = Repositorio()
val meuServico = MeuServico(repositorio)
meuServico.fazerAlgo()

// Bom
interface Servico {
    fun fazerAlgo()
}

class MeuServico(val repositorio: Repositorio) : Servico {
    override fun fazerAlgo() {
        val dados = repositorio.obterDados()
        // ...
    }
}

val repositorio = Repositorio()
val servico: Servico = MeuServico(repositorio)
servico.fazerAlgo()


// Ruim
class Repositorio {
    fun obterDados(): List<Dados> { ...
    }
}
class MeuServico {
    val repositorio = Repositorio()
    fun fazerAlgo() {
        val dados = repositorio.obterDados()   ...
    }
}
val meuServico = MeuServico()
meuServico.fazerAlgo()

// Bom
interface IRepositorio {
    fun obterDados(): List<Dados>
}
class Repositorio : IRepositorio {
    override fun obterDados(): List<Dados> { ...
    }
}

class MeuServico(val repositorio: IRepositorio) {
    fun fazerAlgo() {
        val dados = repositorio.obterDados(). ...
    }
}
val repositorio: IRepositorio = Repositorio()
val meuServico = MeuServico(repositorio)
meuServico.fazerAlgo()

// Ruim
object MeuSingleton {
    fun fazerAlgo() {
        // ...
    }
}
class MeuServico {
    fun fazerAlgo() {
        MeuSingleton.fazerAlgo()
    }
}
val meuServico = MeuServico()
meuServico.fazerAlgo()

// Bom
interface IMeuServico {
    fun fazerAlgo()
}
class MeuServico : IMeuServico {
    override fun fazerAlgo() {...
    }
}
class MeuOutroServico(val meuServico: IMeuServico) {
    fun fazerAlgo() {
        meuServico.fazerAlgo()
    }
}
val meuServico: IMeuServico = MeuServicoLegal()
val meuOutroServico = MeuOutroServico(meuServico)
meuOutroServico.fazerAlgo()


// Ruim
class MeuServico {
    val repositorio = Repositorio()

    fun fazerAlgo() {
        val dados = repositorio.obterDados()
        // ...
    }
}

val meuServico = MeuServico()
meuServico.fazerAlgo()


// Bom
class MeuServico(val repositorio: IRepositorio) {
    fun fazerAlgo() {
        val dados = repositorio.obterDados()
        // ...
    }
}

val repositorio: IRepositorio = Repositorio()
val meuServico = MeuServico(repositorio)
meuServico.fazerAlgo()


// Ruim
fun processUserInput(input: String) {
    val sanitizedInput = input.trim().toLowerCase()
    val isValid = validateInput(sanitizedInput)
    if (!isValid) {
        println("Input inválido")
        return
    }
    val result = performAction(sanitizedInput)
    println("Resultado: $result")
}

// Bom
fun processUserInput(input: String) {
    val sanitizedInput = sanitizeInput(input)
    if (!validateInput(sanitizedInput)) {
        println("Input inválido")
        return
    }
    val result = performAction(sanitizedInput)
    println("Resultado: $result")
}

// Ruim
class OrderProcessor {
    fun validateOrder(order: Order) { /* ... */ }
    fun calculateTotal(order: Order): Double { /* ... */ }
    fun sendEmail(order: Order, total: Double) { /* ... */ }
}

// Bom
class OrderValidator {
    fun validate(order: Order) { /* ... */ }
}

class OrderCalculator {
    fun calculateTotal(order: Order): Double { /* ... */ }
}

class EmailSender {
    fun sendEmail(order: Order, total: Double) { /* ... */ }
}


// Ruim
fun calculateTotal(order: Order): Double {
    sendEmail(order)
    // ...
}

fun calculateTotalAndthoughEmail(order: Order): Double {
    calculateTotal(order)
    sendEmail(order)
    // ...
}

// Bom
fun calculateTotal(order: Order): Double {
    // ...
}


//Ruim
fun calculateAreaOfRectangle(width: Double, height: Double): Double {
    val area = width * height
    println("A área é: $area")
    return area
}
fun calculateAreaOfSquare(side: Double): Double {
    val area = side * side
    println("A área é: $area")
    return area
}

//Bom
fun calculateArea(width: Double, height: Double): Double {
    val area = width * height
    return area
}
fun printArea(area: Double) {
    println("A área é: $area")
}



// Ruim
for (i in 1..10) {
    for (j in 1..10) {
        if (i * j > 50) {
            println("$i * $j = ${i * j}")
        }
    }
}

// Bom
for (i in 1..10) {
    for (j in 1..10) {
        printProductIfGreaterThan50(i, j)
    }
}

fun printProductIfGreaterThan50(i: Int, j: Int) {
    if (i * j > 50) {
        println("$i * $j = ${i * j}")
    }
}

// Ruim
val max: Int
if (a > b) {
    max = a
} else {
    max = b
}

val max = if (a > b) a else b


// Ruim
fun findEvenNumber(numbers: List<Int>): Int? {
    for (number in numbers) {
        if (number % 2 == 0) {
            return number
        } else {
            continue
        }
    }
    return null
}

// Bom
fun findEvenNumber(numbers: List<Int>): Int? {
    for (number in numbers) {
        if (number % 2 == 0) {
            return number
        }
    }
    return null
}



// Ruim
class UsuarioActivity : AppCompatActivity() {
    fun carregarUsuario() {
        // Carregar usuário do banco de dados
    }
    fun mostrarUsuario() {
        // Mostrar usuário na tela
    }
    fun salvarUsuario() {
        // Salvar usuário no banco de dados
    }
}

// Bom

interface IUsuarioRepository {
    fun carregarUsuario(): Usuario
    fun salvarUsuario(usuario: Usuario)
}
class UsuarioRepository :IUsuarioRepository{
    fun carregarUsuario(): Usuario {
        // Carregar usuário do banco de dados
    }
    fun salvarUsuario(usuario: Usuario) {
        // Salvar usuário no banco de dados
    }
}
class UsuarioActivity : AppCompatActivity() {
    private val usuarioRepository = UsuarioRepository()
    fun mostrarUsuario() {
        val usuario = usuarioRepository.carregarUsuario()
        // Mostrar usuário na tela
    }
    fun salvarUsuario(usuario: Usuario) {
        usuarioRepository.salvarUsuario(usuario)
    }
}

open class Ave {
    open fun voar() {}
}
class Pato : Ave() {
    override fun voar() {}
}
class Avestruz : Ave() {
    override fun voar() {
        throw UnsupportedOperationException("Avestruzes não podem voar")
    }
}
fun main() {
    val aves: List<Ave> = listOf(Pato(), Avestruz())
    for (ave in aves) {
        ave.voar()
    }
}

interface Ave {
    fun voar()
}
class Pato : Ave {
    override fun voar() {
        // Voar como um pato
    }
}
class Avestruz : Ave {
    override fun voar() {
        // Não fazer nada
    }
}
fun main() {
    val aves: List<Ave> = listOf(Pato(), Avestruz())
    for (ave in aves) {
        ave.voar()
    }
}


interface Impressora {
    fun imprimir()
    fun escanear()
    fun enviarFax()
}
class ImpressoraMultifuncional : Impressora {
    override fun imprimir() {
}
    override fun escanear() {
}
    override fun enviarFax() {
}
}
class ImpressoraSimples : Impressora {
    override fun imprimir() {
    }
    override fun escanear() {
        throw UnsupportedOperationException("Esta impressora não pode escanear")
    }
    override fun enviarFax() {
        throw UnsupportedOperationException("Esta impressora não pode enviar fax")
    }
}

interface Impressora {
    fun imprimir()
}
interface Scanner {
    fun escanear()
}
interface Fax {
    fun enviarFax()
}
class ImpressoraMultifuncional : Impressora, Scanner, Fax {
    override fun imprimir() {}
    override fun escanear() {}
    override fun enviarFax() {}
}
class ImpressoraSimples : Impressora {
    override fun imprimir() {}
}
class ScannerSimples : Scanner {
    override fun escanear() {}
}

interface INetworkService {
    fun postData(data:String): Boolean
    fun fetchData(): String
}



class Servico {
    fun executar() {
        // Executar serviço
    }
}

class Aplicativo {
    private val servico = Servico()

    fun iniciar() {
        servico.executar()
    }
}

fun main() {
    val aplicativo = Aplicativo()
    aplicativo.iniciar()
}


interface Servico {
    fun executar()
}

class ServicoImpl : Servico {
    override fun executar() {
        // Executar serviço
    }
}

class Aplicativo(private val servico: Servico) {
    fun iniciar() {
        servico.executar()
    }
}

fun main() {
    val servico: Servico = ServicoImpl()
    val aplicativo = Aplicativo(servico)
    aplicativo.iniciar()
}














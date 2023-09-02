Int iStatus;
char cVogal;
int numero;
class Minha{

}
Minha minha = new Minha();
class MinhaClasse{
    int meu_int;
    char meu_char;
    private int _meu_int_privado;
}

class UserData: ObservableObject {
    @Published var name = "Mark Joselli"
}

let userData = UserData()
let contentView = ContentView().environmentObject(userData)

struct ContentView: View {
    @EnvironmentObject var userData: UserData
    
    var body: some View {
        Text("Hello, \(userData.name)!")
    }
}


var conta: Conta?
conta.depositar(100)

var max = a > b ? a : 

guard let a = b else {
    fsdfds
    return
}

if let a = b {

}

struct ContentView: View {
    @State private var tasks: [Task] = []
    var body: some View {
        List(tasks) { task in
            Text(task.name)
        }
        .onAppear {
            // Buscar tarefas da API
            APIService.fetchTasks { fetchedTasks in
                self.tasks = fetchedTasks
            }}  }}
struct Task: Identifiable {
    var id: Int
    var name: String
}
class APIService {
    static func fetchTasks(completion: @escaping ([Task]) -> Void) {// Buscar tarefas da API}}
    }
}


// Bom
struct ContentView: View {
    @StateObject private var taskViewModel = TaskViewModel()
    var body: some View {
        List(taskViewModel.tasks) { task in
            Text(task.name)}
        .onAppear {
            taskViewModel.fetchTasks()
        }}}

class TaskViewModel: ObservableObject {
    @Published var tasks: [Task] = []
    func fetchTasks() {
        APIService.fetchTasks { fetchedTasks in
            self.tasks = fetchedTasks
        }}}
struct Task: Identifiable {
    var id: Int
    var name: String
}
class APIService {
    static func fetchTasks(completion: @escaping ([Task]) -> Void) {// Buscar tarefas da API
    }}


//Ruim
struct Notificacao {
    func enviar(mensagem: String, tipo: String) {
        switch tipo {
        case "email":
            // Enviar email
        case "sms":
            // Enviar SMS
        default:
            break
        }}}
struct ContentView: View {
    var body: some View {
        Button(action: {
            let notificacao = Notificacao()
            notificacao.enviar(mensagem: "Olá, mundo!", tipo: "email")
        }) {
            Text("Enviar Notificação")
        }}}


protocol Notificacao { func enviar(mensagem: String)}
struct Email: Notificacao { func enviar(mensagem: String) {}}
struct SMS: Notificacao { func enviar(mensagem: String) {}}
struct WhatsAppAPI: Notificacao { func enviar(mensagem: String) {}}

struct NotificacaoService {
    private let notificacao: Notificacao
    init(notificacao: Notificacao) {
        self.notificacao = notificacao
    }
    func enviar(mensagem: String) {
        notificacao.enviar(mensagem: mensagem)
    }
}
struct ContentView: View {
    var body: some View {
        Button(action: {
            let notificacaoService = NotificacaoService(notificacao: Email())
            notificacaoService.enviar(mensagem: "Olá, mundo!")
       }) {
            Text("Enviar Notificação")
        } }}


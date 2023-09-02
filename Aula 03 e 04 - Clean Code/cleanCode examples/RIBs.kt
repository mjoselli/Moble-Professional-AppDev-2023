// Entity
//User é a Entity. Ela representa os dados e a lógica de negócios do aplicativo.
data class User(val name: String, val age: Int)

// Interactor
//UserInteractor é o Interactor. Ele contém a lógica de negócios para obter os dados do usuário e interage com o Presenter e o Router.
class UserInteractor(val presenter: UserPresenter, val router: UserRouter) {

    fun start() {
        val user = User("John Doe", 30)
        presenter.showUser(user)
    }
}

// Presenter
//UserPresenter é o Presenter. Ele define os métodos que o Interactor usará para atualizar a View.

interface UserPresenter {
    fun showUser(user: User)
}

// Router
//UserRouter é o Router. Ele é responsável por lidar com a navegação entre as telas e interage com o Builder para construir as dependências necessárias.

class UserRouter(val builder: UserBuilder) {

    fun navigateToNextScreen() {
        // navigate to next screen
    }
}

// Builder
//UserBuilder é o Builder. Ele é responsável por construir as dependências necessárias para o Router e o Interactor.

class UserBuilder {

    fun build(view: UserView): UserRouter {
        val presenter = UserPresenterImpl(view)
        val interactor = UserInteractor(presenter, router)
        val router = UserRouter(this)
        return router
    }
}

// View
//UserView é a View. Ela define os métodos que o Presenter usará para atualizar a View.

interface UserView {
    fun showUser(user: User)
}

// Activity
//MainActivity é a Activity que implementa a UserView e é responsável por exibir os dados do usuário na interface do usuário.

class MainActivity : AppCompatActivity(), UserView {

    private lateinit var router: UserRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val builder = UserBuilder()
        router = builder.build(this)
        router.interactor.start()
    }

    override fun showUser(user: User) {
        // update UI
        println("User name: ${user.name}, User age: ${user.age}")
    }

    // This method is called when a button is clicked in the UI
    fun onNextButtonClicked() {
        router.navigateToNextScreen()
    }
}
/*
O MainActivity cria uma instância do UserBuilder e usa isso para construir o UserRouter e iniciar o UserInteractor. O UserInteractor carrega os dados do usuário e os passa para o MainActivity para serem exibidos. O UserRouter é usado para navegar para a próxima tela quando o botão "Next" é clicado.





 */


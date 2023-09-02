import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// Entity
//User é a Entity. Ela representa os dados e a lógica de negócios do aplicativo.
data class User(val name: String, val age: Int)

// Interactor
//GetUserInteractor é o Interactor. Ele contém a lógica de negócios para obter os dados do usuário.

class GetUserInteractor {
    fun getUser(): User {
        // load user data from network or database
        return User("John Doe", 30)
    }
}


// View
//UserView é a View. Ela define os métodos que o Presenter usará para atualizar a View.

interface UserView {
    fun showUser(user: User)
}



// Presenter
//UserPresenter é o Presenter. Ele recupera os dados do usuário do Interactor e os passa para a View para serem exibidos.

class UserPresenter(val view: UserView, val interactor: GetUserInteractor) {

    fun loadUser() {
        val user = interactor.getUser()
        view.showUser(user)
    }
}

// Router
//UserRouter é o Router. Ele é responsável por lidar com a navegação entre as telas.

class UserRouter {

    fun navigateToNextScreen() {
        // navigate to next screen
    }
}

// Activity
//MainActivity é a Activity que implementa a UserView e é responsável por exibir os dados do usuário na interface do usuário.

class MainActivity : AppCompatActivity(), UserView {

    private lateinit var presenter: UserPresenter
    private lateinit var router: UserRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val interactor = GetUserInteractor()
        presenter = UserPresenter(this, interactor)
        router = UserRouter()

        presenter.loadUser()
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
O MainActivity cria instâncias do UserPresenter e do UserRouter, e o UserPresenter carrega os dados do usuário e os passa para o MainActivity para serem exibidos. O UserRouter é usado para navegar para a próxima tela quando o botão "Next" é clicado.
 */

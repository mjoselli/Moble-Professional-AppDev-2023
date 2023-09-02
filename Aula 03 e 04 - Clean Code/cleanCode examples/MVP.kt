import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// Model
data class User(val name: String, val age: Int)

// View
interface UserView {
    fun showUser(user: User)
}

// Presenter
class UserPresenter(val view: UserView) {

    fun loadUser() {
        val user = User("John Doe", 30)
        view.showUser(user)
    }
}

// Activity
class MainActivity : AppCompatActivity(), UserView {

    private lateinit var presenter: UserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = UserPresenter(this)
        presenter.loadUser()
    }

    override fun showUser(user: User) {
        // update UI
        println("User name: ${user.name}, User age: ${user.age}")
    }
}
/*
No MVC, a MainActivity (View) obtém a entrada do usuário e a passa para o UserController (Controller), que então cria um objeto User e atualiza a View. A View é ativa e pode solicitar dados do Controller.

No MVP, a MainActivity (View) é passiva e simplesmente exibe os dados do usuário fornecidos pelo UserPresenter (Presenter). O Presenter é responsável por obter os dados do usuário e passá-los para a View.
 */
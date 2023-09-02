// Model
data class User(val name: String, val age: Int)

object ModelDatabase {
    fun getUser(pos:Int): User {
        // load user data from database
        return User("John Doe", 30)
    }
}

// View
interface UserView {
    fun getUserInput(): String
    fun showUser(user: User)
}

// Controller
class UserController(val view: UserView) {

    fun onShowUserClicked() {
        val userInput = view.getUserInput()
        val user = User(userInput, 30) // Assume age is 30 for this example
        view.showUser(user)
    }
}

// Activity
class MainActivity : AppCompatActivity(), UserView {

    private lateinit var controller: UserController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller = UserController(this)
    }

    override fun getUserInput(): String {
        // get user input from UI
        return "John Doe"
    }

    override fun showUser(user: User) {
        // update UI
        println("User name: ${user.name}, User age: ${user.age}")
    }

    // This method is called when a button is clicked in the UI
    fun onShowUserButtonClicked() {
        controller.onShowUserClicked()
    }
}
/*
No MVC, a MainActivity (View) obtém a entrada do usuário e a passa para o UserController (Controller), que então cria um objeto User e atualiza a View. A View é ativa e pode solicitar dados do Controller.
 */

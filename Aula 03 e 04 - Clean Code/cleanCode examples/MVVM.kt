import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// Model
//User é o Model. Ele representa os dados e a lógica de negócios do aplicativo.

data class User(val name: String, val age: Int)

// View
//UserView é a View. Ela define os métodos que o ViewModel usará para atualizar a View.

interface UserView {
    fun showUser(user: User)
}

// ViewModel
//UserViewModel é o ViewModel. Ele recupera os dados do Model e os passa para a View para serem exibidos.

class UserViewModel : ViewModel() {

    private val _user = MutableLiveData<String>().apply {
        value = User("John Doe", 30)
    }
    val user: LiveData<String> = _user
    
    fun fetchUserDetails() {
        // fetch user details from database
        _user.value = User("John Doe", 33)
    }
}

// Activity
//MainActivity é a Activity que implementa a UserView e é responsável por exibir os dados do usuário na interface do usuário.

class MainActivity : AppCompatActivity(), UserView {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.user.observe(viewLifecycleOwner) {
            showUser(it)
        }
        viewModel.fetchUserDetails()
    }

    override fun showUser(user: User) {
        // update UI
        println("User name: ${user.name}, User age: ${user.age}")
    }
}
/*
O MainActivity cria uma instância do UserViewModel, anexa-se como a View e chama o método loadUser para carregar os dados do usuário. O UserViewModel carrega os dados do usuário e os passa para o MainActivity para serem exibidos. O UserViewModel não sabe nada sobre a interface do usuário, o que torna o código mais modular e testável.
 */

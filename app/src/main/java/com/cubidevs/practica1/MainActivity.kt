package com.cubidevs.practica1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cubidevs.practica1.databinding.ActivityMainBinding

private const val EMPTY = ""
private const val SPACE = " "

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private var users: MutableList<User> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.saveButton.setOnClickListener {
            val email = mainBinding.emailEditText.text.toString()
            val password = mainBinding.passwordEditText.text.toString()
            val repPassword = mainBinding.repPasswordEditText.text.toString()
            val genre = if (mainBinding.femaleRadioButton.isChecked) getString(R.string.female) else getString(R.string.male)
            var hobbies = if (mainBinding.danceCheckBox.isChecked) getString(R.string.dance) else EMPTY
            hobbies = hobbies + SPACE + if (mainBinding.eatCheckBox.isChecked) getString(R.string.eat) else EMPTY
            hobbies = hobbies + SPACE + if (mainBinding.readCheckBox.isChecked) getString(R.string.read) else EMPTY
            hobbies = hobbies + SPACE + if (mainBinding.sportCheckBox.isChecked) getString(R.string.sport) else EMPTY

            if (email.isNotEmpty()) {
                if (password == repPassword) {
                    saveUser(email, password, genre, hobbies)
                    mainBinding.repPasswordTextInputLayout.error = null
                } else
                    mainBinding.repPasswordTextInputLayout.error = getString(R.string.password_error)
            } else
                Toast.makeText(this, getString(R.string.email_error), Toast.LENGTH_LONG).show()
            cleanViews()
        }
    }

    private fun cleanViews() {
        with(mainBinding) {
            emailEditText.setText(EMPTY)
            passwordEditText.setText(EMPTY)
            repPasswordEditText.setText(EMPTY)
            femaleRadioButton.isChecked = true
            danceCheckBox.isChecked = false
            eatCheckBox.isChecked = false
            sportCheckBox.isChecked = false
            readCheckBox.isChecked = false
        }
    }

    private fun saveUser(email: String, password: String, genre: String, hobbies: String) {
        val newUser = User(email, password, genre, hobbies)
        users.add(newUser)
        printUserData()
    }

    private fun printUserData() {
        var info = ""
        for (user in users)
            info = info + "\n\n" + user.email + "\n" + user.genre + "\n" + user.hobbies
        mainBinding.infoTextView.text = info
    }
}
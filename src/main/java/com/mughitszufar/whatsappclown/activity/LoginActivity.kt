package com.mughitszufar.whatsappclown.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.mughitszufar.whatsappclown.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseAuthListener = FirebaseAuth.AuthStateListener {
        val user = firebaseAuth.currentUser?.uid
        if (user != null){
            val intent  = Intent(this, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)//menghilangkan ActionBar
        setContentView(R.layout.activity_login)

        setTextChangedListener(edt_email, til_email)
        setTextChangedListener(edt_password, til_password)
        progress_layout.setOnTouchListener { v, event -> true}

        btn_login.setOnClickListener {
            onLogin()
        }

        txt_signup.setOnClickListener {
            onSignup()
        }
    }

    private fun onSignup() {
        startActivity(Intent(this, SignUpActivity::class.java))

    }



    private fun setTextChangedListener(edt: EditText, til: TextInputLayout) {
        edt.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?
                                           , p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?
                                       , p1: Int, p2: Int, p3: Int) {
                til.isErrorEnabled = false

            }
        })
    }

    private fun onLogin() {
        var proceed = true

        if (edt_email.text.isNullOrEmpty()) { // check jika EditText kosong
            til_email.error = "Required Password" // TextInputLayout(til) menampilkan pesan
            til_email.isErrorEnabled = true // mengubah state til yang sebelumnya tidak
            proceed = false // menampilkan error sekarang menampilkan
        }

        if (edt_password.text.isNullOrEmpty()) {
            til_password.error = "Required Password"
            til_password.isErrorEnabled = true
            proceed = false
        }

        if (proceed) {
            progress_layout.visibility = View.VISIBLE // menampilkan ProgressBar
            firebaseAuth.signInWithEmailAndPassword( // untuk menunjukkan bahwa ada
                edt_email.text.toString(), // proses yang sedang dilakukan
                edt_password.text.toString() // mengubah data dalam editText jadi string
            )
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful){
                        progress_layout.visibility = View.GONE
                        Toast.makeText(
                            this@LoginActivity,
                            "Login error: ${task.exception?.localizedMessage}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                .addOnFailureListener { e ->
                    progress_layout.visibility = View.GONE
                        e.printStackTrace()
                }
            }
        }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(firebaseAuthListener)
    }

    override fun onStop() {
        super.onStop()
        firebaseAuth.removeAuthStateListener(firebaseAuthListener)
    }


    }





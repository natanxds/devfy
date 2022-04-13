package com.example.mainscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.android_api.model.LoginResponse
import com.example.android_api.model.UserEmpresa
import com.example.android_api.rest.RestEmpresa
import com.example.android_api.service.EmpresaService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    private val retrofit = RestEmpresa.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun logar(view: View) {

        val usuarioDigitado: String = findViewById<EditText>(R.id.et_usuario).text.toString()
        val senhaDigitada: String = findViewById<EditText>(R.id.et_usuario).text.toString()
        var usuarioEmpresa = UserEmpresa(usuarioDigitado, senhaDigitada)

        val postEmpresa = retrofit.create(EmpresaService::class.java)

        val loginResponseCall: Call<LoginResponse> = postEmpresa.logar(usuarioEmpresa)

        loginResponseCall.enqueue(object: Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.body()?.isLogado == true){
                    //TODO: implementar logica de login sucesso
                Toast.makeText(baseContext,  "DEU TUDO CERTO" + response.body().toString(), Toast.LENGTH_LONG).show()
                }else {
                    //TODO: implementar logica de login false
                    Toast.makeText(baseContext,  "LOGIN ERRADO MANO" + response.body().toString(), Toast.LENGTH_LONG).show()

                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(baseContext,  "DEU ERRO FAMILIA", Toast.LENGTH_LONG).show()
            }

        })




    }


}
package br.edu.fatecpg.projetomvvm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.fatecpg.projetomvvm.databinding.ActivityAdminBinding
import br.edu.fatecpg.projetomvvm.databinding.ActivityMainBinding
import br.edu.fatecpg.projetomvvm.model.User
import br.edu.fatecpg.projetomvvm.viewmodel.LoginViewModel

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val users = intent.getSerializableExtra("users") as? ArrayList<User> ?: arrayListOf()

        val adapter = object : ArrayAdapter<User>(this, R.layout.item_usuario, users) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = layoutInflater.inflate(R.layout.item_usuario, parent, false)
                val tvLogin = view.findViewById<TextView>(R.id.tvLogin)
                val tvStatus = view.findViewById<TextView>(R.id.tvStatus)

                val user = users[position]
                tvLogin.text = user.login
                tvStatus.text = if (user.bloqueado) "Bloqueado" else "Ativo"
                tvStatus.setTextColor(
                    if (user.bloqueado)
                        resources.getColor(android.R.color.holo_red_dark)
                    else
                        resources.getColor(android.R.color.holo_green_dark)
                )

                return view
            }
        }

        binding.listUsers.adapter = adapter
    }
}

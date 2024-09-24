package com.example.provakotlin

import Estoque
import Estoque.Companion.produtos
import Produto
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    class ProdutoRepository {
        companion object ProdutoRepository {
            val produtos = mutableListOf<Produto>()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutMain()
        }
    }
}

@Composable
fun LayoutMain() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "lista") {
        composable("lista") { ListaProdutos(navController) }
        composable("cadastro") { CadastroProduto(navController) }
        composable("detalhes/{produtoJson}") { backStackEntry ->
            val produtoJson = backStackEntry.arguments?.getString("produtoJson")
            val produto = Gson().fromJson(produtoJson, Produto::class.java)
            DetalhesProduto(navController, produto)
        }
        composable("estatisticas") { Estatisticas(navController) }
    }

}



@Composable
fun DetalhesProduto(navController: NavHostController, produto: Produto) {

}

@Composable
fun ListaProdutos(navController: NavHostController) {
    val produto =   Estoque.listarProdutos()


    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Lista de Produtos", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(produtos) { produto ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "${produto.nome} (${produto.quantidade} unidades)")
                    Button(onClick = {
                    }) {
                        Text(text = "Detalhes")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("estatisticas")
        }) {
            Text(text = "Ver Estatísticas")
        }
    }
}

@Composable
fun Estatisticas(navController: NavHostController) {
    val valorTotal = Estoque.calcularValorTotalEstoque()
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Estatísticas do Estoque", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Valor Total do Estoque: R$ ${"%.2f".format(valorTotal)}")
        Spacer(modifier = Modifier.height(8.dp))

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.popBackStack() // Volta para a tela anterior
        }) {
            Text(text = "Voltar")
        }
    }
}


@Composable
fun CadastroProduto(navController: NavHostController) {
    val context = LocalContext.current

    var nome by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "Cadastro de Produto", modifier = Modifier.padding(bottom = 16.dp))


        Button(onClick = {
            if (nome.isBlank() || categoria.isBlank() || preco.isBlank() || quantidade.isBlank()) {
                Toast.makeText(context, "Todos os campos são obrigatórios", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    val precoDouble = preco.toDouble()
                    val quantidadeInt = quantidade.toInt()

                    if (precoDouble < 0) {
                        Toast.makeText(context, "O preço não pode ser menor que zero", Toast.LENGTH_SHORT).show()
                    } else if (quantidadeInt < 1) {
                        Toast.makeText(context, "A quantidade deve ser pelo menos 1", Toast.LENGTH_SHORT).show()
                    } else {
                        Estoque.adicionarProduto(Produto(nome, categoria, precoDouble, quantidadeInt))

                        Toast.makeText(context, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show()

                        navController.navigate("lista")
                    }
                } catch (e: NumberFormatException) {
                    Toast.makeText(context, "Preço e Quantidade devem ser numéricos", Toast.LENGTH_SHORT).show()
                }
            }
        }) {
            Text("Cadastrar Produto")
        }
    }
}


    @Preview(showBackground = true)
    @Composable
    fun LayoutPreview() {
        LayoutMain()
    }



package com.example.provakotlin

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson

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
fun Estatisticas(navController: NavHostController) {
    TODO("Not yet implemented")
}

@Composable
fun <NavHostController> DetalhesProduto(navController: NavHostController,
                                        produto: Produto?) {

}

@Composable
fun <NavHostController> CadastroProduto(navController: NavHostController) {

}

class Produto {

}

@Composable
fun <NavHostController> ListaProdutos(navController: NavHostController) {

}

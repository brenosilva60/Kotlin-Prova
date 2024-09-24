data class  Produto(
    val nome: String,
    val categoria: String,
    val preco: Double,
    val quantidade: Int,
    val produtos: Produto
) {


    class ProdutoRepository {
        companion object {
            lateinit var produtos: Produto
        }
    }
}


class Estoque {

    companion object {
        private val produtos = mutableListOf<Produto>()

        fun adicionarProduto(produto: Produto) {
            produtos.add(produto)
        }

        fun calcularValorTotalEstoque(): Double {
            return produtos.sumOf { it.preco * it.quantidade }
        }

        fun getProdutos(): List<Produto> {
            return produtos
        }
    }
}

class Estoque {
    companion object {
        var produtos = listOf<Produto>()
        fun adicionarProduto(produto: Produto) {
            produtos = produtos + produto
        }

        fun calcularValorTotalEstoque(): Double {
            var valorTotal = 0.0;

            for (produto in produtos) {
                valorTotal += (produto.quantidade * produto.preco);
            }
            return valorTotal
        }

        fun listarProdutos() {
            TODO("Not yet implemented")
        }
    }
}
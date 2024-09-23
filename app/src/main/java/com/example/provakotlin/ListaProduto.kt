import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.aula14.R
import com.google.gson.Gson

class ListaProdutosActivity : AppCompatActivity() {

    private lateinit var lvProdutos: ListView
    private lateinit var btnCadastrarProduto: Button
    private lateinit var btnEstatisticas: Button
    private lateinit var adapter: ArrayAdapter<String>
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_produtos)

        lvProdutos = findViewById(R.id.lvProdutos)
        btnCadastrarProduto = findViewById(R.id.btnCadastrarProduto)
        btnEstatisticas = findViewById(R.id.btnEstatisticas)

        btnCadastrarProduto.setOnClickListener {
            val intent = Intent(this, CadastroProdutoActivity::class.java)
            startActivity(intent)
        }

        btnEstatisticas.setOnClickListener {
            val intent = Intent(this, EstatisticasActivity::class.java)
            startActivity(intent)
        }

        // Atualiza a lista de produtos
        updateProductList()

        lvProdutos.setOnItemClickListener { _, _, position, _ ->
            val produto = Estoque.getProdutos()[position]
            val produtoJson = gson.toJson(produto)
            val intent = Intent(this, DetalhesProdutoActivity::class.java).apply {
                putExtra("produto", produtoJson)
            }
            startActivity(intent)
        }
    }

    class DetalhesProdutoActivity {

    }

    class EstatisticasActivity {

    }

    private fun updateProductList() {
        val productDescriptions = Estoque.getProdutos().map {
            "${it.nome} (${it.quantidade} unidades)"
        }
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, productDescriptions)
        lvProdutos.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        updateProductList()  // Atualiza a lista sempre que a atividade é retomada
    }
}

package java.br.edu.ifam.nutriveg;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProductDatabaseHelper databaseHelper;
    private LinearLayout productContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productContainer = findViewById(R.id.productContainer);
        databaseHelper = new ProductDatabaseHelper(this);

        // Inserir produtos no banco apenas na primeira vez
        if (databaseHelper.getAllProducts().isEmpty()) {
            databaseHelper.insertProduct("Milkshake Vegano", 2.59);
            databaseHelper.insertProduct("Produto Saudável", 3.49);
        }

        // Exibir produtos
        displayProducts();
    }

    private void displayProducts() {
        List<Product> products = databaseHelper.getAllProducts();
        for (Product product : products) {
            // Inflate o layout de cada item
            View productView = getLayoutInflater().inflate(R.layout.product_item, productContainer, false);

            // Encontre os TextViews dentro do layout inflado
            TextView productName = productView.findViewById(R.id.productName);
            TextView productPrice = productView.findViewById(R.id.productPrice);

            // Defina os valores do produto
            productName.setText(product.getName());
            productPrice.setText(String.format("$%.2f", product.getPrice()));

            // Adicione o layout inflado ao contêiner principal
            productContainer.addView(productView);
        }
    }

}


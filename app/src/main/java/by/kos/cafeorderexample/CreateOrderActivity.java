package by.kos.cafeorderexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import by.kos.cafeorderexample.databinding.ActivityCreateOrderBinding;

public class CreateOrderActivity extends AppCompatActivity {
    private ActivityCreateOrderBinding binding;
    private String drink;
    private StringBuilder builderDrinkOptions;
    private String name;
    private String password;
    private String drink_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateOrderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setupWelcome();

        builderDrinkOptions = new StringBuilder();

        binding.rbTea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickChangeDrink(v);
            }
        });

        binding.rbCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickChangeDrink(v);
            }
        });

        binding.btnCreateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOrder(v);
            }
        });
    }

    private void onClickChangeDrink(View v) {
        RadioButton button = (RadioButton) v;
        int id = button.getId();
        if (id == R.id.rbTea) {
            drink = getString(R.string.txt_tea);
            binding.spinnerTea.setVisibility(View.VISIBLE);
            binding.spinnerCoffee.setVisibility(View.INVISIBLE);
            binding.cbLemon.setVisibility(View.VISIBLE);
        } else if (id == R.id.rbCoffee) {
            drink = getString(R.string.txt_coffee);
            binding.spinnerTea.setVisibility(View.INVISIBLE);
            binding.spinnerCoffee.setVisibility(View.VISIBLE);
            binding.cbLemon.setVisibility(View.INVISIBLE);
        }
    }

    private void sendOrder(View v) {
        builderDrinkOptions.setLength(0);
        builderDrinkOptions.append(getString(R.string.order_name)).append(name).append("\n");
        builderDrinkOptions.append(getString(R.string.order_password)).append(password).append("\n");
        builderDrinkOptions.append(getString(R.string.order_drink)).append(drink).append("\n");

        if (drink.equals(getString(R.string.txt_tea))) {
            drink_type = binding.spinnerTea.getSelectedItem().toString();
        } else drink_type = binding.spinnerCoffee.getSelectedItem().toString();
        builderDrinkOptions.append(getString(R.string.order_type_drink)).append(drink_type).append("\n");

        builderDrinkOptions.append(getString(R.string.order_options)).append("\n");
        if (binding.cbMilk.isChecked()) {
            builderDrinkOptions.append(getString(R.string.milk)).append("\n");
        }
        if (binding.cbSugar.isChecked()) {
            builderDrinkOptions.append(getString(R.string.sugar)).append("\n");
        }
        if (binding.cbLemon.isChecked() && drink.equals(getString(R.string.txt_tea))) {
            builderDrinkOptions.append(getString(R.string.lemon)).append("\n");
        }
        if (!binding.cbMilk.isChecked() && !binding.cbSugar.isChecked() && !binding.cbLemon.isChecked()) {
            builderDrinkOptions.append(getString(R.string.order_options_none)).append("\n");
        }

        String order = builderDrinkOptions.toString();
        Intent intent = new Intent(v.getContext(), OrderDetailActivity.class);
        intent.putExtra("order", order);
        startActivity(intent);
    }

    private void setupWelcome() {
        Intent intent = getIntent();
        if (intent.hasExtra("Name") && intent.hasExtra("Password")) {
            name = intent.getStringExtra("Name");
            password = intent.getStringExtra("Password");
        } else {
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }
        String hello = String.format(getString(R.string.hello_user), name);
        binding.tvWelcome.setText(hello);

    }
}
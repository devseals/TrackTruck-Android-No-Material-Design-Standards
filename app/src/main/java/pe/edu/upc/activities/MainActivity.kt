package pe.edu.upc.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_food_trucks_list.*
import pe.edu.upc.R
import pe.edu.upc.fragments.AdministrativeFragment
import pe.edu.upc.fragments.FoodtrucksFragment


class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        navigateTo(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_trucks_list)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigateTo(navigation.menu.findItem(R.id.navigation_trucks))
    }

    private fun getFragmentFor(item: MenuItem): Fragment {
        when (item.itemId) {
            R.id.navigation_trucks -> {
                return FoodtrucksFragment()
            }
            R.id.navigation_administrative -> {
                return AdministrativeFragment()
            }
        }
        return FoodtrucksFragment()
    }

    private fun navigateTo(item: MenuItem):Boolean{

        item.isChecked = true

        return supportFragmentManager.beginTransaction()
            .replace(R.id.content,getFragmentFor(item))
            .commit() > 0

    }

}

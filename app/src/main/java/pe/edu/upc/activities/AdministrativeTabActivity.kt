package pe.edu.upc.activities

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import pe.edu.upc.R
import pe.edu.upc.activities.ui.main.SectionsPagerAdapter
import pe.edu.upc.activities.ui.main.SectionsPagerAdapter2

class AdministrativeTabActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrative_tab)
        val sectionsPagerAdapter = SectionsPagerAdapter2(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager2)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs2)
        tabs.setupWithViewPager(viewPager)
    }
}
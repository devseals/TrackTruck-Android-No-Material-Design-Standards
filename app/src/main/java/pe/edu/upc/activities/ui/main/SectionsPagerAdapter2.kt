package pe.edu.upc.activities.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import pe.edu.upc.R
import pe.edu.upc.fragments.*

private val TAB_TITLES2 = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_5
)

class SectionsPagerAdapter2(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        when(position){
            1->{
                return OwnerLoginFragment()
            }
            2->{
                return  OwnerRegisterFragment()
            }
            3->{
                return EmployeeLoginFragment()
            }
        }
        return OwnerLoginFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES2[position])
    }

    override fun getCount(): Int {

        return 3
    }
}
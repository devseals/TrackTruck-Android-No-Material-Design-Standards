package pe.edu.upc.activities.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import pe.edu.upc.R
import pe.edu.upc.fragments.UserLoginFragment
import pe.edu.upc.fragments.UserRegisterFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_3,
    R.string.tab_text_4
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when(position){
            1->{
                return UserLoginFragment()
            }
            2->{
                return UserRegisterFragment()
            }
        }
        return UserLoginFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}
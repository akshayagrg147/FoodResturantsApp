package com.meetSuccess.FoodResturant.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.meetSuccess.FoodResturant.Model.Meals
import com.meetSuccess.FoodResturant.R

class ViewPagerHeaderAdapter(val meals: List<Meals.Meal>, context: Context): PagerAdapter() {


    private var context: Context? = null

    override fun getCount(): Int {
        return meals.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view: View = LayoutInflater.from(context).inflate(
            R.layout.item_view_pager_header,
            container,
            false
        )
        val mealThumb = view.findViewById<ImageView>(R.id.mealThumb)
        val mealName = view.findViewById<TextView>(R.id.mealName)

        val strMealThumb: String = meals[position].getstrMeal()
       // Picasso.get().load(strMealThumb).into(mealThumb)

        val strMealName: String = meals.get(position).getstrMeal()
        mealName.text = strMealName



        container.addView(view, 0)
        return super.instantiateItem(container, position)
    }
}
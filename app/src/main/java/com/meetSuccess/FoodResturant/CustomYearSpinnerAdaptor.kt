package com.meetSuccess.FoodResturant
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import java.util.*
import android.content.Context


class CustomYearSpinnerAdaptor(
     val context1: Context,
    textViewResourceId: Int,
    private var values: List<String>
) :
    ArrayAdapter<Any?>(context1, textViewResourceId, values) {
    var spinner: Spinner? = null
    override fun getCount(): Int {
        return values.size
    }

    override fun getItem(position: Int): String? {
        return if (position < values.size) values[position] else values[0]
    }

    override fun getItemId(position: Int): Long {
        return if (position < values.size) position.toLong() else 0
    }

    //@RequiresApi(api = Build.VERSION_CODES.O)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = TextView(context1)
        label.setPadding(40, 0, 30, 0)
        label.setCompoundDrawablesRelativeWithIntrinsicBounds(
            0,
            0,
            R.drawable.ic_spinner_arrow_new,
            0
        )
        label.setTextColor(context1.resources.getColor(R.color.black))
        label.textSize = 16.0f
        //Typeface.createFromAsset(context.getAssets(),"font/avenirltstd_medium.otf");
        val typeface = ResourcesCompat.getFont(context1, R.font.avenirltstd_medium)
        label.setTypeface(typeface)
        //label.setTypeface(context.getResources().getFont(R.font.avenirltstd_medium));
        if (position < values.size) {
            label.text = values[position]
        }
        return label
    }

    fun getCustomView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val textView = TextView(context1)
        textView.setPadding(20, 20, 20, 20)
        textView.setTextColor(context1.resources.getColor(R.color.black))
        textView.text = values[position]
        return textView
    }

    override fun getDropDownView(position: Int, convertView: View, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    fun updateData(list: ArrayList<String>) {
        values = list
        notifyDataSetChanged()
    }
}



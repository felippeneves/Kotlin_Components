package com.felippe_neves.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_time.*
import java.text.SimpleDateFormat
import java.util.*

class TimeActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener,
    TimePicker.OnTimeChangedListener {

    private val mContext: Context = this
    private val mSimpleDate = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        bt_date.setOnClickListener(this)
        bt_time.setOnClickListener(this)
        time_picker.setOnTimeChangedListener(this)
        bt_get_time.setOnClickListener(this)
        bt_set_time.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_date -> {
                val calendar = Calendar.getInstance()
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val month = calendar.get(Calendar.MONTH)
                val year = calendar.get(Calendar.YEAR)

                DatePickerDialog(this, this, year, month, day).show()
            }
            R.id.bt_time -> {
                val calendar = Calendar.getInstance()
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val month = calendar.get(Calendar.MONTH)
                val year = calendar.get(Calendar.YEAR)

                TimePickerDialog(this, this, 1, 1, false).show()
            }
            R.id.bt_get_time -> {

                var hour = 0
                var minute = 0

                if(Build.VERSION.SDK_INT > 23) {
                    hour = time_picker.hour
                    minute = time_picker.minute
                } else {
                    hour = time_picker.currentHour
                    minute = time_picker.currentMinute
                }
                toast("$hour:$minute")

                progress_default.visibility = View.GONE
            }
            R.id.bt_set_time -> {
                if(Build.VERSION.SDK_INT > 23) {
                    time_picker.hour = 20
                    time_picker.minute = 20
                } else {
                    time_picker.currentHour = 20
                    time_picker.currentMinute = 20
                }
            }
        }
    }

    private fun toast(str: String) {
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val date = Calendar.getInstance()
        date.set(year, month, dayOfMonth)
        bt_date.text = mSimpleDate.format(date.time)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        toast("$hourOfDay:$minute")
    }

    override fun onTimeChanged(view: TimePicker?, hourOfDay: Int, minute: Int) {
        toast("$hourOfDay:$minute")
    }
}
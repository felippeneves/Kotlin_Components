package com.felippe_neves.components

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener,
        CompoundButton.OnCheckedChangeListener{

    private val mContext: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_toast.setOnClickListener(this)
        bt_snack.setOnClickListener(this)
        bt_get_spinner.setOnClickListener(this)
        bt_set_spinner.setOnClickListener(this)
        bt_get_seekbar.setOnClickListener(this)
        bt_set_seekbar.setOnClickListener(this)


        sp_static.onItemSelectedListener = this
        seekbar.setOnSeekBarChangeListener(this)

        sw_on_off.setOnCheckedChangeListener(this)
        cb_on_off.setOnCheckedChangeListener(this)
        rb_on.setOnCheckedChangeListener(this)
        rb_off.setOnCheckedChangeListener(this)

        loadSpinner()
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.bt_toast -> {
                toastCustom()
            }
            R.id.bt_snack -> {
                snackbar()
            }
            R.id.bt_get_spinner -> {
                val selectedItem = sp_static.selectedItem
                val selectedItemId = sp_static.selectedItemId
                val selectedItemPosition = sp_static.selectedItemPosition

                toast("Position: $selectedItemId, $selectedItem")
            }
            R.id.bt_set_spinner -> {
                sp_static.setSelection(2)
            }
            R.id.bt_get_seekbar -> {
                toast("Seekbar: ${seekbar.progress}")
            }
            R.id.bt_set_seekbar -> {
                seekbar.progress = 15
            }
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        when (buttonView.id) {
            R.id.sw_on_off -> {
                toast("Switch: ${if (isChecked) "true" else "false"}")
//                sw_on_off.isChecked = true
            }
            R.id.cb_on_off -> {
                toast("Checkbox: ${if (isChecked) "true" else "false"}")
//                cb_on_off.isChecked = false
            }
            R.id.rb_on -> {
                toast("Radio on: ${if (isChecked) "true" else "false"}")
//                rb_on.isChecked = false
            }
            R.id.rb_off -> {
                toast("Radio off: ${if (isChecked) "true" else "false"}")
//                rb_off.isChecked = false
            }
        }
    }

    //region Toast

    private fun toast(str: String) {
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show()
    }

    private fun toastCustom() {
        val toast = Toast.makeText(mContext, "TOAST", Toast.LENGTH_SHORT)
//        toast.setGravity(Gravity.TOP, 0, 250)

        val layout = layoutInflater.inflate(R.layout.toast_layout, null)
        toast.view = layout

        layout.findViewById<AppCompatTextView>(R.id.tv_toast).text = "TESTEEEEEEEEEEEEE"

        toast.show()
    }

    //endregion

    //region Snackbar

    private fun snackbar() {
        val snack = Snackbar.make(ll_root, "Snack", Snackbar.LENGTH_LONG)

        snack.setAction("Desfazer") {
            toast("Desfeito")
        }

        snack.setActionTextColor(Color.BLUE)
        snack.setBackgroundTint(Color.GRAY)


        snack.show()

    }

    //endregion

    //region Spinner

    private fun loadSpinner() {
        val mList = listOf("Gramas", "Kg", "Arroba", "Tonelada")
        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, mList)
        sp_dynamic.adapter = adapter
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id) {
            R.id.sp_static -> {
                val text = parent?.getItemAtPosition(position).toString()
                toast(text)
            }
        }
    }

    //endregion

    //region Seekbar

    override fun onNothingSelected(parent: AdapterView<*>?) {
        toast("Nothing")
    }

    override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
        tv_seekbar_value.text = "Valor seekbar: $progress"
    }

    //Chamado quando clica no seekbar
    override fun onStartTrackingTouch(seekbar: SeekBar?) {
        toast("Track started")
    }

    override fun onStopTrackingTouch(seekbar: SeekBar?) {
        toast("Track stoped")
    }

    //endregion
}
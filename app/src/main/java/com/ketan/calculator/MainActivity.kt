package com.ketan.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    lateinit var txtInput: EditText
    lateinit var txtAnswer: TextView
    lateinit var txtClear: Button
    lateinit var txtBackspace: ImageButton
    lateinit var txt7 : Button
    lateinit var txt8: Button
    lateinit var txt9: Button
    lateinit var txt4: Button
    lateinit var txt5: Button
    lateinit var txt6: Button
    lateinit var txt1: Button
    lateinit var txt2: Button
    lateinit var txt3: Button
    lateinit var txt0: Button
    lateinit var txt00: Button
    lateinit var btnDiv: Button
    lateinit var btnMul: Button
    lateinit var btnsub: Button
    lateinit var btnadd: Button
    lateinit var btnequal: Button
    lateinit var txtPercent: Button
    lateinit var txtdecimal: Button
    var clear_val = true

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtInput = findViewById(R.id.txtInput)
        txtAnswer = findViewById(R.id.txtAnswer)
        txtClear = findViewById(R.id.txtClear)
        txtBackspace = findViewById(R.id.txtBackspace)
        txt7 = findViewById(R.id.txt7)
        txt8 = findViewById(R.id.txt8)
        txt9 = findViewById(R.id.txt9)
        txt4 = findViewById(R.id.txt4)
        txt5 = findViewById(R.id.txt5)
        txt6 = findViewById(R.id.txt6)
        txt1 = findViewById(R.id.txt1)
        txt2 = findViewById(R.id.txt2)
        txt3 = findViewById(R.id.txt3)
        txt0 = findViewById(R.id.txt0)
        txt00 = findViewById(R.id.txt00)
        btnDiv = findViewById(R.id.btnDiv)
        btnMul = findViewById(R.id.btnMul)
        btnsub = findViewById(R.id.btnsub)
        btnadd = findViewById(R.id.btnadd)
        btnequal = findViewById(R.id.btnequal)
        btnsub = findViewById(R.id.btnsub)
        txtPercent = findViewById(R.id.txtPercent)
        txtdecimal = findViewById(R.id.txtdecimal)


        txt1.setOnClickListener {
            evalExp("1", clear = true)
        }
        txt2.setOnClickListener {
            evalExp("2", clear = true)

        }
        txt3.setOnClickListener {
            evalExp("3", clear = true)
        }
        txt4.setOnClickListener {
            evalExp("4", clear = true)
        }
        txt5.setOnClickListener {
            evalExp("5", clear = true)
        }
        txt6.setOnClickListener {
            evalExp("6", clear = true)
        }
        txt7.setOnClickListener {
            evalExp("7", clear = true)
        }
        txt8.setOnClickListener {
            evalExp("8", clear = true)
        }
        txt9.setOnClickListener {
            evalExp("9", clear = true)
        }
        txt0.setOnClickListener {
            evalExp("0", clear = true)
        }
        txt00.setOnClickListener {
            evalExp("00", clear = true)
        }
        txtdecimal.setOnClickListener {
            val data = txtInput.text.toString()
            val dataAns = txtAnswer.text.toString()

            if (data.isNotEmpty()) {
                if (!data.endsWith(".") && !data.startsWith(".")) {

                    if (dataAns.isNotEmpty())
                    {

                        evalExp(".", clear = false)
                    }
                    else
                    {
                        evalExp(".", clear = true)
                    }
                }
            }
            else
            {
                evalExp("0.", clear = true)
            }
        }
        btnadd.setOnClickListener {
            val data = txtInput.text.toString()
            val dataAns = txtAnswer.text.toString()

            if (data.isNotEmpty()) {
                if (!data.endsWith("+") && !data.startsWith("+")) {

                    if (dataAns.isNotEmpty())
                    {

                        evalExp("+", clear = false)
                    }
                    else
                    {
                        evalExp("+", clear = true)
                    }
                }
            }
        }
        btnsub.setOnClickListener {
            val data = txtInput.text.toString()
            val dataAns = txtAnswer.text.toString()

            if (data.isNotEmpty()) {
                if (!data.endsWith("-") && !data.startsWith("-")) {

                    if (dataAns.isNotEmpty())
                    {

                        evalExp("-", clear = false)
                    }
                    else
                    {
                        evalExp("-", clear = true)
                    }
                }
            }
        }
        btnMul.setOnClickListener {
            val data = txtInput.text.toString()
            val dataAns = txtAnswer.text.toString()

            if (data.isNotEmpty()) {
                if (!data.endsWith("×") && !data.startsWith("×")) {

                    if (dataAns.isNotEmpty())
                    {

                        evalExp("×", clear = false)
                    }
                    else
                    {
                        evalExp("×", clear = true)
                    }
                }
            }
        }
        btnDiv.setOnClickListener {
            val data = txtInput.text.toString()
            val dataAns = txtAnswer.text.toString()

            if (data.isNotEmpty()) {
                if (!data.endsWith("/") && !data.startsWith("/")) {

                    if (dataAns.isNotEmpty())
                    {

                        evalExp("/", clear = false)
                    }
                    else
                    {
                        evalExp("/", clear = true)
                    }
                }
            }
        }
        txtPercent.setOnClickListener {
            val data = txtInput.text.toString()
            val dataAns = txtAnswer.text.toString()

            if (data.isNotEmpty()) {
                if (!data.endsWith("%") && !data.startsWith("%")) {

                    if (dataAns.isNotEmpty())
                    {

                        evalExp("%", clear = false)
                    }
                    else
                    {
                        evalExp("%", clear = true)
                    }
                }
            }
        }

        txtClear.setOnClickListener {
            txtAnswer.text = ""
            txtInput.text.clear()
        }

        txtBackspace.setOnClickListener {
            txtAnswer.text = ""
            val data = txtInput.text.toString()
            if(!data.isEmpty())
            {
                txtInput.setText(data.substring(0, data.length - 1))
            }
        }

        btnequal.setOnClickListener {

            var text = txtInput.text.toString()
            text = text.replace("×","*")

            if (!text.matches("\\d+(\\.\\d+)?".toRegex()) && text.isNotEmpty() && text.lastOrNull() !in(arrayOf('+', '-', '/', '*', '.'))) {
                val expression = ExpressionBuilder(text).build()
                val result = expression.evaluate()
                val longResult = result.toLong()

                if (result == longResult.toDouble()) {
                    txtAnswer.text = longResult.toString()
                } else {
                    txtAnswer.text = result.toString()
                }
            }
        }
    }

    fun evalExp(string: String, clear : Boolean)
    {
        if(clear)
        {

            txtAnswer.text = ""
            txtInput.append(string)
        }
        else
        {
            txtInput.text.clear()
            txtInput.append(txtAnswer.text)
            txtInput.append(string)
            txtAnswer.text = ""
        }
    }
}
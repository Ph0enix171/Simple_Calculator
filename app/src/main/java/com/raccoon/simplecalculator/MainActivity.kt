package com.raccoon.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var lastdot=false
    var lastdigit=false
    var operatorClicked=false

    fun digitClick(view:View)
    {
        text_result.append((view as Button).text)
        lastdigit=true
        operatorClicked=false
    }
    fun operatorClick(view: View)
    {

        if(!operatorClicked && lastdigit && text_result.text.toString().length>0)
        {
            text_result.append((view as Button).text)
            operatorClicked=true
            lastdot=false
            lastdigit=false
        }
        else if(!operatorClicked && text_result.text.toString().length==0)
        {
            text_result.append((view as Button).text)
            operatorClicked=true
        }
    }
    fun clearText(view: View)
    {
        text_result.setText("")
        lastdot=false
        lastdigit=false
        operatorClicked=false
    }
    fun clearOnce(view: View)
    {
        var str=text_result.text?.toString()
        if (str != null) {
            if(str.length>0) {
                if (str[str.length - 1] == '.')
                    lastdot = false
                else if(str[str.length - 1] == '/'||str[str.length - 1] == '+'||str[str.length - 1] == 'X'||str[str.length - 1] == '-')
                    operatorClicked=false
                str = str.substring(0, str.length - 1)
                text_result.setText(str)
            }

        }
    }
    fun dotClick(view: View)
    {
        val str=text_result?.toString()
        if(!lastdot && lastdigit && !operatorClicked) {
            text_result.append(".")
            lastdot=true
        }

        else if(!lastdot && !lastdigit &&!operatorClicked) {
                text_result.append("0.")
                lastdot=true

        }
    }
    fun onEqual(view:View)
    {
        operatorClicked=false
        lastdot=true
        if(lastdigit)
        {
            var textResult=text_result.text.toString()
            try{
                var prefix=""
                if(textResult.startsWith("-")){
                    textResult=textResult.substring(1)
                    prefix="-"
                }


                if(textResult.contains("-"))
                {
                    var splitResult=textResult.split("-")

                    var a=splitResult[0]
                    var b=splitResult[1]
                    if(!prefix.isEmpty())
                        a=prefix+a
                    text_result.setText(removeZero((a.toDouble()-b.toDouble()).toString()))
                }
                else if(textResult.contains("+"))
                {
                    var splitResult=textResult.split("+")

                    var a=splitResult[0]
                    var b=splitResult[1]
                    if(!prefix.isEmpty())
                        a=prefix+a
                    text_result.setText(removeZero((a.toDouble()+b.toDouble()).toString()))
                }
                else if(textResult.contains("/"))
                {
                    var splitResult=textResult.split("/")

                    var a=splitResult[0]
                    var b=splitResult[1]
                    if(!prefix.isEmpty())
                        a=prefix+a
                    text_result.setText(removeZero((a.toDouble()/b.toDouble()).toString()))
                }
                else if(textResult.contains("X"))
                {
                    var splitResult=textResult.split("X")

                    var a=splitResult[0]
                    var b=splitResult[1]
                    if(!prefix.isEmpty())
                        a=prefix+a
                    text_result.setText(removeZero((a.toDouble()*b.toDouble()).toString()))
                }

            }
            catch(e:ArithmeticException) {
                e.printStackTrace()
            }
        }
    }
    fun removeZero(s:String):String{
        val dec=DecimalFormat("#.0000")
        var str:Double=s.toDouble()
        str=dec.format(str).toDouble()
        return str.toString()
    }

}
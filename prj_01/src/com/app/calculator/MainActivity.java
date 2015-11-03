package com.app.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    boolean isdotclicked = false;
    TextView t1, t2, s;
    int condition = 0;
    Button one, two, three, four, five, six, seven, eight, nine, zero, plus, minus, plmn, mult, dev, c, del, eq, dot, author, pow;

    public int getRotation() {
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        switch (rotation) {
            case Surface.ROTATION_0:
            case Surface.ROTATION_180:
                return 1;
            case Surface.ROTATION_90:
            case Surface.ROTATION_270:
                return 2;
            default:
                return 1;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int rotation = getRotation();
        switch (rotation) {
            case 1:
                setContentView(R.layout.main);
                break;
            case 2:
                setContentView(R.layout.main2);
                break;
        }

        t1 = (TextView) findViewById(R.id.text1);
        t2 = (TextView) findViewById(R.id.text2);
        s = (TextView) findViewById(R.id.sign);
        t1.setText("0");
        one = (Button) findViewById(R.id.one);
        //one.setOnClickListener();
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        c = (Button) findViewById(R.id.C);
        del = (Button) findViewById(R.id.del);
        zero = (Button) findViewById(R.id.zero);
        dot = (Button) findViewById(R.id.dot);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        plmn = (Button) findViewById(R.id.neg);
        mult = (Button) findViewById(R.id.mult);
        dev = (Button) findViewById(R.id.dev);
        eq = (Button) findViewById(R.id.EQ);
        author = (Button) findViewById(R.id.author);
        pow = (Button) findViewById(R.id.pow);
    }

    public void StartAnimation1(View v) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        v.startAnimation(animation);
    }

    public void ClickPow(View v) {
        StartAnimation1(pow);
        isdotclicked = false;
        if (!t1.getText().toString().equals("")) {
            if (!s.getText().toString().equals("")) {
                double a = Double.parseDouble(t2.getText().toString());
                double b = Double.parseDouble(t1.getText().toString());
                double value = 0;
                switch (s.getText().toString()) {
                    case "+":
                        value = a + b;
                        break;
                    case "-":
                        value = a - b;
                        break;
                    case "/":
                        value = a / b;
                        break;
                    case "*":
                        value = a * b;
                        break;
                    case "^":
                        value = Math.pow(a, b);
                        break;
                }
                if (value == (int) value) {
                    t2.setText(Integer.toString((int) value));
                } else {
                    t2.setText(Double.toString(value));
                }
            } else {
                t2.setText(t1.getText().toString());

            }
            s.setText("^");
            t1.setText("0");
        }

    }

    public void ClickLn(View v) {

    }

    public void ClickLg(View v) {

    }

    public void Click1(View v) {
        if(t1.getText() == "infinity"){
            t1.setText("");
        }
        StartAnimation1(one);
        if (!(t1.getText().toString().equals(""))) {
            if (t1.getText().toString().equals("0")) {
                t1.setText("1");
            } else
                t1.setText(t1.getText().toString() + "1");
        } else {
            t1.setText("1");
        }
    }

    public void Click2(View v) {
        if(t1.getText() == "infinity"){
            t1.setText("");
        }
        StartAnimation1(two);
        if (!(t1.getText().toString().equals(""))) {
            if (t1.getText().toString().equals("0")) {
                t1.setText("2");
            } else
                t1.setText(t1.getText().toString() + "2");
        } else {
            t1.setText("2");
        }
    }

    public void Click3(View v) {
        if(t1.getText() == "infinity"){
            t1.setText("");
        }
        StartAnimation1(three);
        if (!(t1.getText().toString().equals(""))) {
            if (t1.getText().toString().equals("0")) {
                t1.setText("3");
            } else
                t1.setText(t1.getText().toString() + "3");
        } else {
            t1.setText("3");
        }
    }

    public void Click4(View v) {
        if(t1.getText() == "infinity"){
            t1.setText("");
        }
        StartAnimation1(four);
        if (!(t1.getText().toString().equals(""))) {
            if (t1.getText().toString().equals("0")) {
                t1.setText("4");
            } else
                t1.setText(t1.getText().toString() + "4");
        } else {
            t1.setText("4");
        }
    }

    public void Click5(View v) {
        if(t1.getText() == "infinity"){
            t1.setText("");
        }
        StartAnimation1(five);
        if (!(t1.getText().toString().equals(""))) {
            if (t1.getText().toString().equals("0")) {
                t1.setText("5");
            } else
                t1.setText(t1.getText().toString() + "5");
        } else {
            t1.setText("5");
        }
    }

    public void Click6(View v) {
        if(t1.getText() == "infinity"){
            t1.setText("");
        }
        StartAnimation1(six);
        if (!(t1.getText().toString().equals(""))) {
            if (t1.getText().toString().equals("0")) {
                t1.setText("6");
            } else
                t1.setText(t1.getText().toString() + "6");
        } else {
            t1.setText("6");
        }
    }

    public void Click7(View v) {
        if(t1.getText() == "infinity"){
            t1.setText("");
        }
        StartAnimation1(seven);
        if (!(t1.getText().toString().equals(""))) {
            if (t1.getText().toString().equals("0")) {
                t1.setText("7");
            } else
                t1.setText(t1.getText().toString() + "7");
        } else {
            t1.setText("7");
        }
    }

    public void Click8(View v) {
        if(t1.getText() == "infinity"){
            t1.setText("");
        }
        StartAnimation1(eight);
        if (!(t1.getText().toString().equals(""))) {
            if (t1.getText().toString().equals("0")) {
                t1.setText("8");
            } else
                t1.setText(t1.getText().toString() + "8");
        } else {
            t1.setText("8");
        }
    }

    public void Click9(View v) {
        if(t1.getText() == "infinity"){
            t1.setText("");
        }
        StartAnimation1(nine);
        if (!(t1.getText().toString().equals(""))) {
            if (t1.getText().toString().equals("0")) {
                t1.setText("9");
            } else
                t1.setText(t1.getText().toString() + "9");
        } else {
            t1.setText("9");
        }
    }

    public void Click0(View v) {
        if(t1.getText() == "infinity"){
            t1.setText("");
        }
        StartAnimation1(zero);
        if (!(t1.getText().toString().equals(""))) {
            if (t1.getText().toString().equals("0")) {
                t1.setText("0");
            } else
                t1.setText(t1.getText().toString() + "0");
        } else {
            t1.setText("0");
        }
    }

    public void ClickC(View v) {
        if(t1.getText() == "infinity"){
            t1.setText("");
        }
        StartAnimation1(c);
        isdotclicked = false;
        t1.setText("0");
        t2.setText("");
        s.setText("");
    }

    public void ClickDel(View v) {
        if(t1.getText() == "infinity"){
            t1.setText("");
        }
        StartAnimation1(del);
        if (!(t1.getText().toString().equals("")) && t1.getText().toString().length() != 1) {
            if (t1.getText().toString().length() != 0) {
                String str = t1.getText().toString();
                if (str.charAt(str.length() - 1) == '.') {
                    isdotclicked = false;
                }
                str = str.substring(0, str.length() - 1);
                if (str == "0" || str == "")
                    t1.setText("0");
                else
                    t1.setText(str);
            }
        } else t1.setText("0");
    }


    public void ClickPlusMinus(View v) {
        if(t1.getText() == "infinity"){
            t1.setText("");
        }
        StartAnimation1(plmn);
        if (!(t1.getText().toString().equals(""))) {
            double value = Double.parseDouble(t1.getText().toString()) * -1;
            if (value == (int) value)
                t1.setText(Integer.toString((int) value));
            else
                t1.setText(Double.toString(value));
        }
    }

    public void ClickDot(View v) {
        if(t1.getText() == "infinity"){
            t1.setText("");
        }
        StartAnimation1(dot);
        if (!isdotclicked) {
            if (!(t1.getText().toString().equals(""))) {
                t1.setText(t1.getText().toString() + ".");
            } else {
                t1.setText("0.");
            }
        }
        isdotclicked = true;
    }

    public void ClickPlus(View v) {
        if(t1.getText() == "infinity"){
            t1.setText("");
        }
        StartAnimation1(plus);
        isdotclicked = false;
        if (!t1.getText().toString().equals("")) {
            if (!s.getText().toString().equals("")) {
                double a = Double.parseDouble(t2.getText().toString());
                double b = Double.parseDouble(t1.getText().toString());
                double value = 0;
                switch (s.getText().toString()) {
                    case "+":
                        value = a + b;
                        break;
                    case "-":
                        value = a - b;
                        break;
                    case "/":
                        value = a / b;
                        break;
                    case "*":
                        value = a * b;
                        break;
                }
                if (value == (int) value) {
                    t2.setText(Integer.toString((int) value));
                } else {
                    t2.setText(Double.toString(value));
                }
            } else {
                t2.setText(t1.getText().toString());

            }
            s.setText("+");
            t1.setText("0");
        }
    }

    public void ClickMinus(View v) {
        if(t1.getText() == "infinity"){
            t1.setText("");
        }
        StartAnimation1(minus);
        isdotclicked = false;
        if (!t1.getText().toString().equals("")) {
            if (!s.getText().toString().equals("")) {
                double a = Double.parseDouble(t2.getText().toString());
                double b = Double.parseDouble(t1.getText().toString());
                double value = 0;
                switch (s.getText().toString()) {
                    case "+":
                        value = a + b;
                        break;
                    case "-":
                        value = a - b;
                        break;
                    case "/":
                        value = a / b;
                        break;
                    case "*":
                        value = a * b;
                        break;
                    case "^":
                        value = Math.pow(a, b);
                        break;
                }

                if (value == (int) value) {
                    t2.setText(Integer.toString((int) value));
                } else {
                    t2.setText(Double.toString(value));
                }
            } else {
                t2.setText(t1.getText().toString());
            }
            s.setText("-");
            t1.setText("0");
        }
    }


    public void ClickMult(View v) {
        if(t1.getText() == "infinity"){
            t1.setText("");
        }
        StartAnimation1(mult);
        isdotclicked = false;
        if (!t1.getText().toString().equals("")) {
            if (!s.getText().toString().equals("")) {
                double a = Double.parseDouble(t2.getText().toString());
                double b = Double.parseDouble(t1.getText().toString());
                double value = 0;
                switch (s.getText().toString()) {
                    case "+":
                        value = a + b;
                        break;
                    case "-":
                        value = a - b;
                        break;
                    case "/":
                        value = a / b;
                        break;
                    case "*":
                        value = a * b;
                        break;
                    case "^":
                        value = Math.pow(a, b);
                        break;
                }
                if (value == (int) value) {
                    t2.setText(Integer.toString((int) value));
                } else {
                    t2.setText(Double.toString(value));
                }
            } else {
                t2.setText(t1.getText().toString());

            }
            s.setText("*");
            t1.setText("0");
        }
    }

    public void ClickDev(View v) {
        if(t1.getText() == "infinity"){
            t1.setText("");
        }
        StartAnimation1(dev);
        isdotclicked = false;
        if (!t1.getText().toString().equals("")) {
            if (!s.getText().toString().equals("")) {
                double a = Double.parseDouble(t2.getText().toString());
                double b = Double.parseDouble(t1.getText().toString());
                double value = 0;
                if (t1.getText().toString() != "") {
                    switch (s.getText().toString()) {
                        case "+":
                            value = a + b;
                            break;
                        case "-":
                            value = a - b;
                            break;
                        case "/":
                            if (b != 0) {
                                value = a / b;
                            } else {
                                t1.setText("infinity");
                                t2.setText("");
                            }
                            break;
                        case "*":
                            value = a * b;
                            break;
                        case "^":
                            value = Math.pow(a, b);
                            break;
                    }
                    if (value == (int) value) {
                        t2.setText(Integer.toString((int) value));
                    } else {
                        t2.setText(Double.toString(value));
                    }
                }
            } else {
                t2.setText(t1.getText().toString());

            }
            s.setText("/");
            t1.setText("0");
        }
    }

    public void ClickAuthor(View v) {
        if(t1.getText() == "infinity"){
            t1.setText("");
        }
        StartAnimation1(author);
        Intent intent = new Intent(MainActivity.this, AuthorActivity.class);
        startActivity(intent);
    }

    public void ClickEq(View v) {
        if(t1.getText() == "infinity"){
            t1.setText("");
        }
        StartAnimation1(eq);
        if (t2.getText().toString() != "" && t1.getText().toString() != "" && s.getText().toString() != "") {
            double a = Double.parseDouble(t2.getText().toString());
            double b = Double.parseDouble(t1.getText().toString());
            switch (s.getText().toString()) {
                case "+":
                    if (a + b == (int) (a + b))
                        t1.setText(Integer.toString((int) (a + b)));
                    else t1.setText(Double.toString(a + b));
                    s.setText("");
                    t2.setText("");
                    break;
                case "-":
                    if (a - b == (int) (a - b))
                        t1.setText(Integer.toString((int) (a - b)));
                    else t1.setText(Double.toString(a - b));
                    s.setText("");
                    t2.setText("");
                    break;
                case "/":
                    if (b != 0) {
                        if (a / b == (int) (a / b)) {
                            t1.setText(Integer.toString((int) (a / b)));
                            s.setText("");
                            t2.setText("");
                        } else {
                            t1.setText(Double.toString(a / b));
                            s.setText("");
                            t2.setText("");
                        }
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Division by 0", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    break;
                case "*":
                    if (a * b == (int) (a * b))
                        t1.setText(Integer.toString((int) (a * b)));
                    else t1.setText(Double.toString(a * b));
                    s.setText("");
                    t2.setText("");
                    break;
                case "^":
                    if (Math.pow(a,b) == (int) Math.pow(a, b))
                        t1.setText(Integer.toString((int) Math.pow(a,b)));
                    else t1.setText(Double.toString(Math.pow(a,b)));
                    s.setText("");
                    t2.setText("");
                    break;
            }

        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Empty parameters", Toast.LENGTH_SHORT);
            toast.show();
        }


    }
}

package tw.edu.pu.gm.poker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import tw.edu.pu.gm.poker.databinding.ActivityMainBinding
import java.lang.Math.abs

class MainActivity : AppCompatActivity() ,View.OnTouchListener,View.OnClickListener,GestureDetector.OnGestureListener{
    private lateinit var binding: ActivityMainBinding
    lateinit var g:GestureDetector
    var color:String=""
    var number:Int=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.txv.text="撲克牌魔術"
        binding.img.setOnTouchListener(this)
        g= GestureDetector(this,this)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if(event?.action==MotionEvent.ACTION_DOWN){
            binding.txv.text="按下"
            color=""
        }
        else if(event?.action==MotionEvent.ACTION_UP){
            if(color==""){
                binding.img.setImageResource(R.drawable.joker)
            }
            else{
                binding.txv.text="放開"
                var res:Int = getResources().getIdentifier(color + number.toString(),
                        "drawable", getPackageName())
                binding.img.setImageResource(res)
            }
        }
        g.onTouchEvent(event)
        return true
    }

    override fun onClick(v: View?) {
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onShowPress(e: MotionEvent?) {

    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        if(e1!!.x>=e2!!.x){
            if(e1!!.y>=e2!!.y){
                binding.txv.text="左上方"
                color="c"
            }
            else{
                binding.txv.text="左下方"
                color="d"
            }
        }
        else{
            if(e1!!.y>=e2!!.y){
                binding.txv.text="右上方"
                color="h"
            }else{
                binding.txv.text="右下方"
                color="s"
            }
            number = abs(e1!!.y.toInt() - e2!!.y.toInt())/(binding.img.height/26)+1
            if (number>13){ number=13 }
        }
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        return true
    }
}
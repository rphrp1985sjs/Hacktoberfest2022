import android.R
import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import com.prianshuprasad.brainsmasher.R.layout
import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import com.prianshuprasad.brainsmasher.Box
import com.prianshuprasad.brainsmasher.gameMachine
import com.prianshuprasad.brainsmasher.playground

class Adapter(private val listener: playground,context: Context,gM:gameMachine, BoxArray: MutableList<Box>) :
    ArrayAdapter<Box?>(listener, 0, BoxArray!! as List<Box?>) {

    var Isinitial=0;
    val gm = gM
    var userIndex=-1;
    var userIndex2=-1;
    var prevIndex=-1;
    var prevIndex2=-1;


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        if(Isinitial>17)
        {
          return ViewUserCard(position,convertView,parent)
        }
          Isinitial++;

        var listitemView = convertView
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(context).inflate(com.prianshuprasad.brainsmasher.R.layout.grid_support, parent, false)
        }
        val box: Box? = gm.getList()[(position)]

        val image= listitemView!!.findViewById<ImageView>(com.prianshuprasad.brainsmasher.R.id.card)



        var scale = context.resources.displayMetrics.density

        Handler().postDelayed({
            image.cameraDistance = 8000 * scale

            val flipOutAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context,
                    com.prianshuprasad.brainsmasher.R.anim.flipfront
                ) as AnimatorSet
            val flipInAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context,
                    com.prianshuprasad.brainsmasher.R.anim.flipback
                ) as AnimatorSet


            flipOutAnimatorSet.setTarget(image)
            flipOutAnimatorSet.start()
            Handler().postDelayed({
                if(box?.label!=0)
                image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.f)
                flipInAnimatorSet.setTarget(image)
                flipInAnimatorSet.start()
            },500)




        },((100*position).toLong()))

        convertView?.setOnClickListener {

            if(userIndex==-1|| userIndex2==-1) {
                if (gm.getList()[0].label != 0) {
                    if (userIndex == -1) {
                        userIndex = 0

                    } else {
                        if (position != userIndex)
                            userIndex2 = 0
                    }
                    ViewUserCard(position, convertView, parent)

                }
            }



        }

        return listitemView!!
    }

    fun ViewUserCard(position: Int, convertView: View?, parent: ViewGroup): View{



        var listitemView = convertView
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(context).inflate(com.prianshuprasad.brainsmasher.R.layout.grid_support, parent, false)
        }
        val box: Box? = gm.getList()[(position)]

        val image= listitemView!!.findViewById<ImageView>(com.prianshuprasad.brainsmasher.R.id.card)

        val flipOutAnimatorSet =
            AnimatorInflater.loadAnimator(
                context,
                com.prianshuprasad.brainsmasher.R.anim.flipfront
            ) as AnimatorSet
        val flipInAnimatorSet =
            AnimatorInflater.loadAnimator(
                context,
                com.prianshuprasad.brainsmasher.R.anim.flipback
            ) as AnimatorSet


        flipOutAnimatorSet.setTarget(image)


        if(position!=userIndex2 && userIndex!=position)
        {
            if(prevIndex==position )
            {
                flipOutAnimatorSet.start()
                Handler().postDelayed({
                    flipInAnimatorSet.setTarget(image)
                    flipInAnimatorSet.start()
                },500)
              prevIndex=-1;
            }
            if(prevIndex2==position )
            {
                flipOutAnimatorSet.start()
                Handler().postDelayed({
                    flipInAnimatorSet.setTarget(image)
                    flipInAnimatorSet.start()
                },500)
                prevIndex2=-1;
            }
            Handler().postDelayed({
                if (box?.label != 0)
                    image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.f)
                else
                    image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.empty)
            },500)

            return listitemView
        }




        if(position==userIndex2 )
        {
            flipOutAnimatorSet.start()



            Handler().postDelayed({
                when (box!!.label){
                    0-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.empty)
                    1-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.a)
                    2-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.b)
                    3-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.c)
                    4-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.d)
                    5-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.e)
                    6-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.g)
//            7-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.)

                }

                flipInAnimatorSet.setTarget(image)
                flipInAnimatorSet.start()
            },500)


            prevIndex=userIndex
            prevIndex2= userIndex2

            Handler().postDelayed({
                gm.validiateUserChoice(userIndex2,userIndex)
                userIndex=-1;
                userIndex2=-1;
                if(gm.isGameFinish())
                      listener.gameOver()
                    else
                   update()

            },2000)


            return  listitemView
        }

        if(userIndex2!=-1 && position==userIndex )
        {
            when (box!!.label){
                0-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.empty)
                1-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.a)
                2-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.b)
                3-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.c)
                4-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.d)
                5-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.e)
                6-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.g)
//            7-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.)

            }
            return listitemView

        }

        if(userIndex2==-1 && position== userIndex)
        {
            flipOutAnimatorSet.start()

            Handler().postDelayed({
                when (box!!.label){
                    0-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.empty)
                    1-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.a)
                    2-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.b)
                    3-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.c)
                    4-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.d)
                    5-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.e)
                    6-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.g)
//            7-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.)

                }

                flipInAnimatorSet.setTarget(image)
                flipInAnimatorSet.start()
            },500)



        }



        return listitemView

    }

    fun update()
    {
        notifyDataSetChanged();
    }



}
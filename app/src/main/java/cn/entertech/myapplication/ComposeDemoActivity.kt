package cn.entertech.myapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.entertech.myapplication.system.*

class ComposeDemoActivity : AppCompatActivity() {
    private val viewMap by lazy {
        mapOf(
            Pair("text", ComposeTextActivity::class.java),
            Pair("TextField", ComposeTextFieldActivity::class.java),
            Pair("Button", ComposeButtonActivity::class.java),
            Pair("Image", ComposeImageActivity::class.java),
            Pair("Box", ComposeTextActivity::class.java),
            Pair("Column", ComposeTextActivity::class.java),
            Pair("Row", ComposeTextActivity::class.java),
            Pair("Surface", ComposeTextActivity::class.java),
            Pair("Card", ComposeTextActivity::class.java),
            Pair("Divider", ComposeTextActivity::class.java),
            Pair("AlertDialog", ComposeTextActivity::class.java),
            Pair("SnackBar", ComposeTextActivity::class.java),
            Pair("ProgressIndicator", ComposeTextActivity::class.java),
            Pair("CheckBox", ComposeTextActivity::class.java),
            Pair("RadioButton", ComposeTextActivity::class.java),
            Pair("Switch", ComposeTextActivity::class.java),
            Pair("Slider", ComposeTextActivity::class.java),
            Pair("ScrollableColumn", ComposeTextActivity::class.java),
            Pair("ScrollableRow", ComposeTextActivity::class.java),
            Pair("TabRow", ComposeTextActivity::class.java)
        )
    }

    private val itemList by lazy {
        viewMap.keys.toList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecyclerViewComponent()
        }
    }


    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello, $name!", modifier = Modifier.padding(16.dp))
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        RecyclerViewComponent()
    }

    @Composable
    fun RecyclerViewComponent() {
        // AndroidView will take all available space in the screen
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                // Create and configure RecyclerView
                val recyclerView = RecyclerView(context)
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = ItemAdapter(this, viewMap, itemList)
                recyclerView
            }
        )
    }

    class ItemAdapter(
        val context: Context, val viewMap: Map<String, Class<out Activity>>,
        private val itemList: List<String>
    ) :
        RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

        class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val textView = TextView(parent.context)
            return ViewHolder(textView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = itemList[position]
            holder.textView.text = item
            holder.itemView.setOnClickListener {
                val activityClass = viewMap[item] ?: return@setOnClickListener
                context.startActivity(Intent(context, activityClass))
            }
        }

        override fun getItemCount() = itemList.size
    }
}

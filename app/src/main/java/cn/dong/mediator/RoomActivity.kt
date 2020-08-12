package cn.dong.mediator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.dong.mediator.manifest.AManifest
import cn.dong.mediator.manifest.BManifest

class RoomActivity : AppCompatActivity() {

    private var moduleContainer: ModuleContainer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // config
        val config = RoomConfig.parse(intent.extras)
        if (config == null) {
            finish()
            return
        }

        val roomContext = RoomContext(config)

        // manifest
        val manifest = when (config.type) {
            RoomType.ROOM_A -> AManifest(roomContext)
            RoomType.ROOM_B -> BManifest(roomContext)
        }

        val layoutId = manifest.getLayoutId()
        if (layoutId != null) {
            setContentView(layoutId)
        }

        val layoutManager = LayoutManager(window.decorView.rootView)

        moduleContainer = ModuleContainer(lifecycle, roomContext, manifest, layoutManager)
    }

    override fun finish() {
        moduleContainer?.destroy()
        super.finish()
    }
}

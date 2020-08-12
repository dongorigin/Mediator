package cn.dong.mediator

import android.os.Bundle

/**
 * @author zhaodong on 2020/08/11.
 */
class RoomConfig(
    val type: RoomType,
    val roomId: Int
) {

    companion object {

        /** 返回 null 表示数据异常 */
        fun parse(bundle: Bundle?): RoomConfig? {
            if (bundle == null) return null
            val typeValue = bundle.getInt("type", 0)
            val type = RoomType.values().find { it.type == typeValue } ?: return null
            val roomId = bundle.getInt("roomId")
            return RoomConfig(type, roomId)
        }
    }
}

enum class RoomType(val type: Int) {
    ROOM_A(0),
    ROOM_B(1);
}

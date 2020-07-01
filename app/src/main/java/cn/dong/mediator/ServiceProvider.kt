package cn.dong.mediator

fun <T> findServiceRequired(serviceClass: Class<T>): T {
    TODO()
}

inline fun <reified T> service() = ServiceLazy(T::class.java, true)

class ServiceLazy<T>(
    private val serviceClass: Class<T>,
    private val required: Boolean
) : Lazy<T> {

    private var cached: T? = null

    override val value: T
        get() {
            val service = cached
            return if (service == null) {
                findServiceRequired(serviceClass).also {
                    cached = it
                }
            } else {
                service
            }
        }

    override fun isInitialized(): Boolean = cached != null

}

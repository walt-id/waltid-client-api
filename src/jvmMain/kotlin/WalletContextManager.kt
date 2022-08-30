import com.google.common.cache.CacheBuilder
import com.google.common.cache.LoadingCache
import id.walt.services.context.Context
import id.walt.services.context.WaltIdContextManager
import io.javalin.http.Handler

object WalletContextManager : WaltIdContextManager() {

    val userContexts: LoadingCache<String, Context> = CacheBuilder.newBuilder().maximumSize(256).build(UserContextLoader)

    fun getUserContext(userInfo: UserInfo) = userContexts.get(userInfo.id)

}

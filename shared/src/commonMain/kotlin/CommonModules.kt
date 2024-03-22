import com.app.pixabay.core.di.dispatchersModule
import com.app.pixabay.core.network.ktorProvider
import com.app.pixabay.search.di.searchModules
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration) =
    startKoin {
        appDeclaration()
        modules(
            ktorProvider,
            dispatchersModule,
        )
        modules(searchModules())
    }

package domain.process

import org.jire.arrowhead.Module
import org.jire.arrowhead.Process
import org.jire.arrowhead.processByName

private const val GAME_PROCESS_NAME = "csgo.exe"
private const val GAME_PROCESS_CLIENT_DLL_NAME = "client_panorama.dll"
private const val GAME_PROCESS_ENGINE_DLL_NAME = "engine.dll"

class GameProcess {
    val process: Process by lazy {
        processByName(GAME_PROCESS_NAME)?.also { it.loadModules() } ?: error("Process not initialized")
    }

    val clientDll: Module by lazy {
        process.modules[GAME_PROCESS_CLIENT_DLL_NAME] ?: error("$GAME_PROCESS_CLIENT_DLL_NAME not found")
    }

    val engineDll: Module by lazy {
        process.modules[GAME_PROCESS_ENGINE_DLL_NAME] ?: error("$GAME_PROCESS_ENGINE_DLL_NAME not found")
    }
}
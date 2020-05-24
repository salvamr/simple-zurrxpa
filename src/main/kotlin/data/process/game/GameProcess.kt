package data.process.game

import org.jire.arrowhead.Module
import org.jire.arrowhead.Process
import org.jire.arrowhead.processByName

private const val GAME_PROCESS_NAME = "csgo.exe"
private const val GAME_PROCESS_CLIENT_DLL_NAME = "client_panorama.dll"
private const val GAME_PROCESS_ENGINE_DLL_NAME = "engine.dll"

class GameProcess {

    val process: Process by lazy {
        processByName(GAME_PROCESS_NAME)?.also { it.loadModules() } ?: error("$GAME_PROCESS_NAME not found")
    }

    private val clientDll = getModule(GAME_PROCESS_CLIENT_DLL_NAME)
    private val engineDll = getModule(GAME_PROCESS_ENGINE_DLL_NAME)

    val clientDllAddress = clientDll.address
    val engineDllAddress = engineDll.address

    private fun getModule(name: String): Module = process.modules[name] ?: error("$name not found")
}
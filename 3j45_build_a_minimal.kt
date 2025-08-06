import kotlin.system.exitProcess

fun main() {
    val monitoredProcesses = mutableMapOf<String, Process>()

    while (true) {
        println("Minimal CLI Tool Monitor")
        println("------------------------")
        println("1. Start a new process")
        println("2. List all processes")
        println("3. Kill a process")
        println("4. Exit")

        print("Enter your choice: ")
        val choice = readLine()!!.trim()

        when (choice) {
            "1" -> {
                print("Enter command to start a new process: ")
                val command = readLine()!!.trim()
                val process = ProcessBuilder(command.split(" "))
                    .inheritIO()
                    .start()
                monitoredProcesses[command] = process
                println("Process started: $command")
            }
            "2" -> {
                if (monitoredProcesses.isEmpty()) {
                    println("No processes running.")
                } else {
                    println("Running processes:")
                    monitoredProcesses.keys.forEachIndexed { index, key ->
                        println("${index + 1}. $key")
                    }
                }
            }
            "3" -> {
                if (monitoredProcesses.isEmpty()) {
                    println("No processes running.")
                } else {
                    println("Select a process to kill:")
                    monitoredProcesses.keys.forEachIndexed { index, key ->
                        println("${index + 1}. $key")
                    }
                    print("Enter your choice: ")
                    val killChoice = readLine()!!.trim().toInt()
                    val processKey = monitoredProcesses.keys.elementAt(killChoice - 1)
                    monitoredProcesses[processKey]!!.destroy()
                    monitoredProcesses.remove(processKey)
                    println("Process killed: $processKey")
                }
            }
            "4" -> {
                exitProcess(0)
            }
            else -> {
                println("Invalid choice. Please try again.")
            }
        }
    }
}
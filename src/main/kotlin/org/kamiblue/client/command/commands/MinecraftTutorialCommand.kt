package org.kamiblue.client.command.commands

import net.minecraft.client.tutorial.TutorialSteps
import org.kamiblue.client.command.ClientCommand
import org.kamiblue.client.util.text.MessageSendHelper

object MinecraftTutorialCommand : ClientCommand(
    name = "tutorial",
    alias = arrayOf("tut"),
    description = "Modifies the tutorial steps."
) {
    init {
        literal("clear", "stop", "cancel", "remove") {
            executeSafe("Stops the tutorial from showing by setting it to none") {
                mc.gameSettings.tutorialStep = TutorialSteps.NONE
                MessageSendHelper.sendChatMessage("Tutorial cleared.")
            }
        }

        literal("get") {
            executeSafe("Sets the tutorial step to a specific step") {
                MessageSendHelper.sendChatMessage("Current Tutorial Step: ${mc.gameSettings.tutorialStep.getName()}")
            }
        }

        literal("set") {
            enum<TutorialSteps>("tutorial step") { configTypeArg ->
                executeSafe("Sets the tutorial step to a specific step") {
                    mc.gameSettings.tutorialStep = configTypeArg.value
                }
            }
        }
    }
}
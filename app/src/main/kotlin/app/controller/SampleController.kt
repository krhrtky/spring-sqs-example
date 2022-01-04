package app.controller

import app.sender.SampleSender
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController("/")
class SampleController(private val sender: SampleSender) {

    @GetMapping("/{message}")
    fun sendMessage(@PathVariable("message") message: String): String {

        sender.send(message)

        return "Send message: $message"
    }
}

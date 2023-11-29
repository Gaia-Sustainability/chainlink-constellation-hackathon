package br.com.i3focus.nfe.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class NfeServiceApplication

fun main(args: Array<String>) {
    runApplication<NfeServiceApplication>(*args)
}

package org.dissis.ex1

class Pipeline<Input, Output>(private val handler: Handler<Input, Output>) : Handler<Input, Output> {

    override fun run(input: Input): Output = handler.run(input)
    fun <NextOutput> addStep(step: Handler<Output, NextOutput>): Pipeline<Input, NextOutput> {
        val nextHandler = object : Handler<Input, NextOutput> {
            override fun run(input: Input): NextOutput {
                return step.run(handler.run(input))
            }
        }
        return Pipeline(nextHandler)
    }
}

interface Handler<Input, Output> {
    fun run(input: Input): Output
}

class WordSplitter : Handler<String, List<String>> {
    override fun run(input: String): List<String> = input.split(" ")
}

class WordCounter : Handler<List<String>, Map<String, Int>> {
    override fun run(input: List<String>): Map<String, Int> = input.groupBy { x -> x }.mapValues { entry -> entry.value.count() }
}

fun main(args: Array<String>) {
    val pipeline = Pipeline(WordSplitter()).addStep(WordCounter())
    println(pipeline.run("The brown fox jumps over the lazy brown dog"))
}
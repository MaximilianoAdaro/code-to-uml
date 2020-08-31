package org.dissis.ex1;

import java.nio.channels.Pipe;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PipelineProgram {
    public static void main(String[] args) {
        final var pipeline = new Pipeline<>(new WordSplitter())
                .addStep(new WordCounter());
        System.out.println(pipeline.run("The brown fox jumps over the lazy brown dog"));
    }

    static class Pipeline<Input, Output> implements Handler<Input, Output> {

        private final Handler<Input, Output> handler;

        public Pipeline(Handler<Input, Output> handler) {
            this.handler = handler;
        }

        @Override
        public Output run(Input input) {
            return handler.run(input);
        }

        public <NextOutput> Pipeline<Input, NextOutput> addStep(Handler<Output, NextOutput> step) {
            var nextHandler = new Handler<Input, NextOutput>() {
                @Override
                public NextOutput run(Input input) {
                    return step.run(handler.run(input));
                }
            };
            return new Pipeline<>(nextHandler);
        }
    }

    interface Handler<Input, Output> {
        Output run(Input input);
    }

    static class WordSplitter implements Handler<String, Collection<String>> {
        @Override
        public Collection<String> run(String s) {
            return Arrays.stream(s.split(" ")).collect(Collectors.toCollection(LinkedList::new));
        }
    }

    static class WordCounter implements Handler<Collection<String>, Map<String, Long>> {
        @Override
        public Map<String, Long> run(Collection<String> strings) {
            return strings.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        }
    }
}

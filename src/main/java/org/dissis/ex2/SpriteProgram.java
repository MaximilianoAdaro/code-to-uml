package org.dissis.ex2;

public class SpriteProgram {
    public static void main(String[] args) {

    }

    interface Sprite {
        void draw(Graphics graphics);
    }

    static class CircleSprite implements Sprite{

        @Override
        public void draw(Graphics graphics) {

        }
    }

    static class RectangleSprite implements Sprite {
        @Override
        public void draw(Graphics graphics) {

        }
    }

    static class ZeldaSprite implements Sprite {
        @Override
        public void draw(Graphics graphics) {
            // do something
        }
    }

    static class Graphics {

    }
}

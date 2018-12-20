package com.example.csc.gamebasic2;

public class Planet {

        int x,y;
        int planetSpeed=15;
        Planet(int x, int y){

            this.x = x; this.y = y;

        }

        public void move(){
            y+=planetSpeed;
        }

}











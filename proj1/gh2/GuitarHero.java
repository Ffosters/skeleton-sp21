package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static final String keyBoard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static GuitarString[] strings = new GuitarString[keyBoard.length()];
    public static void main(String[] args) {
        for(int i = 0; i < keyBoard.length();i++){
            strings[i] = new GuitarString(countFrequency(i));
        }

        GuitarString aString;
        while(true){
            if(StdDraw.hasNextKeyTyped()){
                char key = StdDraw.nextKeyTyped();
                if(keyBoard.indexOf(key) >= 0){
                    int index = keyBoard.indexOf(key);
                    aString = strings[index];
                    aString.pluck();
                }
            }

            double sample = 0;
            for(GuitarString string : strings){
                sample += string.sample();
            }
            StdAudio.play(sample);
            for(int i = 0; i < strings.length ; i++){
                strings[i].tic();
            }


        }
    }

    //计算频率
    public static double countFrequency(int index){
        return 440 * Math.pow(2,((index - 24) / 12.0));
    }
}

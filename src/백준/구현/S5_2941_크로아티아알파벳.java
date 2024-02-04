package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * S5 2941 크로아티아 알파벳
 * 구현, 문자열
 */
public class S5_2941_크로아티아알파벳 {

    /*
    c=
    c-
    lj
    nj
    s=

    d-
    dz=
    z=
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int ans = 0;
        int i = 0;
        while (i<s.length()){
            if(i==s.length()-1){
            } else if(s.charAt(i) == 'c' && (s.charAt(i+1) =='=' || s.charAt(i+1) =='-')){
                i++;
            } else if (s.charAt(i+1) == 'j' && (s.charAt(i) =='l' || s.charAt(i) =='n')) {
                i++;
            }else if(s.charAt(i) == 's' && s.charAt(i+1)=='='){
                i++;
            }else if(s.charAt(i) == 'd'){
                if(s.charAt(i+1) == '-'){
                    i++;
                } else if (i<s.length()-2 && s.charAt(i + 1) == 'z' && s.charAt(i + 2) == '=') {
                    i += 2;
                }
            } else if (s.charAt(i) == 'z' && s.charAt(i + 1) == '=') {
                i++;
            }
            ans+=1;
            i+=1;
        }
        System.out.println(ans);
    }
}
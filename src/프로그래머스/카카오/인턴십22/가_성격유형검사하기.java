import java.util.HashMap;
import java.util.Map;

/*
2:50:40
 */
class 가_성격유형검사하기 {
    public String solution(String[] survey, int[] choices) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < survey.length; i++) {
            int score = map.getOrDefault(survey[i], 0) + choices[i] - 4;
            map.put(survey[i], score);
        }

        int rt = map.getOrDefault("TR", 0) - map.getOrDefault("RT", 0);
        int cf = map.getOrDefault("FC", 0) - map.getOrDefault("CF", 0);
        int jm = map.getOrDefault("MJ", 0) - map.getOrDefault("JM", 0);
        int an = map.getOrDefault("NA", 0) - map.getOrDefault("AN", 0);

        StringBuilder sb = new StringBuilder();
        if(rt>=0){
            sb.append('R');
        }else{
            sb.append('T');
        }

        if(cf>=0){
            sb.append('C');
        }else{
            sb.append('F');
        }

        if(jm>=0){
            sb.append('J');
        }else{
            sb.append('M');
        }

        if(an>=0){
            sb.append('A');
        }else{
            sb.append('N');
        }
        return sb.toString();
    }
}
package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * S3_16165_걸그룹_마스터_준석이
 */
public class S3_16165_걸그룹_마스터_준석이 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder ans = new StringBuilder();
            int T = 1;
//            int T = Integer.parseInt(br.readLine());
            for (int i = 1; i <= T; i++) {
                ans.append(solve(br));
                ans.append("\n");
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Group> groups = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String gName = br.readLine();
            List<String> members = new ArrayList<>();
            int nMember = Integer.parseInt(br.readLine());
            while (nMember-- > 0) {
                members.add(br.readLine());
            }
            groups.add(new Group(gName, members));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            int q = Integer.parseInt(br.readLine());
            if (q == 0) {
                groups.stream()
                        .filter(g -> g.name.equals(name))
                        .findFirst()
                        .ifPresent(g -> g.members.stream()
                                .forEach(m -> sb.append(m).append("\n")));
            } else {
                groups.stream()
                        .filter(g -> g.members.stream().
                                anyMatch(m -> m.equals(name)))
                        .findFirst()
                        .ifPresent(g -> sb.append(g.name).append("\n"));
            }
        }
        return sb;
    }

    static class Group {
        String name;
        List<String> members;

        public Group(String name, List<String> members) {
            this.name = name;
            this.members = members;
            members.sort(String::compareTo);
        }
    }
}
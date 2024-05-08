// Time Complexity : k^(n/k) * log(n/k), k = average length of characters in braces , n = total string length. 
//TC: number of levels: n/k and options at each level = k
// Space Complexity : O(n) for groups ( will have n/k lists of k size) and n/k recursive space.
// Did this code successfully run on Leetcode : yes
//approach : Go exhaustive and find each possible combination and add it to result.


// Your code here along with comments explaining your approacg

class Solution {
    public String[] expand(String s) {
        List<String> result = new ArrayList<>();

        List<List<Character>> groups = new ArrayList<>();
        for(int i = 0 ; i < s.length(); i++){
            List<Character> group = new ArrayList<>();
            if(s.charAt(i) == '{'){
                i++;
                while(s.charAt(i) != '}'){
                    if(s.charAt(i) != ','){
                        group.add(s.charAt(i));
                    }
                    i++;
                }
            }
            else{
                group.add(s.charAt(i));
            }
            // i++;
            Collections.sort(group); // sort the group initially
            groups.add(group);
        }

        helper(groups, 0, result, new StringBuilder());
        // System.out.println(groups.toString());
        return result.toArray(new String[0]);
    }

    private void helper(List<List<Character>> groups,int idx, List<String> result, StringBuilder path ){
        //base
        if(idx ==  groups.size()){
            result.add(path.toString());
            return;
        }
        //logic
        List<Character> group = groups.get(idx);
        for(int i = 0 ; i < group.size(); i++){
            //action
            path.append(group.get(i));
            //recurse
            helper(groups, idx+1, result, path);
            //backtrack
            path.deleteCharAt(path.length()-1);
        }
    }
}
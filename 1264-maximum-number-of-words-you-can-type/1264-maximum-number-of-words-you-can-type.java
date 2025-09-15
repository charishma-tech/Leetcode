class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        Set<Character> s1=new HashSet<>();
        for (char ch:brokenLetters.toCharArray()) s1.add(ch);
        int result=0;
        int count=0;
        for (char ch:text.toCharArray()){
            if (ch==' '){
                result+=(count==0) ? 1 : 0;
                count=0;
            }
            else count+=(s1.contains(ch)==true?1:0);
        }
        result += (count == 0) ? 1 : 0;
        return result;
    }
}
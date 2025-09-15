class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        boolean[] b=new boolean[26];
        for (char ch:brokenLetters.toCharArray()) b[ch-'a']=true;
        int result=0,count=0;
        for (char ch:text.toCharArray()){
            if (ch==' '){
                result+=(count==0)?1:0;
                count=0;
            }
            else count+=(b[ch-'a']==true)?1:0;
        }
        result+=(count==0)?1:0;
        return result;
    }
}
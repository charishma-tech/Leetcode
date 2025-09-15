class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        boolean[] b=new boolean[26];
        for (int i=0;i<brokenLetters.length();i++){
            char ch=brokenLetters.charAt(i);
            b[ch-'a']=true;
        }
        int result=0,count=0;
        for (int i=0;i<text.length();i++){
            char ch=text.charAt(i);
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
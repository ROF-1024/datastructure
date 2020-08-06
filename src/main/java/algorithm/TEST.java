package algorithm;

import org.junit.Test;

/**
 * @Author xujie
 * @Date 2020/7/2 10:55
 * git做一下修改测试
 */
public class TEST {

    @Test
    public void test() {
        String s = "MCMXCIV";
        Solution solution = new Solution();
        int res = solution.romanToInt(s);
        System.out.println(res);
/*        int result = romanToInt(s);
        System.out.println(result);*/
    }

    public int romanToInt(String s) {
        char[] ch = s.toCharArray();
        int res=0;
        for(int i = 0; i < ch.length; i++){
            switch(ch[i]){
                case 'I':
                    if(i+1 < ch.length && ch[i+1]=='V'){
                        res+=4;
                        i++;
                    }
                    if(i+1 < ch.length && ch[i+1]=='X'){
                        res+=9;
                        i++;
                    }else{
                        res+=1;
                    }
                    break;
                case 'V':
                    res+=5;
                    break;
                case 'X':
                    if(i+1 < ch.length && ch[i+1]=='L'){
                        res+=40;
                        i++;
                    }else if(i+1 < ch.length && ch[i+1]=='C'){
                        res+=90;
                        i++;
                    }else{
                        res+=10;
                    }
                    break;
                case 'L':
                    res+=50;
                    break;
                case 'C':
                    if(i+1 < ch.length && ch[i+1]=='D'){
                        res+=400;
                        i++;
                    }else if(i+1 < ch.length && ch[i+1]=='M'){
                        res+=900;
                        i++;
                    }else{
                        res+=100;
                    }
                    break;
                case 'D':
                    res+=500;
                    break;
                case 'M':
                    res+=1000;
                    break;
                default:
                    System.out.println("字符串中含有非法字符！");
                    break;
            }
        }
        return res;
    }
}

class Solution {
    public int romanToInt(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for(int i = 1;i < s.length(); i ++) {
            int num = getValue(s.charAt(i));
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}


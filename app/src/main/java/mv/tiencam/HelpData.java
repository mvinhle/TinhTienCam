package mv.tiencam;

import android.util.Log;
import android.widget.TextView;

public class HelpData {
    public static Double dataInput(String s){ // chuyển String thành double và sửa 1 số Exception
        if (s.equals("")){s = "0.0";}
        if (s.equals("0")){s = "0.0";}
        if (s.startsWith(".")){s = "0".concat(s);}
        if (s.endsWith(".")){s = s.concat("0");}
        return Double.parseDouble(s);
    }

    public static String readNumber(double d){
        String tam = String.valueOf((long)d);
        String n = "";
        for (int i = tam.length()-1; i >= 0; i--){n += tam.charAt(i);}
        String s = "";
        boolean b = true;
            switch (n.length()-1){
                default:
                case 14:
                    if (!String.valueOf(n.charAt(14)).equals("0")){b = true;}
                    if (b){s += readN(2, null, n, 14);}
                    else if (!b && String.valueOf(n.charAt(14)).equals("0")){s += " . ";}
//                    if (String.valueOf(n.charAt(14)).equals("0")){b = false;}
                case 13:
                    if (!String.valueOf(n.charAt(13)).equals("0")){b = true;}
                    if (b){s += readN(1, null, n, 13);}
                    else if (!b && String.valueOf(n.charAt(13)).equals("0")){s += " . ";}
//                    if (String.valueOf(n.charAt(13)).equals("0")){b = false;}
                case 12:
                    if (!String.valueOf(n.charAt(13)).equals("0")){b = true;}
                    if (b){s += readN(0, " ngìn ", n, 13);}
                    else if (!b && String.valueOf(n.charAt(13)).equals("0")){s += " . ";}
//                    if (String.valueOf(n.charAt(13)).equals("0")){b = false;}

                case 11:
                    if (!String.valueOf(n.charAt(11)).equals("0")){b = true;}
                    if (b){s += readN(2, null, n, 11);}
                    else if (!b && String.valueOf(n.charAt(11)).equals("0")){s += " . ";}
//                    if (String.valueOf(n.charAt(11)).equals("0")){b = false;}
                case 10:
                    if (!String.valueOf(n.charAt(10)).equals("0")){b = true;}
                    if (b){s += readN(1, null, n, 10);}
                    else if (!b && String.valueOf(n.charAt(10)).equals("0")){s += " . ";}
//                    if (String.valueOf(n.charAt(10)).equals("0")){b = false;}
                case 9:
                    if (!String.valueOf(n.charAt(9)).equals("0")){b = true;}
                    if (b){s += readN(0, " tỉ ", n, 9);}
                    else if (!b && String.valueOf(n.charAt(9)).equals("0")){s += " . ";}
//                    if (String.valueOf(n.charAt(9)).equals("0")){b = false;}

                case 8:
                    if (!String.valueOf(n.charAt(8)).equals("0")){b = true;}
                    if (b){s += readN(2, null, n, 8);}
                    else if (!b && String.valueOf(n.charAt(8)).equals("0")){s += " . ";}
//                    if (String.valueOf(n.charAt(8)).equals("0")){b = false;}
                case 7:
                    if (!String.valueOf(n.charAt(7)).equals("0")){b = true;}
                    if (b){s += readN(1, null, n, 7);}
                    else if (!b && String.valueOf(n.charAt(7)).equals("0")){s += " . ";}
//                    if (String.valueOf(n.charAt(7)).equals("0")){b = false;}
                case 6:
                    if (!String.valueOf(n.charAt(6)).equals("0")){b = true;}
                    if (b){s += readN(0, " triệu ", n, 6);}
                    else if (!b && String.valueOf(n.charAt(6)).equals("0")){s += " . ";}
//                    if (String.valueOf(n.charAt(6)).equals("0")){b = false;}

                case 5:
                    if (!String.valueOf(n.charAt(5)).equals("0")){b = true;}
                    if (b){s += readN(2, null, n, 5);}
                    else if (!b && String.valueOf(n.charAt(5)).equals("0")){s += " . ";}
//                    if (String.valueOf(n.charAt(5)).equals("0")){b = false;}
                case 4:
                    if (!String.valueOf(n.charAt(4)).equals("0")){b = true;}
                    if (b){s += readN(1, null, n, 4);}
                    else if (!b && String.valueOf(n.charAt(4)).equals("0")){s += " . ";}
//                    if (String.valueOf(n.charAt(4)).equals("0")){b = false;}
                case 3:
                    if (!String.valueOf(n.charAt(3)).equals("0")){b = true;}
                    if (b){s += readN(0, " ngìn ", n, 3);}
                    else if (!b && String.valueOf(n.charAt(3)).equals("0")){s += " . ";}
//                    if (String.valueOf(n.charAt(3)).equals("0")){b = false;}

                case 2:
                    if (!String.valueOf(n.charAt(2)).equals("0")){b = true;}
                    if (b){s += readN(2, null, n, 2);}
                    else if (!b && String.valueOf(n.charAt(2)).equals("0")){s += " . ";}
//                    if (String.valueOf(n.charAt(2)).equals("0")){b = false;}
                case 1:
                    if (!String.valueOf(n.charAt(1)).equals("0")){b = true;}
                    if (b){s += readN(1, null, n, 1);}
                    else if (!b && String.valueOf(n.charAt(1)).equals("0")){s += " . ";}
//                    if (String.valueOf(n.charAt(1)).equals("0")){b = false;}
                case 0:
                    if (!String.valueOf(n.charAt(0)).equals("0")){b = true;}
                    if (b){s += readN(0, " đồng.", n, 0);}
                    else if (!b && String.valueOf(n.charAt(0)).equals("0")){s += " . ";}
//                    if (String.valueOf(n.charAt(0)).equals("0")){b = false;}
            }
        return s;
    }
    private static String readN(int i, String s, String n, int vt){
        String ss = "";
        switch (i) {
            case 2:
                ss = ss.concat(number(n.charAt(vt))).concat(" trăm ");
                break;
            case 1:
                if (n.charAt(vt) == '1') {
                    ss = ss.concat(" mười ");
                } else {
                    ss = ss.concat(number(n.charAt(vt)).concat(" mươi "));
                }
                break;
            case 0:
                ss = ss.concat(number(n.charAt(vt)).concat(" "+s+" "));
                break;
                default:
                    ss = ss.concat(". ");
                    break;
        }
        return ss;
    }
    private static String number(char c){
        switch ((char)c){
            case '0': return "không";
            case '1': return "một";
            case '2': return "hai";
            case '3': return "ba";
            case '4': return "bốn";
            case '5': return "năm";
            case '6': return "sáu";
            case '7': return "bảy";
            case '8': return "tám";
            case '9': return "chín";
            default: return " _ ";
        }
    }
}

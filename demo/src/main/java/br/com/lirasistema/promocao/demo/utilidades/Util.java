package br.com.lirasistema.promocao.demo.utilidades;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class Util {
    
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat dateFormatWeb = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat dataHoraFormatado = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    public static String calendarToStringWeb(Calendar cal) {
        if (cal != null) {
            return dateFormatWeb.format(cal.getTime());
        } else {
            return "";
        }
    }
    
    public static String calendarToString(Calendar cal) {
        if (cal != null) {
            return dateFormat.format(cal.getTime());
        } else {
            return "";
        }
    }
    
    public static Calendar stringToCalendarWeb(String data) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(dateFormatWeb.parse(data));
            return c;
        } catch (ParseException ex) {

        } catch (Exception e) {

        }
        return null;
    }
    
    public static Calendar stringToCalendar(String data) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(dateFormat.parse(data));
            return c;
        } catch (ParseException ex) {

        } catch (Exception e) {

        }
        return null;
    }
    
    public static String md5(String senha) {
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        sen = hash.toString(16);
        return sen;
    }
    
    public static String dataHoraFormatada(Calendar data) {
        return dataHoraFormatado.format(data.getTime());
    }

    public static String dataHoraFormatada(Date data) {
        return dataHoraFormatado.format(data);
    }
    
    public static Integer gerarNumeroAleatorio(Integer maximo) {
        Random random = new Random();
        return random.nextInt(maximo);
    }

    public static Integer validarInteiro(String descricao) {
        try {
            return Integer.parseInt(descricao);
        } catch (Exception ex) {
            return 0;
        }
    }
}

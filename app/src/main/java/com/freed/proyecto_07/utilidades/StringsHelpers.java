package com.freed.proyecto_07.utilidades;

public class StringsHelpers {
    public boolean isEmail(String email){
        if(email.contains("@")){
            String[] parts = email.split("@");
            if (parts.length == 2){
                if (parts[1].contains(".")){

                    parts = parts[1].split("\\.");
                    if (parts.length == 2){
                        if (parts[0].length() >= 3 && parts[1].length() >= 2){
                            return true;

                        }else
                            return false;


                    }else if (parts.length == 3){
                        if (parts[0].length() >= 3 && parts[1].length() >= 2 && parts[1].length()== 2){
                            return true;

                        }else
                            return false;
                    }else return false;

                }else
                    return false;

            }else
                return false;

        }else
        return false;

    }
    public boolean isUserPass(String mail,String pass){

        return (isEmail(mail) && !pass.isEmpty())? true : false;

    }
}
